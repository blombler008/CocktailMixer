package com.tattyhost.cocktail_mixer.network.command;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import com.tattyhost.cocktail_mixer.CocktailActivity;
import com.tattyhost.cocktail_mixer.R;
import com.tattyhost.cocktail_mixer.network.NetworkCMD;

import lombok.Getter;

public class UID extends NetworkCMD {

    private final TextView uidView;
    private final CocktailActivity activity;

    @Getter
    private final long timeout = 20 * 1000;

    @Getter
    private String currentUID;
    private boolean isTimeout = false;
    private boolean requireUid = true;

    private final Runnable clearUIDTextRunnable;
    private final Runnable setUIDTextRunnable;

    public UID(CocktailActivity activity, TextView uidView) {
        this.activity = activity;
        this.uidView = uidView;

        clearUIDTextRunnable = () -> {
            clearUIDText();
            isTimeout = false;
        };

        setUIDTextRunnable = () -> {

            setUIDText(currentUID);
            isTimeout = true;
        };
    }

    private void clearUIDText() {
        uidView.setText("");
    }

    private void setUIDText(String uid) {
        String res = activity.getResources().getString(R.string.uid);
        uidView.setText(String.format(res, uid));
    }

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void execute(String[] args) {

        if(args.length < 1) return;

        String uidCommand = args[0];
        switch (uidCommand) {
            case "set":
                if(isTimeout) return;
                if(args.length < 2) break;
                mHandler.post(setUIDTextRunnable);
                mHandler.postDelayed(clearUIDTextRunnable, timeout);
                currentUID = args[1];
                break;
            case "clear":
                mHandler.removeCallbacks(clearUIDTextRunnable);
                currentUID = null;
                mHandler.post(clearUIDTextRunnable);
                break;
            case "get":
                mHandler.removeCallbacks(clearUIDTextRunnable);
                getNetworkTask().sendMessage("uid " + currentUID);
                currentUID = null;
                mHandler.post(clearUIDTextRunnable);
                break;
            case "req":
                requireUid = Boolean.parseBoolean(args[1]);
                break;
            default:
                break;
        }


    }

    public boolean isUIDSet() {
        return currentUID != null;
    }

    public boolean isRequired() {
        return requireUid;
    }
}
