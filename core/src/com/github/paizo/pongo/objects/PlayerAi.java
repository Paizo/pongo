package com.github.paizo.pongo.objects;

import com.github.paizo.pongo.GameScreen;

public class PlayerAi extends PlayerPaddle {


    public PlayerAi(float x, float y, GameScreen gameScreen) {
        super(x, y, gameScreen);
    }

    public void update() {
        super.update();

        Ball ball = gameScreen.getBall();
        if (ball.y + 10 > y && ball.y - 10 > y)
            velY = 1;
        if (ball.y + 10 < y && ball.y - 10 < y)
            velY = -1;

        body.setLinearVelocity(0, velY * speed);
    }
}
