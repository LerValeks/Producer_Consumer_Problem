package pl.ovaluyskov.ProductionLine;

import java.util.ArrayList;

public class Producer implements Runnable {
    private final ProductionLine productionLine;


    public Producer(ProductionLine productionLine) {
        this.productionLine = productionLine;
    }

    @Override
    public void run() {
        ArrayList unsortedArray = new ArrayList();
        int i = 1;
        try {
                while  (i<=10) {
                    synchronized (this) {
                        while (productionLine.getFilledSlots() == productionLine.getCapacity())
                            wait();

                        productionLine.addBox();
                        System.out.println("Producer produced " + productionLine.getFilledSlots());
                        i++;
                        notify();
                        Thread.sleep(1000);

                    }
                }


        } catch (InterruptedException excep) {
            excep.printStackTrace();
        }

        this.productionLine.FinishProduction();

    }
}
