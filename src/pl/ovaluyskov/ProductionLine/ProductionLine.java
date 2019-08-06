package pl.ovaluyskov.ProductionLine;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ProductionLine {

    private Integer filledSlots = 0;
    private Integer emptySlots = 5;
    private Integer capacity = 5;
    private boolean producing = Boolean.TRUE;
    private Integer producedBoxes = 0;


    public Integer getProducedBoxes() {
        return producedBoxes;
    }

    public void setProducedBoxes() {
        this.producedBoxes += 1;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void addBox() {
        this.filledSlots += 1;
        this.emptySlots -= 1;
    }

    public void deleteBox() {
        this.filledSlots -= 1;
        this.emptySlots += 1;
    }

    public Integer getFilledSlots() {
        return filledSlots;
    }

    public Integer getEmptySlots() {
        return emptySlots;
    }

    public void FinishProduction() {
        this.producing = false;
    }

    public boolean isProducing() {
        return producing;
    }
}
