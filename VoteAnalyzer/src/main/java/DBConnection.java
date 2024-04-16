import java.sql.*;

public class DBConnection {

    private static Connection connection;

    private static String dbName = "learn";
    private static String dbUser = "root";
    private static String dbPass = "6488274Ss";

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + dbName + "?allowLoadLocalInfile=true"+
                        "&user=" + dbUser + "&password=" + dbPass);
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "name TINYTEXT NOT NULL, " +
                    "birthDay DATE NOT NULL, " +
                    "PRIMARY KEY(id))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void executeMultiInsert(String path) throws Exception{
        String sqll = "LOAD XML LOCAL INFILE '" + path +
                "' INTO TABLE voter_count " +
                "ROWS IDENTIFIED BY '<voter>';";
        DBConnection.getConnection().createStatement().execute(sqll);
    }


    public static void printVoterCounts() throws SQLException {
        String sql = "SELECT voter_count.name, birthDay, count(voter_count.name) FROM learn.voter_count " +
                "group by voter_count.name, birthDay ";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            if(rs.getInt("count(voter_count.name)") > 1) {
                System.out.println("\t" + rs.getString("name") + " (" +
                        rs.getString("birthDay") + ") - " + rs.getInt("count(voter_count.name)"));
            }
        }
    }
}
