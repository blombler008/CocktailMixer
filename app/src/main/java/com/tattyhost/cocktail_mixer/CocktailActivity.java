package com.tattyhost.cocktail_mixer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;

import com.tattyhost.cocktail_mixer.databinding.ActivityMainBinding;
import com.tattyhost.cocktail_mixer.helper.WindowHelper;

import lombok.Getter;

public class CocktailActivity extends AppCompatActivity {

    private @Getter ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        WindowHelper.setFullscreen(getWindow());
        WindowHelper.setKeepScreenOn(getWindow());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        setContentView(binding.getRoot());
    }



}