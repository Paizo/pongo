package com.github.paizo.pongo.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.paizo.pongo.BodyHelper;
import com.github.paizo.pongo.Boot;
import com.github.paizo.pongo.ContactType;
import com.github.paizo.pongo.GameScreen;

public class Wall {

    protected Body body;
    protected float x, y;
    protected int width, height;
    protected GameScreen gameScreen;
    protected Texture texture;

    public Wall(float y, GameScreen gameScreen) {
        this.x = Boot.INSTANCE.getScreenWidth() / 2;
        this.y = y;
        this.gameScreen = gameScreen;
        this.width = Boot.INSTANCE.getScreenWidth();
        this.height = 64;
        this.texture = new Texture("white.png");
        this.body = BodyHelper.createBody(x, y, width, height, true, 0, gameScreen.getWorld(), ContactType.WALL);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x - (width / 2), y - (height / 2), width, height);
    }
}

