package com.example.basic.utils;

import android.app.Activity;
import android.content.Intent;

import com.example.basic.R;

public class ThemeUtils{
    private static int cTheme=2;
    public final static int GREEN_WA = 0;
    public final static int SOFT = 1;
    public final static int BLUE = 2;

    public static void changeToTheme(Activity activity, int theme){
        cTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }
    public static void onActivityCreateSetTheme(Activity activity){
        switch (cTheme)
        {
            default:
            case GREEN_WA:
                activity.setTheme(R.style.ThemeWA);
                break;
            case SOFT:
                activity.setTheme(R.style.ThemeDark);
                break;
            case BLUE:
                activity.setTheme(R.style.ThemeLight);
                break;
        }
    }
}