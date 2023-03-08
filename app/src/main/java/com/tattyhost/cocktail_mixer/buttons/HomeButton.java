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
    private final Button button;
    private final ActivityMainBinding binding;
    private final HomeViewBinding homeBinding;
    public HomeButton(CocktailActivity cocktailActivity, Button homeButton) {
        this.button = homeButton;
        setActivity(cocktailActivity);
        this.binding = getActivity().getBinding();
        homeBinding = HomeViewBinding.inflate(getActivity().getLayoutInflater());
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
        if(getActivity().getViewState() == ViewState.HOME) {
            return this;
        }
        getActivity().setViewState(ViewState.HOME);

        binding.contentView.removeAllViews();
        binding.contentView.addView(homeBinding.getRoot());

        return this;
    }
}
