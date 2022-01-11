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

            if (items[i].sellIn < 0) {
                if (!isAgedBrie(items[i])) {
                    if (!isBackstagePasses(items[i])) {
                        if (items[i].quality > MIN_QUALITY) {
                            if (!isLegendary(items[i])) {
                                decreaseQuality(items[i], 1);
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    if (items[i].quality < MAX_QUALITY) {
                        increaseQuality(items[i], 1);
                    }
                }
            }
        }
    }

    public void itemDailyUpdate(Item item) {
        if (isNormalItem(item)) {
            normalItemQualityDailyUpdate(item);
        } else {
            if (item.quality < MAX_QUALITY) {
                increaseQuality(item, 1);

                if (isBackstagePasses(item)) {
                    if (item.sellIn < 11) {
                        if (item.quality < 50) {
                            increaseQuality(item, 1);
                        }
                    }

                    if (item.sellIn < 6) {
                        if (item.quality < 50) {
                            increaseQuality(item, 1);
                        }
                    }
                }
            }
        }

        normalItemSellInDailyUpdate(item);
    }

    private void normalItemSellInDailyUpdate(Item item) {
        if (!isLegendary(item)) {
            decreaseSellIn(item, 1);
        }
    }

    private void normalItemQualityDailyUpdate(Item item) {
        if (item.quality > MIN_QUALITY) {
            decreaseQuality(item, 1);
        }
    }

    private boolean isNormalItem(Item item) {
        return !isAgedBrie(item)
            && !isBackstagePasses(item)
            && !isLegendary(item);
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
