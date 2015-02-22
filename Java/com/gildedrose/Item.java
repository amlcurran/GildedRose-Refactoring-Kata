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
                    input.ageADay();
                }
            };
        } else if (name.equals(GildedRose.SULFURAS)) {
            return new ValueStrategy() {
                public void update(Item input) {

                }
            };
        } else if (name.equals(GildedRose.BACKSTAGE_PASS)) {
            return new ValueStrategy() {
                public void update(Item input) {
                    input.incrementValue();
                    if (input.sellIn < 11) {
                        input.incrementValue();
                    }
                    if (input.sellIn < 6) {
                        input.incrementValue();
                    }
                    if (input.sellIn <= 0) {
                        input.quality = 0;
                    }
                    input.ageADay();
                }
            };
        } else if (name.equals(GildedRose.CONJURED)) {
            return new ValueStrategy() {
                public void update(Item input) {
                    input.ageADay();
                    input.decrementValue();
                    if (isPassSellByDate(input)) {
                        input.decrementValue();
                    }
                }
            };
        } else {
            return new ValueStrategy() {
                public void update(Item input) {
                    input.ageADay();
                    input.decrementValue();
                    if (isPassSellByDate(input)) {
                        input.decrementValue();
                    }
                }
            };
        }
    }

    private void ageADay() {
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
        valueStrategy.update(this);
    }

    private interface ValueStrategy {
        void update(Item input);
    }

}
