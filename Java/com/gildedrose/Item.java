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

   @Override
   public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    public void update() {
        if (!name.equals(GildedRose.BRIE) && !name.equals(GildedRose.BACKSTAGE_PASS)) {
            GildedRose.decrementValue(this);
        } else {
            GildedRose.incrementValue(this);
            if (name.equals(GildedRose.BACKSTAGE_PASS)) {
                if (sellIn < 11) {
                    GildedRose.incrementValue(this);
                }

                if (sellIn < 6) {
                    GildedRose.incrementValue(this);
                }
            }
        }

        if (!name.equals(GildedRose.SULFURAS)) {
            sellIn = sellIn - 1;
        }

        if (GildedRose.isPassSellByDate(this)) {
            if (name.equals(GildedRose.BRIE)) {
                GildedRose.incrementValue(this);
            } else {
                if (name.equals(GildedRose.BACKSTAGE_PASS)) {
                    quality = 0;
                } else {
                    GildedRose.decrementValue(this);
                }
            }
        }
    }
}
