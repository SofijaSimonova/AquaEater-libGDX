package com.sofija.aquaeater.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.math.MathUtils;
import com.sofija.aquaeater.assets.GameAssets;
import com.sofija.aquaeater.config.GameConfig;
import com.sofija.aquaeater.entity.BoosterFish;
import com.sofija.aquaeater.entity.Fish;
import com.sofija.aquaeater.entity.enums.BoosterType;

import java.util.List;

public class FishSpawner {

    private final List<Fish> fishes;
    private float fishSpawnTimer;
    private float boosterSpawnTimer;
    private final GameAssets assets;

    public FishSpawner(List<Fish> fishes, GameAssets assets) {
        this.fishes = fishes;
        this.assets = assets;
    }

    public void update(float delta) {

        fishSpawnTimer += delta;
        boosterSpawnTimer += delta;

        if (fishSpawnTimer >= GameConfig.FISH_SPAWN_INTERVAL) {

            fishes.add(createRandomFish());

            fishSpawnTimer = 0f;
        }

        if (boosterSpawnTimer >= GameConfig.BOOSTER_FISH_SPAWN_INTERVAL) {
            fishes.add(createRandomBoosterFish());
            boosterSpawnTimer = 0f;
        }
    }

    private BoosterFish createRandomBoosterFish() {
        float width = GameConfig.BOOSTER_WIDTH;
        float height = GameConfig.BOOSTER_HEIGHT;
        float y = MathUtils.random(0, GameConfig.WORLD_HEIGHT - height);
        BoosterType boosterType = BoosterType.values()[MathUtils.random(0, BoosterType.values().length-1)];
        Texture texture = assets.getBoosterTexture(boosterType);
        return new BoosterFish(texture, -width, y, width, height, GameConfig.FISH_SPEED, boosterType);

    }

    private Fish createRandomFish() {
        float width = MathUtils.random(GameConfig.MIN_FISH_WIDTH, GameConfig.MAX_FISH_WIDTH);
        float height = width * 0.75f;
        float y = MathUtils.random(0, GameConfig.WORLD_HEIGHT - height);
        return new Fish(assets.getRandomFishTexture(), -width, y, width, height, GameConfig.FISH_SPEED);
    }
}
