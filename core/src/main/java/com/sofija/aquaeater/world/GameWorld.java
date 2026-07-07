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


    public GameWorld(GameAssets assets) {

        this.player = new PlayerFish(assets.getPlayer());
        this.assets = assets;

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
        if (player.canEat(collidingFish) || player.hasBooster(BoosterType.FRENZY)){
            eatFish(collidingFish);
            return;
        }
        if (player.hasBooster(BoosterType.ARMOR)){
            fishes.remove(collidingFish);
            return;
        }
        gameOver();
    }

    private void gameOver() {
        System.out.println("Game Over");
    }

    private void eatFish(Fish fish) {
        fishes.remove(fish);
        player.grow();
    }
    public PlayerFish getPlayer() {
        return player;
    }

    public List<Fish> getFishes() {
        return fishes;
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
}
