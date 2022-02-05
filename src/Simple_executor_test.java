import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Simple_executor_test {

    private static final int NTHREADS = 10;

    public static void main(String[] args) {

        Calka_callable heh = new Calka_callable(0, Math.PI, 0.0001);
        System.out.println(heh.compute());
        ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);
        List<Future<Double>> list = new ArrayList<Future<Double>>();
        double start = 0.0;
        double end = Math.PI;
        double przedzial = (end - start) / NTHREADS;
        for (int i = 0; i < NTHREADS; i++) {

            Callable<Double> callable = new Calka_callable((przedzial * i), (przedzial * (i + 1)), 0.0001);
            Future<Double> future = executor.submit(callable);
            list.add(future);
        }
        double resultCalka = 0;
        for (Future<Double> result : list) {
            try {
                resultCalka += result.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.print("Result Calka: " + resultCalka);
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        int[] numbers = {10,9,8,7,6,5,4,3,2,1,0};
        DivideTask hehy = new DivideTask(numbers);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.execute(hehy);

        System.out.println(Arrays.toString(numbers));
        System.out.println(Arrays.toString(hehy.join()));

    }
}

