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

}
