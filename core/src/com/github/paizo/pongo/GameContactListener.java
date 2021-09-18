package com.github.paizo.pongo;

import com.badlogic.gdx.physics.box2d.*;

public class GameContactListener implements ContactListener {

    GameScreen gameScreen;

    public GameContactListener(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        if (a == null || b == null) return;
        if (a.getUserData() == null || b.getUserData() == null) return;

        if (a.getUserData() == ContactType.BALL || b.getUserData() == ContactType.BALL) {
            //Ball - Player
            if (a.getUserData() == ContactType.PLAYER || b.getUserData() == ContactType.PLAYER) {
                gameScreen.getBall().reverseVelocityX();
                gameScreen.getBall().increaseSpeed();
            }

            //Ball - Wall
            if (a.getUserData() == ContactType.WALL || b.getUserData() == ContactType.WALL) {
                gameScreen.getBall().reverseVelocityY();
                gameScreen.getBall().increaseSpeed();
            }
        }



    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
