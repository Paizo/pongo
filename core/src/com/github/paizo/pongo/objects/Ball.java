package com.github.paizo.pongo.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.paizo.pongo.*;

public class Ball {

    protected Body body;
    protected float x, y, speed, velX, velY;
    protected int width, height;
    protected GameScreen gameScreen;
    protected Texture texture;

    public Ball(GameScreen gameScreen) {
        this.x = Boot.INSTANCE.getScreenWidth() / 2;
        this.y = Boot.INSTANCE.getScreenHeight() / 2;
        this.gameScreen = gameScreen;
        this.speed = 5;
        this.width = 32;
        this.height = 32;
        this.texture = new Texture("white.png");
        this.velX = getRandomDirection();
        this.velY = getRandomDirection();
        this.body = BodyHelper.createBody(x, y, width, height, false, 0, gameScreen.getWorld(), ContactType.BALL);
    }

    private float getRandomDirection() {
        return Math.random() < 0.5 ? 1 : -1;
    }
    public void update() {
        x = body.getPosition().x * Consts.PPM - (width / 2);
        y = body.getPosition().y * Consts.PPM - (height / 2);

        this.body.setLinearVelocity(velX * speed, velY * speed);

        //score
        if (x < 0) {
            gameScreen.getPlayerAi().score();
            reset();
        } else if (x > Boot.INSTANCE.getScreenWidth()) {
            gameScreen.getPlayer().score();
            reset();
        }

    }

    public void reset() {
        velX = getRandomDirection();
        velY = getRandomDirection();
        speed = 5;
        body.setTransform(Boot.INSTANCE.getScreenWidth() / 2 / Consts.PPM, Boot.INSTANCE.getScreenHeight() / 2 / Consts.PPM, 0);
    }

    public void reverseVelocityX() {
        velX *= -1;
    }

    public void reverseVelocityY() {
        velY *= -1;
    }

    public void increaseSpeed() {
        speed *= 1.1f;
    }
    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }
}

