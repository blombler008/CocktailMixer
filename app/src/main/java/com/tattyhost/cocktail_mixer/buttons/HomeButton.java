package com.tattyhost.cocktail_mixer.buttons;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.tattyhost.cocktail_mixer.CocktailActivity;
import com.tattyhost.cocktail_mixer.R;
import com.tattyhost.cocktail_mixer.databinding.ActivityMainBinding;
import com.tattyhost.cocktail_mixer.databinding.HomeViewBinding;
import com.tattyhost.cocktail_mixer.helper.ButtonAction;
import com.tattyhost.cocktail_mixer.helper.ViewState;

public class HomeButton extends ButtonAction {
    private Button button;
    private CocktailActivity activity;
    private ActivityMainBinding binding;
    private HomeViewBinding homeBinding;
    public HomeButton(CocktailActivity cocktailActivity, Button homeButton) {
        this.button = homeButton;
        this.activity = cocktailActivity;
        this.binding = activity.getBinding();
        homeBinding = HomeViewBinding.inflate(activity.getLayoutInflater());
    }

    @Override
    public Button getButton() {
        return button;
    }

    @Override
    public void getOnClickListener(View view) {
        setHomeView();
    }

    public HomeButton setHomeView() {
        if(activity.getViewState() == ViewState.HOME) {
            return this;
        }
        activity.setViewState(ViewState.HOME);

        binding.contentView.removeAllViews();
        binding.contentView.addView(homeBinding.getRoot());

        return this;
    }
}
