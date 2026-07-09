package com.sofija.aquaeater.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;


public class HighScoreManager {

    private static final String PREFERENCE_NAME = "AquaEater";
    private static final String HIGH_SCORE_KEY = "highScore";

    private final Preferences preferences;

    public HighScoreManager() {
        this.preferences = Gdx.app.getPreferences(PREFERENCE_NAME);
    }

    public int getHighScore(){
        return preferences.getInteger(HIGH_SCORE_KEY, 0);
    }

    public void saveHighScore(int score){
        if(score <= getHighScore()){
            return;
        }
        preferences.putInteger(HIGH_SCORE_KEY, score);
        preferences.flush();
    }
}
