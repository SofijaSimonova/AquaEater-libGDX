package com.sofija.aquaeater;

import com.badlogic.gdx.Game;
import com.sofija.aquaeater.assets.GameAssets;
import com.sofija.aquaeater.screen.GameScreen;

public class AquaEaterGame extends Game {

    private GameAssets assets;

    @Override
    public void create() {
        assets = new GameAssets();
        assets.load();
        assets.finishLoading();

        setScreen(new GameScreen(this));
    }

    public GameAssets getAssets() {
        return assets;
    }

    @Override
    public void dispose() {
        super.dispose();
        assets.dispose();
    }
}
