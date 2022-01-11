package com.gildedrose;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {

    @Test
    public void simpleItem() {
        Item foo = new Item("foo", 20, 10);
        Item[] items = new Item[] { foo  };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(19,foo.sellIn);
        assertEquals(9, foo.quality);
    }


    @Test
    public void decreaseQuality_should_decrease_quality_of_normal_item() {
        Item foo = new Item("foo", 20, 10);
        Item[] items = new Item[] { foo  };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(19,foo.sellIn);
        assertEquals(9, foo.quality);
    }

    @Test
    public void increaseQuality_should_increase_quality_of_item() {
        Item foo = new Item("foo", 20, 10);
        Item[] items = new Item[]{foo};
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.increaseQuality(items[0], 1);
        assertEquals(11, foo.quality);
    }

    @Test
    public void decreaseSellIn_should_increase_sellIn_of_item() {
        Item foo = new Item("foo", 20, 10);
        Item[] items = new Item[] { foo  };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.decreaseSellIn(items[0],1);
        assertEquals(19, foo.sellIn);
    }

    @Test
    public void should_decrease_quality_and_selling_of_normal_item_by_one_in_daily_update() {
        Item foo = new Item("foo", 20, 10);
        Item[] items = new Item[] { foo  };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.itemDailyUpdate(items[0]);
        assertEquals(19, foo.sellIn);
        assertEquals(9, foo.quality);
    }

    @Test
    public void isAgedBrie_should_return_true_for_aged_brie() {
        Item agedBrie = new Item("Aged Brie", 20, 10);
        Item[] items = new Item[] { agedBrie  };
        GildedRose gildedRose = new GildedRose(items);
        assertTrue(gildedRose.isAgedBrie(agedBrie));
    }

    @Test
    public void isAgedBrie_should_return_false_for_foo_item() {
        Item foo = new Item("foo", 20, 10);
        Item[] items = new Item[] { foo  };
        GildedRose gildedRose = new GildedRose(items);
        assertFalse(gildedRose.isAgedBrie(foo));
    }

    @Test
    void isLegendary_should_return_true_for_legendary_item() {
        Item legendary = new Item("Sulfuras, Hand of Ragnaros", 20, 10);
        Item[] items = new Item[] { legendary  };
        GildedRose gildedRose = new GildedRose(items);
        assertTrue(gildedRose.isLegendary(legendary));
    }

    @Test
    public void isBackstagePasses_should_return_true_for_backstage_passes() {
        Item foo = new Item("Backstage passes to a TAFKAL80ETC concert", 20, 10);
        Item[] items = new Item[] { foo  };
        GildedRose gildedRose = new GildedRose(items);
        assertTrue(gildedRose.isBackstagePasses(foo));
    }

    @Test
    public void isBackstagePasses_should_return_false_for_legendary() {
        Item foo = new Item("Sulfuras, Hand of Ragnaros", 20, 10);
        Item[] items = new Item[] { foo  };
        GildedRose gildedRose = new GildedRose(items);
        assertFalse(gildedRose.isBackstagePasses(foo));
    }

    @Test
    public void isLegendary_should_return_false_for_normal_item() {
        Item foo = new Item("foo", 20, 10);
        Item[] items = new Item[] { foo  };
        GildedRose gildedRose = new GildedRose(items);
        assertFalse(gildedRose.isLegendary(foo));
    }

    @Test
    public void goldenMaster() throws IOException {
        Item[] items = new Item[] {
            new Item("+5 Dexterity Vest", 10, 20), //
            new Item("Aged Brie", 2, 0), //
            new Item("Elixir of the Mongoose", 5, 7), //
            new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            // this conjured item does not work properly yet
            new Item("Conjured Mana Cake", 3, 6) };

        GildedRose app = new GildedRose(items);

        int days = 30;
        List actualLog = new ArrayList<String>();
        for (int i = 0; i <= days; i++) {
            actualLog.add("-------- day " + i + " --------");
            actualLog.add("name, sellIn, quality");
            for (Item item : items) {
                actualLog.add(item.toString());
            }
            app.updateQuality();
        }

        List expectedLog = new ArrayList<String>();
        String workingDir = System.getProperty("user.dir");
        String expectedPath = workingDir + "/../golden-master/expected-output.txt";
        File expectedFile = new File(expectedPath);
        FileReader reader = new FileReader(expectedFile);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer stringBuffer = new StringBuffer();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if(! line.isEmpty()) {
                expectedLog.add(line);
            }
        }
        assertEquals(expectedLog, actualLog);
    }


}
