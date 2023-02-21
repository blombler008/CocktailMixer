package com.tattyhost.cocktail_mixer;

import android.view.View;
import android.widget.Button;

import com.tattyhost.cocktail_mixer.helper.ButtonAction;

public class HomeButton extends ButtonAction {
    private Button button;

    public HomeButton(Button homeButton) {
        this.button = homeButton;
    }

    @Override
    public Button getButton() {
        return button;
    }

    @Override
    public void getOnClickListener(View view) {

    }

    public void setHomeView() {
    }
}
