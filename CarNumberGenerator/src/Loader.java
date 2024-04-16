import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Loader {

    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(5);
        NumberGenerator generator1 = new NumberGenerator("res/numbers1.txt", 1, 200);
        NumberGenerator generator2 = new NumberGenerator("res/numbers2.txt", 200, 400);
        NumberGenerator generator3 = new NumberGenerator("res/numbers3.txt", 400, 600);
        NumberGenerator generator4 = new NumberGenerator("res/numbers4.txt", 600, 800);
        NumberGenerator generator5 = new NumberGenerator("res/numbers5.txt", 800, 1000);

        service.execute(generator1::getNums);
        service.execute(generator2::getNums);
        service.execute(generator3::getNums);
        service.execute(generator4::getNums);
        service.execute(generator5::getNums);
        service.shutdown();

    }
}
