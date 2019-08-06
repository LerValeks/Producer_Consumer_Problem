package pl.ovaluyskov.ProductionLine;

import java.util.ArrayList;

public class Producer implements Runnable {
    private final ProductionLine productionLine;
    private String name;


    public Producer(ProductionLine productionLine, String name) {
        this.productionLine = productionLine;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void produce() throws InterruptedException {
        while (productionLine.getToBeProduced()!= productionLine.getProducedBoxes()) {
            synchronized (this) {
                while (productionLine.getFilledSlots() == productionLine.getCapacity()) {
                    System.out.println(getName() + ": Line is full");
                    wait();
                }
                productionLine.setProducedBoxes();
                productionLine.addBox();
                System.out.println(getName() + " made " + productionLine.getProducedBoxes() +
                        " empty slots " + productionLine.getEmptySlots() +
                        " filled slots " + productionLine.getFilledSlots());

                notify();
                Thread.sleep(1000);
            }
        }
    }


    @Override
    public void run() {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

