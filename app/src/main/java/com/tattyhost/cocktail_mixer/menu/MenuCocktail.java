package com.tattyhost.cocktail_mixer.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.tattyhost.cocktail_mixer.CocktailActivity;
import com.tattyhost.cocktail_mixer.R;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class MenuCocktail extends ConstraintLayout {
    private ConstraintSet constraintSet = new ConstraintSet();
    private int rows;
    private int cols;
    private List<View> itemList = new ArrayList<>();
    @Getter @Setter
    private int margin = 8;
    private boolean constraintsSet = false;
    public MenuCocktail(@NonNull Context context, int rows) {
        super(context);
        this.rows = rows;
        setId(View.generateViewId());
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setBackgroundColor(getResources().getColor(R.color.transparent, context.getTheme()));
        constraintSet.clone(this);
    }

    public void addItem(int item, CocktailActivity activity) {

        LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = layoutInflater.inflate(R.layout.menu_cocktail_item, null);

        layout.setId(View.generateViewId());
        addView(layout);
        itemList.add(layout);
    }

    public MenuCocktail apply() {
        if(constraintsSet) return this;
        setConstraints();
        constraintsSet = true;
        constraintSet.applyTo(this);
        return this;
    }

    private void setConstraints() {
        cols = getColumns(itemList.size());
        for(int i = 0; i < itemList.size(); i ++) {
            View item = itemList.get(i);
            int viewId = item.getId();

            setDimensions(viewId, constraintSet);

            setFirstItemProperties(constraintSet, viewId, i);

            setLastItemProperties(constraintSet, viewId, i);

            setFirstOfRowItemProperties(constraintSet, viewId, i);

            setInterItemProperties(constraintSet, viewId, i);

            setLastOfRowItemProperties(constraintSet, viewId, i);
        }
    }

    private void setLastOfRowItemProperties(ConstraintSet constraintSet, int viewId, int n) {
        if(!isLastItemOfRow(n)) return;

        constraintSet.connect(viewId, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, margin);
    }

    private void setInterItemProperties(ConstraintSet constraintSet, int viewId, int n) {
        if(isFirstItem(n)) return;
        if(isFirstItemOfRow(n)) return;
        View prevItem = itemList.get(n-1);
        int prevViewId = prevItem.getId();
        constraintSet.connect(viewId, ConstraintSet.TOP, prevViewId, ConstraintSet.TOP);
        constraintSet.connect(viewId, ConstraintSet.BOTTOM, prevViewId, ConstraintSet.BOTTOM);
        constraintSet.connect(viewId, ConstraintSet.START, prevViewId, ConstraintSet.END);
        constraintSet.connect(prevViewId, ConstraintSet.END, viewId, ConstraintSet.START);
    }

    private void setFirstOfRowItemProperties(ConstraintSet constraintSet, int viewId, int n) {
        if(!isFirstItemOfRow(n)) return;

        constraintSet.connect(viewId, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, margin);

        if(n<rows) return;

        View prevItem = itemList.get(n - rows);
        int prevViewId = prevItem.getId();
        constraintSet.connect(viewId, ConstraintSet.TOP, prevViewId, ConstraintSet.BOTTOM, margin*2);
        constraintSet.connect(prevViewId, ConstraintSet.BOTTOM, viewId, ConstraintSet.TOP, margin*2);

        if(!isLastItemOfColumn(n)) return;

        constraintSet.connect(viewId, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
    }

    private void setLastItemProperties(ConstraintSet constraintSet, int viewId, int n) {
        if(!isLastItem(n)) return;

        constraintSet.connect(viewId, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, margin);
    }

    private void setFirstItemProperties(ConstraintSet constraintSet, int viewId, int n) {
        if(!isFirstItem(n)) return;

        constraintSet.setHorizontalChainStyle(viewId, ConstraintSet.CHAIN_SPREAD);
        constraintSet.setHorizontalChainStyle(viewId, ConstraintSet.CHAIN_SPREAD);
        constraintSet.connect(viewId, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, margin);
    }

    private void setDimensions(int id, ConstraintSet constraintSet) {
        constraintSet.constrainHeight(id, 300);
        constraintSet.constrainWidth(id, 300);
    }

    private boolean isFirstItemOfRow(int n) {
        return getRow(n) == 0;
    }
    private boolean isFirstItem(int n) {
        return n == 0;
    }
    private boolean isLastItem(int n) {
        return n+1 == itemList.size();
    }
    private boolean isLastItemOfRow(int n) {
        return getRow(n) == rows - 1 || isLastItem(n);
    }
    private boolean isLastItemOfColumn(int n) {
        return cols - 1 == getColumn(n);
    }

    private int getRow(int n) {
        return n % rows;
    }
    private int getColumn(int n) {
        return (int) Math.floor((double) n / (double) rows);
    }
    private int getColumns(int n) {
        return (int) Math.ceil( (double) n / (double) rows);
    }

}
