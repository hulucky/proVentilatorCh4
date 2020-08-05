package com.ventilator.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefrenceUtils {
    public static String SP_NAME = "TFJ_SAVE";

    public static void setCanShowEight(Context context, boolean canShowEight) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("canSave", canShowEight);
        edit.commit();
    }

    public static boolean getCanShowEight(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean("canSave", false);
    }

    public static void setCanShowCh4(Context context, boolean canShowEight) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("canShow", canShowEight);
        edit.commit();
    }

    public static boolean getCanShowCh4(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean("canShow", false);
    }

    public static void clearCanShowEight(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.remove("canSave");
        edit.commit();

    }
}