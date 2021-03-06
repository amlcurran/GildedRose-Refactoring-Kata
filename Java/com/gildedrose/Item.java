package com.gildedrose;

public class Item {

    private final Strategy valueStrategy;
    private final Strategy sellInStrategy;
    private String name;
    public int sellIn;
    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        this.valueStrategy = ValueStrategyFactory.getByName(name);
        this.sellInStrategy = SellInStrategyFactory.getByName(name);
    }

    public boolean isPassSellByDate() {
        return sellIn < 0;
    }

    public void ageADay() {
        sellIn = sellIn - 1;
    }

    public void decrementValue() {
        quality = Math.max(0, quality - 1);
    }

    public void incrementValue() {
        quality = Math.min(50, quality + 1);
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    public void update() {
        sellInStrategy.update(this);
        valueStrategy.update(this);
    }

    public interface Strategy {
        void update(Item input);
    }

}
