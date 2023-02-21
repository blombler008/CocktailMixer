package com.tattyhost.cocktail_mixer.helper;

import android.view.View;
import android.widget.Button;

public abstract class ButtonAction {
    public abstract Button getButton();
    public abstract void getOnClickListener(View view);
}
