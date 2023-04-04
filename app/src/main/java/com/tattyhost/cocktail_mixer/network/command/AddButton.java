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
        StringBuilder sb = new StringBuilder("");
        for (int i = 1; i < args.length; i++) {
            sb.append(args[i]);
            if(i != args.length-1)
                sb.append(' ');
        }

        mHandler.post(() -> {
            View v = menuCocktail.addItem(sb.toString(), (CocktailActivity) menuCocktail.getContext());
            getNetworkTask().sendMessage("but added " + v.getId() + " " +  args[0]);
            v.setOnClickListener((l) -> {
                if(uid.isUIDSet() || !uid.isRequired())
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
