package com.tattyhost.cocktail_mixer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tattyhost.cocktail_mixer.helper.ButtonAction;
import com.tattyhost.cocktail_mixer.helper.ViewState;

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

        if(getActivity().getViewState() == ViewState.HOME) {
            return;
        }
        getActivity().setViewState(ViewState.HOME);

        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = layoutInflater.inflate(R.layout.home_view, null);
        getBinding().contentView.removeAllViews();
        getBinding().contentView.addView(layout);

    }

    public void setHomeView() {
    }
}
