package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }


    /*
     /!\ Do not change code above this line /!\
     */

    private static final int MAX_QUALITY = 50;
    private static final int MIN_QUALITY = 0;

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            itemDailyUpdate(items[i]);

            if (isExpired(items[i])) {
                expiredItemDailyUpdate(items[i]);
            }
        }
    }

    private boolean isExpired(Item item) {
        return item.sellIn < 0;
    }

    public void expiredItemDailyUpdate(Item item){
        if (!isAgedBrie(item)) {
            if (!isBackstagePasses(item)) {
                itemQualityUpdate(item);
            } else {
                item.quality = 0;
            }
        } else {
            if (item.quality < MAX_QUALITY) {
                increaseQuality(item, 1);
            }
        }
    }

    public void itemDailyUpdate(Item item) {
        if (isNormalItem(item)) {
            itemQualityUpdate(item);
        } else {
            if (item.quality < MAX_QUALITY) {
                increaseQuality(item, 1);
                if (isBackstagePasses(item)) {
                    updateQualityForItemSellInLessThan10Days(item);
                    updateQualityForItemSellInLessThan5Days(item);
                }
            }
        }

        normalItemSellInDailyUpdate(item);
    }

    private void updateQualityForItemSellInLessThan5Days(Item item) {
        if (item.sellIn < 6) {
            if (item.quality < 50) {
                increaseQuality(item, 1);
            }
        }
    }

    private void updateQualityForItemSellInLessThan10Days(Item item) {
        if (item.sellIn < 11) {
            if (item.quality < 50) {
                increaseQuality(item, 1);
            }
        }
    }

    private void normalItemSellInDailyUpdate(Item item) {
        if (!isLegendary(item)) {
            decreaseSellIn(item, 1);
        }
    }

    private void itemQualityUpdate(Item item) {
        if (item.quality > MIN_QUALITY) {
            if (!isLegendary(item)) {
                decreaseQuality(item, 1);
            }
        }
    }

    private boolean isNormalItem(Item item) {
        return !isAgedBrie(item)
            && !isBackstagePasses(item);
    }

    public void decreaseSellIn(Item item, int i) {
        item.sellIn -= i;
    }

    public void decreaseQuality(Item item, int i) {
        item.quality = item.quality - i;
    }

    public void increaseQuality(Item item, int i) {
        item.quality = item.quality + i;
    }

    public boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    public boolean isLegendary(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    public boolean isBackstagePasses(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

}
