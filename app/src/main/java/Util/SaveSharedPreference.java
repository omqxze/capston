package Util;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ResourceBundle;

public class SaveSharedPreference {
    public static String getUserName(Context ctx) {
        return getSharedPreferences(ctx).getString("userId", "");
    }

    private static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }
}
