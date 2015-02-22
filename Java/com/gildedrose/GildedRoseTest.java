package com.gildedrose;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GildedRoseTest {

    @Test
    public void oncePastTheSellByDate_QualityDegradesTwiceAsFast() {
        int startingQuality = 10;
        Item normalItem = new Item("Cheese", 1, startingQuality);

        GildedRose gildedRose = new GildedRose(new Item[]{normalItem});
        gildedRose.updateQuality();

        assertThat(normalItem.quality, is(startingQuality - 1));

        gildedRose.updateQuality();

        assertThat(normalItem.quality, is(startingQuality - 3));
    }

    @Test
    public void theQualityOfAnItem_IsNeverNegative() {
        int startingQuality = 1;
        Item normalItem = new Item("Cheese", 4, startingQuality);

        GildedRose gildedRose = new GildedRose(new Item[]{normalItem});
        gildedRose.updateQuality();
        gildedRose.updateQuality();

        assertThat(normalItem.quality, is(0));
    }

    @Test
    public void agedBrieIncreasesInQualityWithTime() {
        int startingQuality = 1;
        Item normalItem = new Item("Aged Brie", 4, startingQuality);

        GildedRose gildedRose = new GildedRose(new Item[]{normalItem});
        gildedRose.updateQuality();

        assertThat(normalItem.quality, is(startingQuality + 1));
    }

    @Test
    public void theQualityOfAnItem_IsNeverAbove50() {
        int startingQuality = 49;
        Item normalItem = new Item("Aged Brie", 4, startingQuality);

        GildedRose gildedRose = new GildedRose(new Item[]{normalItem});
        gildedRose.updateQuality();
        gildedRose.updateQuality();

        assertThat(normalItem.quality, is(50));
    }

}
