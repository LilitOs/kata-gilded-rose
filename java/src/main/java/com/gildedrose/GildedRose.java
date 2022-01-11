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
            if (!isAgedBrie(items[i])
                    && !isBackstagePasses(items[i])) {
                if (items[i].quality > MIN_QUALITY) {
                    if (!isLegendary(items[i])) {
                        decreaseQuality(items[i],1);
                    }
                }
            } else {
                if (items[i].quality < MAX_QUALITY) {
                    increaseQuality(items[i], 1);

                    if (isBackstagePasses(items[i])) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                increaseQuality(items[i], 1);
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                increaseQuality(items[i], 1);
                            }
                        }
                    }
                }
            }

            if (!isLegendary(items[i])) {
                decreaseSellIn(items[i], 1);
            }

            if (items[i].sellIn < 0) {
                if (!isAgedBrie(items[i])) {
                    if (!isBackstagePasses(items[i])) {
                        if (items[i].quality > MIN_QUALITY) {
                            if (!isLegendary(items[i])) {
                                decreaseQuality(items[i],1);
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

    public void decreaseSellIn(Item item, int i){
        item.sellIn -= i;
    }

    public void decreaseQuality(Item item, int i) {
        item.quality = item.quality - i;
    }

    public void increaseQuality(Item item, int i){
        item.quality = item.quality + i;
    }

    public boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    public boolean isLegendary(Item item){
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    public boolean isBackstagePasses(Item item){
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

}
