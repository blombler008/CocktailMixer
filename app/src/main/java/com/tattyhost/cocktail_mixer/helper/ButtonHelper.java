package com.tattyhost.cocktail_mixer.helper;


import android.util.Log;
import android.widget.Button;

import androidx.viewbinding.ViewBinding;

import com.tattyhost.cocktail_mixer.CocktailActivity;
import com.tattyhost.cocktail_mixer.databinding.ActivityMainBinding;

public class ButtonHelper {

    private static final String TAG = "BUTTON REGISTER";
    private ViewBinding binding;
    private CocktailActivity activity;

    public ButtonHelper(CocktailActivity activity, ViewBinding binding) {
        this.activity = activity;
        this.binding = binding;
    }

    public void register(ButtonAction button) {
        Button b = button.getButton();
        Log.i(TAG, "register: " + b.toString());
        button.getButton().setOnClickListener(button::getOnClickListener);
    }
}
