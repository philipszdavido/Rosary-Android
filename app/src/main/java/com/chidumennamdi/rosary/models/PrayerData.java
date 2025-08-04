package com.chidumennamdi.rosary.models;

public class PrayerData {

    static public Prayer constructPrayer(String title, String data) {
        return new Prayer(title, data);
    }

    static public Prayer[] allPrayers = {
            new Prayer("Sign Of the Cross", PrayerData.signOfTheCross),
            new Prayer("Our Father", PrayerData.ourFather),
            new Prayer("Hail Mary", PrayerData.hailMary),
    };

    static public String signOfTheCross = "In the name of the Father, and of the Son, and of the Holy Spirit. Amen.";
    static public String ourFather = "Our Father, who art in Heaven, hallowed be Thy name. Thy Kingdom come, Thy will be done, on earth, as it is in heaven. Give us this day our daily bread and forgive us our trespasses as we forgive those who trespass against us; and lead us not into temptation, but deliver us from evil. Amen.";
    static public String hailMary = "Hail Mary, full of grace,\n" +
            "the Lord is with you.\n" +
            "Blessed are you among women,\n" +
            "and blessed is the fruit of your womb, Jesus.\n" +
            "Holy Mary, Mother of God,\n" +
            "pray for us sinners,\n" +
            "now and at the hour of our death.\n" +
            "Amen.";
}
