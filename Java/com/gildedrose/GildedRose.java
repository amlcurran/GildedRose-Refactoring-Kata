package com.gildedrose;

class GildedRose {
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    private static final String CONJURED = "Conjured";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            item.update();
        }
    }

    public static boolean isPassSellByDate(Item item) {
        return item.sellIn < 0;
    }

    public static void decrementValue(Item item) {
        if (item.quality > 0 && !item.name.equals(SULFURAS)) {
            int decrement = item.name.equals(CONJURED) ? 2 : 1;
            item.quality = item.quality - decrement;
        }
    }

    public static void incrementValue(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }
}
