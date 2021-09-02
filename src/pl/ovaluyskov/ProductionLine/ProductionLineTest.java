package pl.ovaluyskov.ProductionLine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProductionLineTest {

    public static void main(String[] args) {
        ProductionLine productionLine = new ProductionLine();


        ReentrantLock lock = new ReentrantLock();
        Condition con = lock.newCondition();
        Producer pr1 = new Producer(lock, con, productionLine);
        Producer pr2 = new Producer(lock, con, productionLine);
        Consumer cr1 = new Consumer(lock, con, productionLine);
        Consumer cr2 = new Consumer(lock, con, productionLine);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(pr1);
        executorService.execute(pr2);
        executorService.execute(cr1);
        executorService.execute(cr2);

        executorService.shutdown();

        try {
            // wait 1 minute for both writers to finish executing
            boolean tasksEnded =
                    executorService.awaitTermination(30, TimeUnit.SECONDS);
            if (tasksEnded) {
                System.out.println("Boxes Consumed " + productionLine.getNumberOfConsumedBoxes() );
            } else {
                System.out.println("Boxes Consumed " + productionLine.getNumberOfConsumedBoxes() );
                System.out.println(
                        "Timed out while waiting for tasks to finish.");
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }


    }
}
