package com.sofija.aquaeater.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.sofija.aquaeater.assets.GameAssets;
import com.sofija.aquaeater.config.GameConfig;
import com.sofija.aquaeater.entity.enums.BoosterType;

public class PlayerFish extends Fish{

    private boolean facingRight = true;
    private BoosterType activeBooster;
    private float boosterTimer;
    private Texture currentTexture;

    public PlayerFish(Texture texture) {
        super(
            texture,
            0,
            0,
            GameConfig.PLAYER_WIDTH,
            GameConfig.PLAYER_HEIGHT,
            GameConfig.PLAYER_SPEED
        );
        this.x = GameConfig.WORLD_WIDTH / 2f - width / 2f;
        this.y = GameConfig.WORLD_HEIGHT / 2f - height / 2f;
        this.currentTexture = texture;
    }

    @Override
    public void update(float delta) {
        float movementSpeed = getMovementSpeed();
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)){
            y+=movementSpeed*delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            y-=movementSpeed*delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            x-=movementSpeed*delta;
            facingRight = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            x+=movementSpeed*delta;
            facingRight = true;
        }

        updateBooster(delta);
        x = MathUtils.clamp(x, 0, GameConfig.WORLD_WIDTH - width);
        y = MathUtils.clamp(y, 0, GameConfig.WORLD_HEIGHT - height);

    }

    private float getMovementSpeed() {

        if (hasBooster(BoosterType.SPEED)) {

            return speed * GameConfig.PLAYER_SPEED_MULTIPLIER;
        }

        return speed;
    }

    @Override
    public void render(SpriteBatch batch) {
        if (facingRight){
            batch.draw(currentTexture, x, y, width, height);
        }
        else {
            batch.draw(currentTexture, x+width, y, -width, height);
        }
    }

    public boolean canEat(Fish fish){
        return width >= fish.getWidth();
    }

    public void grow(){
        width+= GameConfig.PLAYER_GROWTH;
        height = width * GameConfig.PLAYER_ASPECT_RATIO;
    }


    private void updateBooster(float delta) {

        if (activeBooster == null) {
            return;
        }

        boosterTimer -= delta;

        if (boosterTimer <= 0) {
            activeBooster = null;
            currentTexture = texture;
        }
    }

    public void setActiveBooster(BoosterType activeBooster) {
        this.activeBooster = activeBooster;
    }

    public void setBoosterTimer(float boosterTimer) {
        this.boosterTimer = boosterTimer;
    }

    public boolean hasBooster(BoosterType boosterType) {
        return activeBooster == boosterType;
    }

    public void setCurrentTexture(Texture currentTexture) {
        this.currentTexture = currentTexture;
    }

    public BoosterType getActiveBooster() {
        return activeBooster;
    }

    public float getBoosterTimer() {
        return boosterTimer;
    }
}
