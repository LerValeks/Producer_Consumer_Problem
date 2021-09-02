package pl.ovaluyskov.ProductionLine;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Producer implements Runnable {
    private final ProductionLine productionLine;
    ReentrantLock lock;
    Condition con;

    public Producer(ReentrantLock lock, Condition con, ProductionLine productionLine) {
        this.productionLine = productionLine;
        this.lock = lock;
        this.con = con;
    }

    @Override
    public void run() {
        ArrayList unsortedArray = new ArrayList();

        for (int i = 1; i <= 20; i++) {
            lock.lock();

            try {
                while (productionLine.getMaxNumberOfBoxOnProductionLine() <= productionLine.getNumberOfBoxOnProductionLine()) {

                    try {
                        con.await();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                productionLine.add();
                System.out.println("Producer " + Thread.currentThread().getName() + " boxes on Line " + productionLine.getNumberOfBoxOnProductionLine());
                this.productionLine.setProducing();
                con.signal();
                lock.unlock();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
}
