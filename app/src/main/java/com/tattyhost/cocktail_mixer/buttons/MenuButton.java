package com.tattyhost.cocktail_mixer.buttons;

import android.view.View;
import android.widget.Button;

import com.tattyhost.cocktail_mixer.CocktailActivity;
import com.tattyhost.cocktail_mixer.R;
import com.tattyhost.cocktail_mixer.helper.ButtonAction;
import com.tattyhost.cocktail_mixer.helper.ViewState;
import com.tattyhost.cocktail_mixer.menu.MenuCocktail;

public class MenuButton extends ButtonAction {
    private Button button;
    private MenuCocktail menu;

    public MenuButton(CocktailActivity cocktailActivity, Button button) {
        this.button = button;
        this.setActivity(cocktailActivity);
        menu = new MenuCocktail(getActivity(),4);
        for (int i = 0; i < 15; i++) {

            menu.addItem(R.layout.menu_cocktail_item, getActivity());
        }

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

        menu.apply();
        getBinding().contentView.removeAllViews();
        getBinding().contentView.addView(menu);
        return this;
    }
}
