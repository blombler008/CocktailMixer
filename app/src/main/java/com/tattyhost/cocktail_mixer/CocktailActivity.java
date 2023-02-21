package com.tattyhost.cocktail_mixer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.tattyhost.cocktail_mixer.databinding.ActivityMainBinding;

import java.util.Objects;

import lombok.Getter;

public class CocktailActivity extends AppCompatActivity {

    private @Getter ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        this.binding = binding;
        setContentView(binding.getRoot());
    }
}