package pl.ovaluyskov.ProductionLine;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ProductionLine {

    private Integer num=0;
    private boolean producing  = Boolean.TRUE;


    public void add() {
        this.num += 1;
    }

    public Integer getNum() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
        return num;
    }

    public void setProducing() {
        this.producing = false;
    }

    public boolean isProducing() {
        return producing;
    }
}
