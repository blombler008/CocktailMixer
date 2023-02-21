package com.tattyhost.cocktail_mixer.helper;

import android.widget.Button;

import com.tattyhost.cocktail_mixer.CocktailActivity;
import com.tattyhost.cocktail_mixer.databinding.ActivityMainBinding;

public class ButtonHelper {
    private ActivityMainBinding mainBinding;
    private CocktailActivity activity;

    public ButtonHelper(CocktailActivity activity) {
        this.activity = activity;
        this.mainBinding = activity.getBinding();
    }

    public void register(ButtonAction button) {
        Button b = button.getButton();
        button.setBinding(mainBinding);
        button.setActivity(activity);
        b.setOnClickListener(button::getOnClickListener);
    }
}
