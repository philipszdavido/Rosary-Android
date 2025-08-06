package com.chidumennamdi.rosary.models;

public class PrayerData {

    static public Prayer constructPrayer(String title, String content) {
        return new Prayer(title, content);
    }

    static public Prayer[] allPrayers = {
            new Prayer("Sign Of the Cross", Prayers.signOfTheCross),
            new Prayer("Our Father", Prayers.ourFather),
            new Prayer("Hail Mary", Prayers.hailMary),
    };

    static public Prayer[] quickPrayers = {
            new Prayer("Act of Contrition", Prayers.ACT_OF_CONTRITION),
            new Prayer("Dedication of the Day", Prayers.DEDICATION_OF_THE_DAY)
    };

}
