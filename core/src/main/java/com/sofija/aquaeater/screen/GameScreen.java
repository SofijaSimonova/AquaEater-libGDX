package com.sofija.aquaeater.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sofija.aquaeater.AquaEaterGame;
import com.sofija.aquaeater.assets.GameAssets;
import com.sofija.aquaeater.config.GameConfig;
import com.sofija.aquaeater.entity.Fish;
import com.sofija.aquaeater.entity.PlayerFish;
import com.sofija.aquaeater.manager.HighScoreManager;
import com.sofija.aquaeater.ui.GameHud;
import com.sofija.aquaeater.world.FishSpawner;
import com.sofija.aquaeater.world.GameWorld;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {

    private final AquaEaterGame game;
    private SpriteBatch batch;
    private final GameAssets assets;
    private OrthographicCamera camera;
    private Viewport viewport;
    private GameWorld gameWorld;
    private GameHud hud;
    private HighScoreManager highScoreManager;
    private final int selectedFish;

    public GameScreen(AquaEaterGame game, int selectedFish) {
        this.game = game;
        this.assets = game.getAssets();
        this.selectedFish = selectedFish;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        batch = new SpriteBatch();
        gameWorld = new GameWorld(assets, selectedFish);
        highScoreManager = new HighScoreManager();
        hud = new GameHud(gameWorld, highScoreManager, assets);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0.3f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameWorld.update(delta);

        if (gameWorld.isGameWon()) {
            highScoreManager.saveHighScore(gameWorld.getScore());
            assets.getVictorySound().play();
            game.setScreen(
                new WinScreen(
                    game,
                    gameWorld.getScore(),
                    highScoreManager.getHighScore()
                )
            );
            return;
        }

        if (gameWorld.isGameOver()){
            assets.getGameOverSound().play();
            highScoreManager.saveHighScore(gameWorld.getScore());
            game.setScreen(new GameOverScreen(game, gameWorld.getScore(), highScoreManager.getHighScore()));
            return;
        }

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(assets.getBackground(), 0, 0, GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT);
        gameWorld.render(batch);
        hud.render(batch);
        batch.end();
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
}
