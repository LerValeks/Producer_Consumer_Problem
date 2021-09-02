package pl.ovaluyskov.ProductionLine;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Consumer implements Runnable {
    ReentrantLock lock;
    Condition con;
    private final ProductionLine productionLine;

    public Consumer(ReentrantLock lock, Condition con, ProductionLine productionLine) {
        this.lock = lock;
        this.con = con;
        this.productionLine = productionLine;
    }

    @Override
    public void run() {


        try {
            while (productionLine.isProducing() ||  productionLine.getNumberOfBoxOnProductionLine() > 0) {
                
//                Thread.sleep(1000);
                lock.lock();
                while (productionLine.getNumberOfBoxOnProductionLine() < 1)

                    try {
                        System.out.println("Consumer waiting");
                        con.await();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                productionLine.remove();
                productionLine.setNumberOfConsumedBoxes();
                System.out.println("Consumer " + Thread.currentThread().getName() + " boxes on Line " + productionLine.getNumberOfBoxOnProductionLine() + " box consumed: " + productionLine.getNumberOfConsumedBoxes());
                con.signal();
                lock.unlock();
            }
        } catch (InterruptedException excep) {
            excep.printStackTrace();
        }

    }
}
