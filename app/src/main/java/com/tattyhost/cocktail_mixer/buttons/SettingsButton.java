package com.tattyhost.cocktail_mixer.buttons;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.tattyhost.cocktail_mixer.CocktailActivity;
import com.tattyhost.cocktail_mixer.R;
import com.tattyhost.cocktail_mixer.databinding.ActivityMainBinding;
import com.tattyhost.cocktail_mixer.databinding.SettingsViewBinding;
import com.tattyhost.cocktail_mixer.helper.ButtonAction;
import com.tattyhost.cocktail_mixer.helper.ViewState;

import java.util.ArrayList;
import java.util.List;

public class SettingsButton extends ButtonAction {
    private Button button;
    private SettingsViewBinding settingsBinding;
    private ActivityMainBinding binding;
    private CocktailActivity activity;
    private List<ButtonAction> buttonActions = new ArrayList<>();
    private int buttons;
    public SettingsButton(CocktailActivity cocktailActivity, Button button) {
        this.button = button;
        this.activity = cocktailActivity;
        binding = activity.getBinding();
        settingsBinding = SettingsViewBinding.inflate(activity.getLayoutInflater());
        addCocktailItemButton();
        clearCocktailsButton();
        buttonActions.add(new ConnectESPButton(settingsBinding.connectESPButton, settingsBinding.espOutputText));
    }

    private void addCocktailItemButton() {
        ButtonAction action = new ButtonAction() {
            @Override
            public Button getButton() {
                return settingsBinding.addCocktailButton;
            }

            @Override
            public void getOnClickListener(View view) {
                buttons ++;
                String buttonText = "Button " + buttons;
                activity.getMenuButton().addButton(buttonText);
                Log.i("BUTTON", "getOnClickListener: ADDED BUTTON: " + buttonText);
             }
        };
        buttonActions.add(action);

    }

    private void clearCocktailsButton() {
        ButtonAction action = new ButtonAction() {
            @Override
            public Button getButton() {
                return settingsBinding.clearCocktailButton;
            }

            @Override
            public void getOnClickListener(View view) {
                activity.getMenuButton().getMenu().clear();
                buttons = 0;
            }
        };
        buttonActions.add(action);

    }

    @Override
    public Button getButton() {
        return button;
    }

    @Override
    public void getOnClickListener(View view) {
      setSettingsView();
    }

    public SettingsButton setSettingsView() {
        if(activity.getViewState() == ViewState.SETTINGS) {
            return this;
        }
        activity.setViewState(ViewState.SETTINGS);

        binding.contentView.removeAllViews();
        binding.contentView.addView(settingsBinding.getRoot());
        return this;
    }

    public List<ButtonAction> getButtons() {
        return buttonActions;
    }
}
