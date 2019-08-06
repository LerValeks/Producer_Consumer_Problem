package pl.ovaluyskov.ProductionLine;

import java.util.ArrayList;

public class Producer implements Runnable {
    private final ProductionLine productionLine;


    public Producer(ProductionLine productionLine) {
        this.productionLine= productionLine;
    }

    @Override
    public void run() {
        ArrayList unsortedArray = new ArrayList();

        try {
            for(int i = 1; i<5;i++) {
                System.out.println("Producer produced " + i);
                Thread.sleep(100);
                productionLine.add();
            }
        } catch (InterruptedException excep) {
            excep.printStackTrace();
        }

        this.productionLine.setProducing();

    }
}
