package com.sofija.aquaeater.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sofija.aquaeater.assets.GameAssets;
import com.sofija.aquaeater.config.GameConfig;
import com.sofija.aquaeater.entity.BoosterFish;
import com.sofija.aquaeater.entity.Fish;
import com.sofija.aquaeater.entity.PlayerFish;
import com.sofija.aquaeater.entity.enums.BoosterType;

import java.util.ArrayList;
import java.util.List;

public class GameWorld {

    private final PlayerFish player;
    private final List<Fish> fishes;
    private final FishSpawner fishSpawner;
    private final GameAssets assets;
    private int score;
    private boolean gameOver = false;
    private boolean gameWon = false;
    private final int selectedFish;


    public GameWorld(GameAssets assets, int selectedFish) {
        this.assets = assets;
        this.selectedFish = selectedFish;
        this.player = new PlayerFish(assets.getPlayerTexture(selectedFish));
        this.fishes = new ArrayList<>();

        this.fishSpawner = new FishSpawner(
            fishes,
            assets
        );
    }

    public void update(float delta) {
        player.update(delta);
        fishSpawner.update(delta);
        updateFishes(delta);
        handleCollision();
        removeFishOutsideScreen();
        checkWinCondition();

    }

    private void updateFishes(float delta) {
        fishes.forEach(fish -> fish.update(delta));
    }

    private void removeFishOutsideScreen() {
        fishes.removeIf(Fish::isOutOfScreen);
    }

    private Fish getCollidingFish(){
        for (Fish fish : fishes) {
            if (player.getBounds().overlaps(fish.getBounds())) {
                return fish;
            }
        }
        return null;
    }

    private void handleCollision() {
        Fish collidingFish = getCollidingFish();
        if (collidingFish == null) {
            return;
        }
        if (collidingFish instanceof BoosterFish boosterFish){
            activateBooster(boosterFish);
            eatFish(boosterFish);

            return;
        }
        if (player.hasBooster(BoosterType.POISON)){
            //namali high score, ne jadi ribi
            if (!player.canEat(collidingFish)){
                triggerGameOver();
            }
            return;
        }
        if (player.hasBooster(BoosterType.FRENZY)){
            eatFish(collidingFish);
            return;
        }
        if (player.hasBooster(BoosterType.ARMOR)){
            if (player.canEat(collidingFish)){
                eatFish(collidingFish);
            }
            return;
        }
        if (player.canEat(collidingFish)){
            eatFish(collidingFish);
            return;
        }
        triggerGameOver();
    }

    private void triggerGameOver() {
        gameOver = true;
    }

    private void eatFish(Fish fish) {
        fishes.remove(fish);
        player.grow();
        addScore();
        assets.getEatSound().play();
    }
    public PlayerFish getPlayer() {
        return player;
    }

    public List<Fish> getFishes() {
        return fishes;
    }

    public int getScore() {
        return score;
    }

    private void addScore() {
        score += GameConfig.FISH_SCORE;
    }

    public void render(SpriteBatch batch){
        player.render(batch);
        fishes.forEach(fish -> fish.render(batch));
    }

    public void activateBooster(BoosterFish boosterFish) {
        player.setActiveBooster(boosterFish.getBoosterType());
        player.setBoosterTimer(GameConfig.BOOSTER_EFFECT_DURATION);
        player.setCurrentTexture(assets.getModeTexture(boosterFish.getBoosterType()));

    }

    private void checkWinCondition() {
        if (player.getWidth() >= GameConfig.MAX_FISH_WIDTH) {
            gameWon = true;
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isGameWon() {
        return gameWon;
    }
}
