package com.tattyhost.cocktail_mixer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tattyhost.cocktail_mixer.databinding.ActivityMainBinding;
import com.tattyhost.cocktail_mixer.helper.ButtonAction;
import com.tattyhost.cocktail_mixer.helper.ViewState;

public class MenuButton extends ButtonAction {
    private Button button;

    public MenuButton(Button button) {
        this.button = button;
    }

    @Override
    public Button getButton() {
        return button;
    }

    @Override
    public void getOnClickListener(View view) {
        setMenuView();
    }

    public MenuButton setMenuView() {

        if(getActivity().getViewState() == ViewState.MENU) {
            return this;
        }
        getActivity().setViewState(ViewState.MENU);

        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = layoutInflater.inflate(R.layout.menu_view, null);
        getBinding().contentView.removeAllViews();
        getBinding().contentView.addView(layout);
        return this;
    }
}
