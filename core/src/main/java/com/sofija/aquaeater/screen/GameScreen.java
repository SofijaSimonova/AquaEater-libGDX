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

    public GameScreen(AquaEaterGame game) {
        this.game = game;
        this.assets = game.getAssets();
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        batch = new SpriteBatch();
        gameWorld = new GameWorld(assets);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0.3f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameWorld.update(delta);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(assets.getBackground(), 0, 0, GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT);
        gameWorld.render(batch);
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
