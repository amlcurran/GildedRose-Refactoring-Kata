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
            if (!item.name.equals(BRIE) && !item.name.equals(BACKSTAGE_PASS)) {
                decrementValue(item);
            } else {
                incrementValue(item);
                if (item.name.equals(BACKSTAGE_PASS)) {
                    if (item.sellIn < 11) {
                        incrementValue(item);
                    }

                    if (item.sellIn < 6) {
                        incrementValue(item);
                    }
                }
            }

            if (!item.name.equals(SULFURAS)) {
                item.sellIn = item.sellIn - 1;
            }

            if (isPassSellByDate(item)) {
                if (item.name.equals(BRIE)) {
                    incrementValue(item);
                } else {
                    if (item.name.equals(BACKSTAGE_PASS)) {
                        item.quality = 0;
                    } else {
                        decrementValue(item);
                    }
                }
            }
        }
    }

    private static boolean isPassSellByDate(Item item) {
        return item.sellIn < 0;
    }

    private void decrementValue(Item item) {
        if (item.quality > 0 && !item.name.equals(SULFURAS)) {
            int decrement = item.name.equals(CONJURED) ? 2 : 1;
            item.quality = item.quality - decrement;
        }
    }

    private void incrementValue(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }
}
