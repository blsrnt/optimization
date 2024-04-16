

public class Loader {


    public static void main(String[] args) throws Exception {
        String fileName = "res/data-1572M.xml";
        long start = System.currentTimeMillis();
        DBConnection.executeMultiInsert(fileName);
        System.out.println(System.currentTimeMillis() - start);
        DBConnection.printVoterCounts();
        System.out.println(System.currentTimeMillis() - start);
    }
}