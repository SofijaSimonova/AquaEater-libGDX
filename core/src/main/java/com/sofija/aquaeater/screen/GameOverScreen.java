package com.sofija.aquaeater.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sofija.aquaeater.AquaEaterGame;
import com.sofija.aquaeater.assets.GameAssets;
import com.sofija.aquaeater.config.GameConfig;

public class GameOverScreen extends AbstractEndScreen {

    private static final float LINE_SPACING = 60f;

    private static final Color TITLE_COLOR = Color.RED;
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color SCORE_COLOR = Color.YELLOW;

    public GameOverScreen(AquaEaterGame game, int score, int highScore) {
        super(game, score, highScore);
    }

    @Override
    protected void renderContent() {
        drawCenteredText(titleFont, TITLE_COLOR, "GAME OVER", centerY + LINE_SPACING * 2);
        drawCenteredText(textFont, TEXT_COLOR, "Score: " + score, centerY + LINE_SPACING);
        drawCenteredText(textFont, SCORE_COLOR, "High Score: " + highScore, centerY);
        drawCenteredText(smallFont, TEXT_COLOR, "Press ENTER to Restart", centerY - LINE_SPACING * 2);
    }
}
