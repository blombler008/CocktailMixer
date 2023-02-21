package com.tattyhost.cocktail_mixer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tattyhost.cocktail_mixer.helper.ButtonAction;
import com.tattyhost.cocktail_mixer.helper.ViewState;

import java.lang.reflect.Array;

public class SettingsButton extends ButtonAction {
    private Button button;

    public SettingsButton(Button button) {
        this.button = button;
    }

    @Override
    public Button getButton() {
        return button;
    }

    @Override
    public void getOnClickListener(View view) {
        if(getActivity().getViewState() == ViewState.SETTINGS) {
            return;
        }
        getActivity().setViewState(ViewState.SETTINGS);

        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = layoutInflater.inflate(R.layout.settings_view, null);
        getBinding().contentView.removeAllViews();
        getBinding().contentView.addView(layout);
    }

    public void setSettingsView() {
    }
}
