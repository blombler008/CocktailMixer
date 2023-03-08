package com.tattyhost.cocktail_mixer.buttons;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tattyhost.cocktail_mixer.CocktailActivity;
import com.tattyhost.cocktail_mixer.helper.ButtonAction;
import com.tattyhost.cocktail_mixer.network.NetworkTask;
import com.tattyhost.cocktail_mixer.network.command.AddButton;
import com.tattyhost.cocktail_mixer.network.command.UID;

public class ConnectESPButton extends ButtonAction {

    private final Button button;
    private final TextView output;
    private final StringBuilder currentText = new StringBuilder();
    private final AddButton add;
    private final UID uid;
    private NetworkTask task;
    public ConnectESPButton(CocktailActivity cocktailActivity, Button button, TextView output) {
        setActivity(cocktailActivity);
        this.button = button;
        this.output = output;
        currentText.append(output.getText());

        uid = new UID(getActivity(), getActivity().getBinding().uid);
        add = new AddButton(getActivity().getMenu(), uid);
    }

    @Override
    public Button getButton() {
        return button;
    }

    @Override
    public void getOnClickListener(View view) {
        if(task != null) task.quit(true);
        task = new NetworkTask(this);
        add.setNetworkTask(task);
        uid.setNetworkTask(task);
        task.addCMD("add", add);
        task.addCMD("uid", uid);
        task.execute();

    }

    private Handler mHandler = new Handler(Looper.getMainLooper());

    public void println(String s) {
        currentText.append(s);
        currentText.append('\n');
        mHandler.post(() -> {
            Log.i("OUTPUT ESP", "println: " + s);
            output.setText(currentText.toString());
        } );
    }
}

