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

public abstract class AbstractEndScreen implements Screen {

    protected final AquaEaterGame game;
    protected final GameAssets assets;

    protected final int score;
    protected final int highScore;

    protected SpriteBatch batch;
    protected OrthographicCamera camera;
    protected Viewport viewport;
    protected GlyphLayout layout;

    protected BitmapFont titleFont;
    protected BitmapFont textFont;
    protected BitmapFont smallFont;

    protected float centerX;
    protected float centerY;

    public AbstractEndScreen(AquaEaterGame game, int score, int highScore) {
        this.game = game;
        this.assets = game.getAssets();
        this.score = score;
        this.highScore = highScore;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        batch = new SpriteBatch();
        layout = new GlyphLayout();
        titleFont = assets.getTitleFont();
        textFont = assets.getTextFont();
        smallFont = assets.getSmallFont();

        centerX = GameConfig.WORLD_WIDTH / 2f;
        centerY = GameConfig.WORLD_HEIGHT / 2f;

    }

    @Override
    public void render(float delta) {

        clearScreen();

        batch.begin();

        drawBackground();

        renderContent();

        batch.end();

        handleInput();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    protected abstract void renderContent();

    protected void handleInput() {

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.setScreen(new MainMenuScreen(game));
        }
    }

    protected void drawCenteredText(
        BitmapFont font,
        Color color,
        String text,
        float y
    ) {

        font.setColor(color);

        layout.setText(font, text);

        font.draw(
            batch,
            layout,
            centerX - layout.width / 2f,
            y
        );
    }

    protected void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    protected void drawBackground() {
        batch.draw(
            assets.getBackground(),
            0,
            0,
            GameConfig.WORLD_WIDTH,
            GameConfig.WORLD_HEIGHT
        );
    }

}
