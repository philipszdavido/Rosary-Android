package com.chidumennamdi.rosary.utils;

import android.content.Context;
import android.graphics.Color;

import java.util.Random;

public class Utils {

    static public Utils shared = new Utils();
    private int[] colors = {
            Color.DKGRAY,
            Color.BLACK,
            Color.BLUE,
            Color.GREEN,
            Color.GRAY,
            Color.CYAN,
            Color.MAGENTA,
            Color.parseColor("#FF9800FF")
    };
    static public int dp(int paddingInDp, Context context) {

        float scale = context.getResources().getDisplayMetrics().density;
        int paddingInPx = (int) (paddingInDp * scale + 0.5f);

        return paddingInPx;

    }

    static public int randomColor() {

        Random random = new Random();
        return shared.colors[random.nextInt(shared.colors.length)];

    }

}
