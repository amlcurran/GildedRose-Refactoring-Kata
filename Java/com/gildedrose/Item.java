package com.gildedrose;

public class Item {

    private final ValueStrategy valueStrategy;
    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        this.valueStrategy = getStrategyByName(name);
    }

    private static ValueStrategy getStrategyByName(String name) {
        if (name.equals(GildedRose.BRIE)) {
            return new ValueStrategy() {
                public void update(Item input) {
                    if (isPassSellByDate(input)) {
                        input.incrementValue();
                    }
                    input.incrementValue();
                }
            };
        } else if (name.equals(GildedRose.SULFURAS)) {
            return new ValueStrategy() {
                public void update(Item input) {

                }
            };
        }
        return null;
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
        if (valueStrategy != null) {
            valueStrategy.update(this);
        } else {
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

    private interface ValueStrategy {
        void update(Item input);
    }

}
