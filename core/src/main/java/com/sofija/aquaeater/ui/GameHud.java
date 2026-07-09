package com.sofija.aquaeater.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sofija.aquaeater.assets.GameAssets;
import com.sofija.aquaeater.config.GameConfig;
import com.sofija.aquaeater.entity.enums.BoosterType;
import com.sofija.aquaeater.manager.HighScoreManager;
import com.sofija.aquaeater.world.GameWorld;

public class GameHud {

    private static final float PADDING = 20f;
    private static final float LINE_SPACING = 35f;
    private final BitmapFont font;
    private final GameWorld gameWorld;
    private final HighScoreManager highScoreManager;
    private GlyphLayout layout;

    public GameHud(GameWorld gameWorld, HighScoreManager highScoreManager, GameAssets assets) {
        this.highScoreManager = highScoreManager;
        this.font = assets.getSmallFont();
        this.gameWorld = gameWorld;
        layout = new GlyphLayout();
    }

    public void render(SpriteBatch batch){
       renderScore(batch);
       renderBooster(batch);
    }

    private void renderScore(SpriteBatch batch){
        font.draw(batch, "Score: " + gameWorld.getScore(), PADDING, GameConfig.WORLD_HEIGHT - PADDING);
        font.draw(batch, "High Score: " + highScoreManager.getHighScore(), PADDING, GameConfig.WORLD_HEIGHT - PADDING - LINE_SPACING);
    }

    private void renderBooster(SpriteBatch batch){
        BoosterType booster = gameWorld.getPlayer().getActiveBooster();
        String boosterText = booster == null ? "Booster: None" : "Booster: " + booster;

        layout.setText(font, boosterText);

        font.draw(
            batch,
            boosterText,
            GameConfig.WORLD_WIDTH - layout.width - PADDING,
            GameConfig.WORLD_HEIGHT - PADDING
        );

        if (booster == null){
            return;
        }

        String timerText = String.format("Time: %.1f s", gameWorld.getPlayer().getBoosterTimer());

        layout.setText(font, timerText);

        font.draw(
            batch,
            timerText,
            GameConfig.WORLD_WIDTH - layout.width - PADDING,
            GameConfig.WORLD_HEIGHT - PADDING - LINE_SPACING
        );
    }
}
