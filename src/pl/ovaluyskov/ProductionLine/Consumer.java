package pl.ovaluyskov.ProductionLine;

public class Consumer implements Runnable {

    private final ProductionLine productionLine;
    private String name;

    public Consumer(ProductionLine productionLine, String name) {
        this.productionLine = productionLine;
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void consume () throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (productionLine.getFilledSlots() == 0) {
                    System.out.println(getName() + ": Line is empty");
                    wait();
                }
                productionLine.deleteBox();


                System.out.println(getName() + " empty slots " + productionLine.getEmptySlots()
                        + " filled slots " + productionLine.getFilledSlots());
                //System.out.println("Total produced boxes " + productionLine.getProducedBoxes());
                notify();
                Thread.sleep(1000);

            }
        }

    }

    @Override
    public void run() {
        try {
            consume();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
