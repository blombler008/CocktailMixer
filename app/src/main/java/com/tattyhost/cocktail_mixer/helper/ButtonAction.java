package com.tattyhost.cocktail_mixer.helper;

import android.view.View;
import android.widget.Button;

import androidx.viewbinding.ViewBinding;

import com.tattyhost.cocktail_mixer.CocktailActivity;
import com.tattyhost.cocktail_mixer.databinding.ActivityMainBinding;

import lombok.Getter;
import lombok.Setter;

public abstract class ButtonAction {

    public abstract Button getButton();

    public abstract void getOnClickListener(View view);

}
