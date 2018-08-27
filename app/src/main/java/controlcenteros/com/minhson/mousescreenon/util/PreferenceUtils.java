package controlcenteros.com.minhson.mousescreenon.util;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by binhnk on 6/15/2017.
 */

public class PreferenceUtils {

    public static void save(String key, boolean value, Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putBoolean(key, value).apply();
    }

    public static void save(String key, String value, Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putString(key, value).apply();
    }

    public static void save(String key, int value, Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putInt(key, value).apply();
    }

    public static boolean getBoolean(String key, Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(key, false);
    }

    public static String getString(String key, Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(key, "");
    }

    public static int getInt(String key, Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getInt(key, 0);
    }

    public static float getFloat(String key, Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getFloat(key, 0);
    }

}
