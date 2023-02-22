package com.tattyhost.cocktail_mixer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.tattyhost.cocktail_mixer.buttons.HomeButton;
import com.tattyhost.cocktail_mixer.buttons.MenuButton;
import com.tattyhost.cocktail_mixer.buttons.SettingsButton;
import com.tattyhost.cocktail_mixer.databinding.ActivityMainBinding;
import com.tattyhost.cocktail_mixer.helper.ButtonHelper;
import com.tattyhost.cocktail_mixer.helper.ViewState;
import com.tattyhost.cocktail_mixer.helper.WindowHelper;

import lombok.Getter;
import lombok.Setter;

public class CocktailActivity extends AppCompatActivity {
    @Getter
    private ActivityMainBinding binding;
    @Setter @Getter
    private ViewState viewState = ViewState.NONE;
    @Getter
    private ButtonHelper buttonHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        buttonHelper = new ButtonHelper(this);
        HomeButton defaultButton = getHomeButton();
        buttonHelper.register(defaultButton);
        buttonHelper.register(getSettingsButton());
        buttonHelper.register(getMenuButton());
        WindowHelper.setFullscreen(getWindow());
        WindowHelper.setKeepScreenOn(getWindow());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        setContentView(binding.getRoot());
        defaultButton.setHomeView();
    }

    private HomeButton getHomeButton() {
        return new HomeButton(binding.homeButton);
    }

    private SettingsButton getSettingsButton() {
        return new SettingsButton(binding.settingsButton);
    }

    private MenuButton getMenuButton() {
        return new MenuButton(this, binding.menuButton);
    }



}