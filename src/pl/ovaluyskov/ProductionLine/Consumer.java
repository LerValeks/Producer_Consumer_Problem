package pl.ovaluyskov.ProductionLine;

public class Consumer implements Runnable{

    private final ProductionLine productionLine;

    public Consumer(ProductionLine productionLine) {
        this.productionLine= productionLine;
    }

    @Override
    public void run() {
        try {
            Integer data = productionLine.getNum();

            while(productionLine.isProducing()|| data>0){
                Thread.sleep(1000);
                System.out.println("Consumer processed data from broker " +data);
                data = productionLine.getNum();;
                productionLine.add();

            }
        } catch (InterruptedException excep) {
            excep.printStackTrace();
        }
    }
}
