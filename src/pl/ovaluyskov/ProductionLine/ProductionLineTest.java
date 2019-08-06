package pl.ovaluyskov.ProductionLine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ProductionLineTest {

    public static void main(String[] args) {
        ProductionLine productionLine = new ProductionLine(6,6,12,10,30);

        Producer pr1 = new Producer(productionLine, "producer1");
        Producer pr2 = new Producer(productionLine, "producer2");
        Producer pr3 = new Producer(productionLine, "producer3");
        Consumer cr1 = new Consumer(productionLine, "consumer1");
        Consumer cr2 = new Consumer(productionLine, "consumer2");
        Consumer cr3 = new Consumer(productionLine, "consumer3");
        Consumer cr4 = new Consumer(productionLine, "consumer4");

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(pr1);
        executorService.execute(pr2);
        executorService.execute(pr3);
        executorService.execute(cr1);
        executorService.execute(cr2);
        executorService.execute(cr3);
        executorService.execute(cr4);

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
