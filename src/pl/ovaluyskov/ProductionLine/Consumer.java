package pl.ovaluyskov.ProductionLine;

public class Consumer implements Runnable {

    private final ProductionLine productionLine;

    public Consumer(ProductionLine productionLine) {
        this.productionLine = productionLine;
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (this) {
                    while (productionLine.getFilledSlots() == 0) wait();
                    productionLine.deleteBox();
                    productionLine.setProducedBoxes();

                    System.out.println("Consumer processed boxe from production line " + productionLine.getFilledSlots());
                    System.out.println("Total prduced boxes" + productionLine.getProducedBoxes());
                    notify();
                    Thread.sleep(1000);

                }
            }
        } catch (InterruptedException excep) {
            excep.printStackTrace();
        }
    }
}
