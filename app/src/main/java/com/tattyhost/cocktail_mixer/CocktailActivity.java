package com.tattyhost.cocktail_mixer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.tattyhost.cocktail_mixer.buttons.HomeButton;
import com.tattyhost.cocktail_mixer.buttons.MenuButton;
import com.tattyhost.cocktail_mixer.buttons.SettingsButton;
import com.tattyhost.cocktail_mixer.databinding.ActivityMainBinding;
import com.tattyhost.cocktail_mixer.helper.ButtonAction;
import com.tattyhost.cocktail_mixer.helper.ButtonHelper;
import com.tattyhost.cocktail_mixer.helper.ViewState;
import com.tattyhost.cocktail_mixer.helper.WindowHelper;
import com.tattyhost.cocktail_mixer.menu.MenuCocktail;

import lombok.Getter;
import lombok.Setter;

public class CocktailActivity extends AppCompatActivity {
    @Getter
    private ActivityMainBinding binding;
    @Setter @Getter
    private ViewState viewState = ViewState.NONE;
    @Getter
    private ButtonHelper buttonHelper;

    @Getter
    private SettingsButton settingsButton;
    @Getter
    private MenuButton menuButton;
    @Getter
    private HomeButton homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        initButtons();

        buttonHelper = new ButtonHelper(this, binding);

        buttonHelper.register(homeButton);
        buttonHelper.register(menuButton);
        buttonHelper.register(settingsButton);

        for(ButtonAction action: settingsButton.getButtons()) {
            buttonHelper.register(action);
        }

        WindowHelper.setFullscreen(getWindow());
        WindowHelper.setKeepScreenOn(getWindow());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        setContentView(binding.getRoot());
        homeButton.setHomeView();
    }

    @Override
    public void onBackPressed() {
        homeButton.setHomeView();
    }

    private void initButtons() {
        homeButton =  new HomeButton(this, binding.homeButton);
        menuButton = new MenuButton(this, binding.menuButton);
        settingsButton = new SettingsButton(this, binding.settingsButton);
    }

    public MenuCocktail getMenu() {
        return menuButton.getMenu();
    }
}