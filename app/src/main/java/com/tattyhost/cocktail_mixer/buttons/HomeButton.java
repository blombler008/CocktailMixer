package com.tattyhost.cocktail_mixer.buttons;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.tattyhost.cocktail_mixer.R;
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
        setHomeView();
    }

    public HomeButton setHomeView() {
        if(getActivity().getViewState() == ViewState.HOME) {
            return this;
        }
        getActivity().setViewState(ViewState.HOME);

        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = layoutInflater.inflate(R.layout.home_view, null);
        getBinding().contentView.removeAllViews();
        getBinding().contentView.addView(layout);

        return this;
    }
}
