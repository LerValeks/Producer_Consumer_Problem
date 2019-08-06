package pl.ovaluyskov.ProductionLine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ProductionLineTest {

    public static void main(String[] args) {
        ProductionLine productionLine = new ProductionLine();

        Producer pr1 = new Producer(productionLine);
        Consumer cr1 = new Consumer(productionLine);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(pr1);
        executorService.execute(cr1);

        executorService.shutdown();

        try {
            // wait 1 minute for both writers to finish executing
            boolean tasksEnded =
                    executorService.awaitTermination(1, TimeUnit.MINUTES);
            if (tasksEnded) {
                System.out.println("Task was finished");
            } else {
                System.out.println(
                        "Timed out while waiting for tasks to finish.");
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }


    }
}
