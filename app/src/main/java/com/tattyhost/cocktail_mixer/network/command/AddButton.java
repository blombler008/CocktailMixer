package com.tattyhost.cocktail_mixer.network.command;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.tattyhost.cocktail_mixer.CocktailActivity;
import com.tattyhost.cocktail_mixer.menu.MenuCocktail;
import com.tattyhost.cocktail_mixer.network.NetworkCMD;

public class AddButton extends NetworkCMD {

    private final MenuCocktail menuCocktail;
    private final UID uid;

    public AddButton(MenuCocktail menuCocktail, UID uid) {
        this.uid = uid;
        this.menuCocktail = menuCocktail;
    }

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void execute(String[] args) {
        mHandler.post(() -> {
            View v = menuCocktail.addItem(args[0], (CocktailActivity) menuCocktail.getContext());
            getNetworkTask().sendMessage("but added " + v.getId() + " " +  args[1]);
            v.setOnClickListener((l) -> {
                if(uid.isUIDSet())
                    getNetworkTask().sendMessage("but " + v.getId());
            });
        });
    }

    private int toNumber(String arg) {
        try {
            return Integer.valueOf(arg, 10);
        } catch (Exception ignore) {}
        return -1;
    }
}
