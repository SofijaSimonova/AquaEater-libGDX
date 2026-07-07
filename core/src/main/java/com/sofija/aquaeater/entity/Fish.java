package com.sofija.aquaeater.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.sofija.aquaeater.config.GameConfig;

import java.awt.*;

public class Fish {

    protected Texture texture;
    protected float x;
    protected float y;
    protected float width;
    protected float height;
    protected float speed;


    public Fish(Texture texture, float x, float y, float width, float height, float speed) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    public void update(float delta) {
        x += speed * delta;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public boolean isOutOfScreen() {
        return x > GameConfig.WORLD_WIDTH + GameConfig.SPAWN_MARGIN
            || x + width < -GameConfig.SPAWN_MARGIN;
    }

    public Texture getTexture() {
        return texture;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getSpeed() {
        return speed;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
