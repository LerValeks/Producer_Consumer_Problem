package pl.ovaluyskov.ProductionLine;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ProductionLine {

    private Integer numberOfBoxOnProductionLine = 0;
    final private Integer maxNumberOfBoxOnProductionLine = 16;
    private boolean producing = Boolean.TRUE;
    public Integer numberOfConsumedBoxes = 0;


    public void add() {
        this.numberOfBoxOnProductionLine += 1;
    }

    public Integer getNumberOfConsumedBoxes() {
        return numberOfConsumedBoxes;
    }

    public void setNumberOfConsumedBoxes() {
        this.numberOfConsumedBoxes = numberOfConsumedBoxes + 1;
    }

    public void remove() {
        this.numberOfBoxOnProductionLine -= 1;
    }

    public Integer getNumberOfBoxOnProductionLine() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
        return numberOfBoxOnProductionLine;
    }

    public void setProducing() {
        this.producing = false;
    }

    public boolean isProducing() {
        return producing;
    }

    public Integer getMaxNumberOfBoxOnProductionLine() {
        return maxNumberOfBoxOnProductionLine;
    }
}
