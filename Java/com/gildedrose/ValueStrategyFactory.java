package com.gildedrose;

public class ValueStrategyFactory {
    static Item.ValueStrategy getStrategyByName(String name) {
        if (name.equals(GildedRose.BRIE)) {
            return new Item.ValueStrategy() {
                public void update(Item input) {
                    input.ageADay();
                    if (Item.isPassSellByDate(input)) {
                        input.incrementValue();
                    }
                    input.incrementValue();
                }
            };
        } else if (name.equals(GildedRose.SULFURAS)) {
            return new Item.ValueStrategy() {
                public void update(Item input) {

                }
            };
        } else if (name.equals(GildedRose.BACKSTAGE_PASS)) {
            return new Item.ValueStrategy() {
                public void update(Item input) {
                    input.ageADay();
                    input.incrementValue();
                    if (input.sellIn < 11) {
                        input.incrementValue();
                    }
                    if (input.sellIn < 6) {
                        input.incrementValue();
                    }
                    if (input.sellIn < 0) {
                        input.quality = 0;
                    }
                }
            };
        } else if (name.equals(GildedRose.CONJURED)) {
            return new Item.ValueStrategy() {
                public void update(Item input) {
                    input.ageADay();
                    input.decrementValue();
                    if (Item.isPassSellByDate(input)) {
                        input.decrementValue();
                    }
                }
            };
        } else {
            return new Item.ValueStrategy() {
                public void update(Item input) {
                    input.ageADay();
                    input.decrementValue();
                    if (Item.isPassSellByDate(input)) {
                        input.decrementValue();
                    }
                }
            };
        }
    }
}
