package com.tattyhost.cocktail_mixer.helper;

import android.view.Window;
import android.view.WindowManager;

public class WindowHelper {

    public static void setFullscreen(Window window) {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static void setKeepScreenOn(Window window) {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }
}
