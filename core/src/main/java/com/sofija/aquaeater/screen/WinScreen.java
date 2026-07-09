package com.sofija.aquaeater.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.sofija.aquaeater.AquaEaterGame;
import com.sofija.aquaeater.assets.GameAssets;
import com.sofija.aquaeater.config.GameConfig;


public class WinScreen extends AbstractEndScreen {

    private static final float LINE_SPACING = 60f;

    private static final Color TITLE_COLOR = Color.GREEN;
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color SCORE_COLOR = Color.YELLOW;

    public WinScreen(AquaEaterGame game, int score, int highScore) {
        super(game, score, highScore);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0.3f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawBackground();
        renderContent();
        batch.end();

        handleInput();
    }

    @Override
    protected void renderContent() {
        drawCenteredText(titleFont, TITLE_COLOR, "Congratulations!", centerY + LINE_SPACING * 2);
        drawCenteredText(textFont, TEXT_COLOR, "You are the biggest fish in the sea!", centerY + LINE_SPACING);
        drawCenteredText(textFont, SCORE_COLOR, "Score: " + score, centerY);
        drawCenteredText(smallFont, TEXT_COLOR, "Press ENTER to Return to Menu", centerY - LINE_SPACING * 2);
    }
}
