package com.github.paizo.pongo.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.paizo.pongo.BodyHelper;
import com.github.paizo.pongo.Consts;
import com.github.paizo.pongo.ContactType;
import com.github.paizo.pongo.GameScreen;

public abstract class PlayerPaddle {

    protected Body body;
    protected float x, y, speed, velY;
    protected int width;
    protected int height;

    protected int score;
    protected Texture texture;
    protected GameScreen gameScreen;

    public PlayerPaddle(float x, float y, GameScreen gameScreen) {
        this.x = x;
        this.y = y;
        this.gameScreen = gameScreen;
        this.speed = 6;
        this.width = 16;
        this.height = 64;
        this.texture = new Texture("white.png");
        this.body = BodyHelper.createBody(x, y, width, height, false, 10000, gameScreen.getWorld(), ContactType.PLAYER);

    }

    public void update() {
        x = body.getPosition().x * Consts.PPM - (width / 2);
        y = body.getPosition().y * Consts.PPM - (height / 2);
        velY = 0;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    public void score() {
        score++;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

