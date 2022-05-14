package com.example.thriftandthrive.utils;

import android.app.Activity;
import android.view.View;

import com.example.thriftandthrive.R;

public class UserInterfaceUtils {


    public static void statusBarIcons(Boolean isDark, Activity activity) {
        if (isDark)
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        else
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

    }

    public static void changeStatusBarColor(Activity activity, boolean primary) {
        if (primary)
            activity.getWindow().setStatusBarColor(activity.getResources().getColor(R.color.ttcolor));
        else
            activity.getWindow().setStatusBarColor(activity.getResources().getColor(R.color.white));

    }
}
