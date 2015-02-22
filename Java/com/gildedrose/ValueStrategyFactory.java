package com.gildedrose;

public class ValueStrategyFactory {
    static Item.Strategy getByName(String name) {
        if (name.equals(GildedRose.BRIE)) {
            return new Item.Strategy() {
                public void update(Item input) {
                    if (input.isPassSellByDate()) {
                        input.incrementValue();
                    }
                    input.incrementValue();
                }
            };
        } else if (name.equals(GildedRose.SULFURAS)) {
            return new Item.Strategy() {
                public void update(Item input) {

                }
            };
        } else if (name.equals(GildedRose.BACKSTAGE_PASS)) {
            return new Item.Strategy() {
                public void update(Item input) {
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
            return new Item.Strategy() {
                public void update(Item input) {
                    input.decrementValue();
                    input.decrementValue();
                    if (input.isPassSellByDate()) {
                        input.decrementValue();
                        input.decrementValue();
                    }
                }
            };
        } else {
            return new Item.Strategy() {
                public void update(Item input) {
                    input.decrementValue();
                    if (input.isPassSellByDate()) {
                        input.decrementValue();
                    }
                }
            };
        }
    }
}
