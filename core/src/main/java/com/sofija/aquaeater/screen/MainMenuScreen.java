package com.sofija.aquaeater.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sofija.aquaeater.AquaEaterGame;
import com.sofija.aquaeater.assets.GameAssets;
import com.sofija.aquaeater.config.GameConfig;
import com.sofija.aquaeater.manager.HighScoreManager;

public class MainMenuScreen implements Screen {

    private static final float SELECTION_PADDING = 8f;
    private final AquaEaterGame game;
    private final GameAssets assets;
    private final HighScoreManager highScoreManager;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private GlyphLayout layout;

    private BitmapFont titleFont;
    private BitmapFont textFont;
    private BitmapFont smallFont;
    private Texture selectedTexture;



    private float centerX;
    private float centerY;

    private int selectedFish = 0;

    public MainMenuScreen(AquaEaterGame game) {
        this.game = game;
        this.assets = game.getAssets();
        this.highScoreManager = new HighScoreManager();
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

        updateSelectedFish();
    }

    @Override
    public void render(float delta) {
        handleInput();
        Gdx.gl.glClearColor(0f, 0.3f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        batch.draw(
            assets.getBackground(),
            0,
            0,
            GameConfig.WORLD_WIDTH,
            GameConfig.WORLD_HEIGHT
        );

        assets.getTitleFont().setColor(Color.WHITE);
        drawCenteredText(
            titleFont,
            batch,
            "SELECT YOUR FISH",
            centerY + 180
        );

        assets.getTextFont().setColor(Color.YELLOW);
        drawCenteredText(
            textFont,
            batch,
            "High Score: " + highScoreManager.getHighScore(),
            centerY + 80
        );

        renderFishSelection();

        assets.getSmallFont().setColor(Color.WHITE);
        drawCenteredSmallText(
            "<- -> Select Fish",
            centerX,
            GameConfig.WORLD_HEIGHT * 0.17f
        );

        drawCenteredSmallText(
            "ENTER Start Game",
            centerX,
            GameConfig.WORLD_HEIGHT * 0.12f
        );

        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.setScreen(new GameScreen(game, selectedFish));
        }
    }

    private void drawCenteredText(
        BitmapFont font,
        SpriteBatch batch,
        String text,
        float y
    ) {
        layout.setText(font, text);

        font.draw(
            batch,
            layout,
            centerX - layout.width / 2f,
            y
        );
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

    }

    private void updateSelectedFish() {
        selectedTexture = assets.getPlayerTexture(selectedFish);
    }

    private void handleInput() {

        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            selectedFish = (selectedFish + 1) % getUnlockedFishCount();
            updateSelectedFish();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            selectedFish = (selectedFish - 1 + getUnlockedFishCount()) % getUnlockedFishCount();
            updateSelectedFish();
        }
    }

    private boolean isUnlocked(int fishIndex) {
        int highScore = highScoreManager.getHighScore();

        return switch (fishIndex) {
            case 0 -> true;
            case 1 -> highScore >= GameConfig.FISH_2_UNLOCK_SCORE;
            case 2 -> highScore >= GameConfig.FISH_3_UNLOCK_SCORE;
            case 3 -> highScore >= GameConfig.FISH_4_UNLOCK_SCORE;
            default -> false;
        };
    }

    private int getUnlockedFishCount() {
        int unlocked = 0;

        for (int i = 0; i < assets.getPlayerCount(); i++) {
            if (isUnlocked(i)) {
                unlocked++;
            }
        }

        return unlocked;
    }

    private void renderFishSelection() {

        float availableWidth = GameConfig.WORLD_WIDTH * 0.7f;
        float fishSpacing = availableWidth / (assets.getPlayerCount() - 1);

        float startX = centerX - availableWidth / 2f - GameConfig.PLAYER_WIDTH / 2f;
        float y = centerY - GameConfig.WORLD_HEIGHT * 0.15f;

        for (int i = 0; i < assets.getPlayerCount(); i++) {

            float drawX = startX + i * fishSpacing;
            float width = GameConfig.PLAYER_WIDTH;
            float height = GameConfig.PLAYER_HEIGHT;


            if (i == selectedFish) {
                batch.draw(
                    assets.getSelectionTexture(),
                    drawX - SELECTION_PADDING,
                    y - SELECTION_PADDING,
                    width + SELECTION_PADDING * 2,
                    height + SELECTION_PADDING * 2
                );
            }

            batch.draw(
                assets.getPlayerTexture(i),
                drawX,
                y,
                width,
                height
            );

            if (!isUnlocked(i)) {

                batch.draw(
                    assets.getLockTexture(),
                    drawX + width / 2f - 16,
                    y - 25,
                    assets.getLockTexture().getWidth(),
                    assets.getLockTexture().getHeight()
                );

                drawCenteredSmallText(
                    getUnlockText(i),
                    drawX + width / 2f,
                    y - 35
                );
            }
        }
    }

    private String getUnlockText(int fishIndex) {
        return switch (fishIndex) {
            case 1 -> "Unlock at 10";
            case 2 -> "Unlock at 30";
            case 3 -> "Unlock at 50";
            default -> "";
        };
    }

    private void drawCenteredSmallText(String text, float x, float y) {

        layout.setText(smallFont, text);

        smallFont.draw(
            batch,
            layout,
            x - layout.width / 2f,
            y
        );
    }

}
