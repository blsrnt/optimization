import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class NumberGenerator{
    private char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
    private PrintWriter writer;
    private String path;
    private int start;
    private int end;

    public NumberGenerator(String path, int start, int end) {
        this.path = path;
        this.start = start;
        this.end = end;
    }

    public void getNums(){
        try {
            writer = new PrintWriter(path);
        }catch (Exception e){
            e.printStackTrace();
        }
        long startTimer = System.currentTimeMillis();
        for (int regionCode = 1; regionCode < 100; regionCode++) {
            StringBuilder builder = new StringBuilder();
            for (int number = start; number < end; number++) {
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            builder.append(firstLetter).
                                    append(padNumber(number, 3)).
                                    append(secondLetter).
                                    append(thirdLetter).
                                    append(padNumber(regionCode, 2)).append("\n");
                        }
                    }
                }
            }
            writer.write(builder.toString());
        }
        writer.flush();
        writer.close();
        System.out.println((System.currentTimeMillis() - startTimer) + " ms");
    }


    private static String padNumber(int number, int numberLength) {
        StringBuilder numberStr = new StringBuilder(Integer.toString(number));
        int padSize = numberLength - numberStr.length();

        for (int i = 0; i < padSize; i++) {
            numberStr.insert(0, '0');
        }

        return numberStr.toString();
    }
}
