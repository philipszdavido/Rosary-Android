package com.chidumennamdi.rosary.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    public final SharedPreferences prefs;

    public Prefs(Context context) {
        this.prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    public void saveVoice(String voiceName) {
        prefs.edit().putString("voice", voiceName).apply();
    }

    public String getSavedVoiceName() {
        return prefs.getString("voice", null);
    }

}

