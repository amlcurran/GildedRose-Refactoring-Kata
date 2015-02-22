package com.gildedrose;

public class SellInStrategyFactory {
    public static Item.Strategy getByName(String name) {
        if (name.equals(GildedRose.SULFURAS)) {
            return new DontAgeStrategy();
        } else {
            return new AgeStrategy();
        }
    }

    private static class DontAgeStrategy implements Item.Strategy {
        public void update(Item input) {

        }
    }

    private static class AgeStrategy implements Item.Strategy {
        public void update(Item input) {
            input.ageADay();
        }
    }
}
