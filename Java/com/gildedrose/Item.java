package com.gildedrose;

public class Item {

    private final Strategy valueStrategy;
    private final Strategy sellInStrategy;
    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        this.valueStrategy = ValueStrategyFactory.getByName(name);
        this.sellInStrategy = SellInStrategyFactory.getByName(name);
    }

    public void ageADay() {
        sellIn = sellIn - 1;
    }

    public static boolean isPassSellByDate(Item item) {
        return item.sellIn < 0;
    }

    public void decrementValue() {
        if (quality > 0 && !name.equals(GildedRose.SULFURAS)) {
            int decrement = name.equals(GildedRose.CONJURED) ? 2 : 1;
            quality = quality - decrement;
        }
    }

    public void incrementValue() {
        if (quality < 50) {
            quality = quality + 1;
        }
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
