package com.gildedrose;

public class Item {

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
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
        if (!name.equals(GildedRose.BRIE) && !name.equals(GildedRose.BACKSTAGE_PASS)) {
            decrementValue();
        } else {
            incrementValue();
            if (name.equals(GildedRose.BACKSTAGE_PASS)) {
                if (sellIn < 11) {
                    incrementValue();
                }

                if (sellIn < 6) {
                    incrementValue();
                }
            }
        }

        if (!name.equals(GildedRose.SULFURAS)) {
            sellIn = sellIn - 1;
        }

        if (isPassSellByDate(this)) {
            if (name.equals(GildedRose.BRIE)) {
                incrementValue();
            } else {
                if (name.equals(GildedRose.BACKSTAGE_PASS)) {
                    quality = 0;
                } else {
                    decrementValue();
                }
            }
        }
    }

}
