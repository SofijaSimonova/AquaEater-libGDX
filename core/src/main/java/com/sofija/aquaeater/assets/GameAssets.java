package com.sofija.aquaeater.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.sofija.aquaeater.config.GameConfig;
import com.sofija.aquaeater.entity.enums.BoosterType;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class GameAssets {

    private static final String BACKGROUND = "images/background.png";
    private static final String PLAYER = "images/player/default.png";
    private static final String FISH_FOLDER = "images/fish/";
    private static final String ARMOR_FISH = "images/boosters/armor_fish.png";
    private static final String FRENZY_FISH = "images/boosters/frenzy_fish.png";
    private static final String POISON_FISH = "images/boosters/poison_fish.png";
    private static final String SPEED_FISH = "images/boosters/speed_fish.png";
    private static final String ARMOR_MODE = "images/modes/armored.png";
    private static final String FRENZY_MODE = "images/modes/frenzy.png";
    private static final String POISON_MODE = "images/modes/poisoned.png";
    private static final String SPEED_MODE = "images/modes/speed.png";
    private final AssetManager assetManager;
    private final List<Texture> fishTextures = new ArrayList<>();
    private final Map<BoosterType, Texture> boosterTextures = new EnumMap<>(BoosterType.class);
    private final Map<BoosterType, Texture> modeTextures = new EnumMap<>(BoosterType.class);

    public GameAssets() {
        assetManager = new AssetManager();
    }

    public void load() {
        assetManager.load(BACKGROUND, Texture.class);
        assetManager.load(PLAYER, Texture.class);
        for (int i = 1; i <= GameConfig.FISH_COUNT; i++) {
            assetManager.load(
                FISH_FOLDER + "fish (" + i + ").png",
                Texture.class
            );
        }

        assetManager.load(ARMOR_FISH, Texture.class);
        assetManager.load(SPEED_FISH, Texture.class);
        assetManager.load(POISON_FISH, Texture.class);
        assetManager.load(FRENZY_FISH, Texture.class);
        assetManager.load(POISON_MODE, Texture.class);
        assetManager.load(SPEED_MODE, Texture.class);
        assetManager.load(ARMOR_MODE, Texture.class);
        assetManager.load(FRENZY_MODE, Texture.class);

    }

    public void finishLoading() {
        assetManager.finishLoading();
        for (int i = 1; i <= GameConfig.FISH_COUNT; i++) {
            fishTextures.add(
                assetManager.get(
                    FISH_FOLDER + "fish (" + i + ").png",
                    Texture.class
                )
            );
        }

        boosterTextures.put(BoosterType.ARMOR, assetManager.get(ARMOR_FISH, Texture.class));
        boosterTextures.put(BoosterType.SPEED, assetManager.get(SPEED_FISH, Texture.class));
        boosterTextures.put(BoosterType.POISON, assetManager.get(POISON_FISH, Texture.class));
        boosterTextures.put(BoosterType.FRENZY, assetManager.get(FRENZY_FISH, Texture.class));
        modeTextures.put(BoosterType.ARMOR, assetManager.get(ARMOR_MODE, Texture.class));
        modeTextures.put(BoosterType.SPEED, assetManager.get(SPEED_MODE, Texture.class));
        modeTextures.put(BoosterType.POISON, assetManager.get(POISON_MODE, Texture.class));
        modeTextures.put(BoosterType.FRENZY, assetManager.get(FRENZY_MODE, Texture.class));

    }

    public Texture getBackground() {
        return assetManager.get(BACKGROUND, Texture.class);
    }

    public Texture getPlayer() {
        return assetManager.get(PLAYER, Texture.class);
    }
    public void dispose() {
        assetManager.dispose();
    }

    public Texture getRandomFishTexture() {
        return fishTextures.get(MathUtils.random(fishTextures.size() - 1));
    }

    public Texture getBoosterTexture(BoosterType type) {
        return boosterTextures.get(type);
    }

    public Texture getModeTexture(BoosterType type) {
        return modeTextures.get(type);
    }
}
