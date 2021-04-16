package Util;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

public class HideKeyBoard {
    public void hideKeyboard(Context c, Activity a)
    {
        InputMethodManager inputManager = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(a.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
