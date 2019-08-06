package pl.ovaluyskov.ProductionLine;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ProductionLine {

    private Integer filledSlots;
    private Integer emptySlots;
    private Integer capacity;
    private Integer producedBoxes;
    private Integer toBeProduced;

    public ProductionLine(Integer filledSlots, Integer emptySlots, Integer capacity,
                           Integer producedBoxes, Integer toBeProduced) {
        this.emptySlots = emptySlots;
        this.filledSlots = filledSlots;
        this.capacity = capacity;
        this.producedBoxes = producedBoxes;
        this.toBeProduced = toBeProduced;
    }

    public Integer getToBeProduced() {
        return toBeProduced;
    }

    public  Integer getProducedBoxes() {
        return producedBoxes;
    }

    public  synchronized void setProducedBoxes() {
        this.producedBoxes += 1;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public  synchronized void addBox() {
        this.filledSlots += 1;
        this.emptySlots -= 1;
    }

    public synchronized void deleteBox() {
        this.filledSlots -= 1;
        this.emptySlots += 1;
    }

    public  Integer getFilledSlots() {
        return filledSlots;
    }

    public  Integer getEmptySlots() {
        return emptySlots;
    }

}
