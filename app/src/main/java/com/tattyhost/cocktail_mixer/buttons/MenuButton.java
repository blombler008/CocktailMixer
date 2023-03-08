package com.tattyhost.cocktail_mixer.buttons;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tattyhost.cocktail_mixer.CocktailActivity;
import com.tattyhost.cocktail_mixer.R;
import com.tattyhost.cocktail_mixer.databinding.ActivityMainBinding;
import com.tattyhost.cocktail_mixer.helper.ButtonAction;
import com.tattyhost.cocktail_mixer.helper.ViewState;
import com.tattyhost.cocktail_mixer.menu.MenuCocktail;

import lombok.Getter;

public class MenuButton extends ButtonAction {
    private final Button button;
    @Getter
    private final MenuCocktail menu;
    private final CocktailActivity activity;
    private final ActivityMainBinding binding;

    public MenuButton(CocktailActivity cocktailActivity, Button button) {
        this.button = button;
        this.activity = cocktailActivity;
        this.binding = activity.getBinding();

        menu = new MenuCocktail(activity,4);
    }

    @Override
    public Button getButton() {
        return button;
    }

    public void addButton(String text) {
        Log.i("BUTTON", "addButton: add: " + text);
        menu.addItem(text, activity);
    }

    @Override
    public void getOnClickListener(View view) {
        setMenuView();
    }

    public MenuButton setMenuView() {

        if(activity.getViewState() == ViewState.MENU) {
            return this;
        }
        activity.setViewState(ViewState.MENU);

        menu.apply();
        binding.contentView.removeAllViews();
        binding.contentView.addView(menu);
        return this;
    }
}
