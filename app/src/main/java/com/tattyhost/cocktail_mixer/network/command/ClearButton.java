package com.tattyhost.cocktail_mixer.network.command;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.tattyhost.cocktail_mixer.CocktailActivity;
import com.tattyhost.cocktail_mixer.menu.MenuCocktail;
import com.tattyhost.cocktail_mixer.network.NetworkCMD;

public class ClearButton extends NetworkCMD {

    private final MenuCocktail menuCocktail;

    public ClearButton(MenuCocktail menuCocktail) {
        this.menuCocktail = menuCocktail;
    }

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void execute(String[] args) {
        String cmd = args[0];

        if(!cmd.equalsIgnoreCase("clear")) return;

        if(args.length == 1) {
            mHandler.post(menuCocktail::clear);
            return;
        }

        int view = toNumber(args[1]);
        mHandler.post(() -> {
            menuCocktail.clear(view);
        });
    }
    private int toNumber(String arg) {
        try {
            return Integer.valueOf(arg, 10);
        } catch (Exception ignore) {}
        return -1;
    }
}