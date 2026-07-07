package com.sofija.aquaeater.entity;

import com.badlogic.gdx.graphics.Texture;
import com.sofija.aquaeater.entity.enums.BoosterType;

public class BoosterFish extends Fish {

    private final BoosterType boosterType;

    public BoosterFish(Texture texture, float x, float y, float width, float height, float speed, BoosterType boosterType) {
        super(texture, x, y, width, height, speed);
        this.boosterType = boosterType;
    }

    public BoosterType getBoosterType() {
        return boosterType;
    }

}
