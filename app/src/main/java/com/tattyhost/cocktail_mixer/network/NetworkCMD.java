package com.tattyhost.cocktail_mixer.network;


import lombok.Getter;
import lombok.Setter;

public abstract class NetworkCMD {
    @Getter @Setter
    private NetworkTask NetworkTask;

    public abstract void execute(String [] args);
}
