package com.sofija.aquaeater.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.sofija.aquaeater.config.GameConfig;
import com.sofija.aquaeater.entity.enums.BoosterType;
import com.badlogic.gdx.audio.Sound;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class GameAssets {

    private static final String BACKGROUND = "images/background.png";
    private static final String FISH_FOLDER = "images/fish/";
    private static final String DEFAULT_PLAYER = "images/player/default.png";
    private static final String GLOW_PLAYER = "images/player/glow.png";
    private static final String SHARK_PLAYER = "images/player/shark.png";
    private static final String SKELETON_PLAYER = "images/player/skeleton.png";
    private static final String ARMOR_FISH = "images/boosters/armor_fish.png";
    private static final String FRENZY_FISH = "images/boosters/frenzy_fish.png";
    private static final String POISON_FISH = "images/boosters/poison_fish.png";
    private static final String SPEED_FISH = "images/boosters/speed_fish.png";
    private static final String ARMOR_MODE = "images/modes/armored.png";
    private static final String FRENZY_MODE = "images/modes/frenzy.png";
    private static final String POISON_MODE = "images/modes/poisoned.png";
    private static final String SPEED_MODE = "images/modes/speed.png";
    private static final String FONT = "fonts/fredoka.ttf";
    private static final String LOCK = "images/ui/lock.png";
    private static final String SELECTION = "images/ui/selection.png";
    private static final String EAT_SOUND = "sounds/eat.wav";
    private static final String GAME_OVER_SOUND = "sounds/game_over.wav";
    private static final String VICTORY_SOUND = "sounds/victory.wav";
    private final AssetManager assetManager;
    private final List<Texture> fishTextures = new ArrayList<>();
    private final Map<BoosterType, Texture> boosterTextures = new EnumMap<>(BoosterType.class);
    private final Map<BoosterType, Texture> modeTextures = new EnumMap<>(BoosterType.class);
    private final List<Texture> playerTextures = new ArrayList<>();
    private BitmapFont titleFont;
    private BitmapFont textFont;
    private BitmapFont smallFont;
    private Sound eatSound;
    private Sound gameOverSound;
    private Sound victorySound;

    public GameAssets() {
        assetManager = new AssetManager();
    }

    public void load() {
        assetManager.load(BACKGROUND, Texture.class);
        assetManager.load(DEFAULT_PLAYER, Texture.class);
        assetManager.load(GLOW_PLAYER, Texture.class);
        assetManager.load(SHARK_PLAYER, Texture.class);
        assetManager.load(SKELETON_PLAYER, Texture.class);
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
        assetManager.load(LOCK, Texture.class);
        assetManager.load(SELECTION, Texture.class);
        assetManager.load(EAT_SOUND, Sound.class);
        assetManager.load(GAME_OVER_SOUND, Sound.class);
        assetManager.load(VICTORY_SOUND, Sound.class);

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
        playerTextures.add(assetManager.get(DEFAULT_PLAYER, Texture.class));
        playerTextures.add(assetManager.get(GLOW_PLAYER, Texture.class));
        playerTextures.add(assetManager.get(SHARK_PLAYER, Texture.class));
        playerTextures.add(assetManager.get(SKELETON_PLAYER, Texture.class));
        eatSound = assetManager.get(EAT_SOUND, Sound.class);
        gameOverSound = assetManager.get(GAME_OVER_SOUND, Sound.class);
        victorySound = assetManager.get(VICTORY_SOUND, Sound.class);
        loadFonts();

    }

    private BitmapFont createFont(FreeTypeFontGenerator generator, int size) {
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = size;
        parameter.minFilter = Texture.TextureFilter.Linear;
        parameter.magFilter = Texture.TextureFilter.Linear;
        return generator.generateFont(parameter);
    }

    private void loadFonts(){
        FreeTypeFontGenerator generator =
            new FreeTypeFontGenerator(
                Gdx.files.internal(FONT)
            );

        titleFont = createFont(generator, 72);
        textFont = createFont(generator, 42);
        smallFont = createFont(generator, 26);

        generator.dispose();
    }

    public Texture getBackground() {
        return assetManager.get(BACKGROUND, Texture.class);
    }

    public void dispose() {
        titleFont.dispose();
        textFont.dispose();
        smallFont.dispose();
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

    public BitmapFont getTitleFont() {
        return titleFont;
    }

    public BitmapFont getTextFont() {
        return textFont;
    }

    public BitmapFont getSmallFont() {
        return smallFont;
    }

    public Texture getPlayerTexture(int index) {
        return playerTextures.get(index);
    }

    public int getPlayerCount() {
        return playerTextures.size();
    }

    public Texture getLockTexture() {
        return assetManager.get(LOCK, Texture.class);
    }

    public Texture getSelectionTexture() {
        return assetManager.get(SELECTION, Texture.class);
    }

    public Sound getEatSound() {
        return eatSound;
    }

    public Sound getGameOverSound() {
        return gameOverSound;
    }

    public Sound getVictorySound() {
        return victorySound;
    }
}
