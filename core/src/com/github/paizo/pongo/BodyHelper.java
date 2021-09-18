package com.github.paizo.pongo;

import com.badlogic.gdx.physics.box2d.*;

public class BodyHelper {

    public static Body createBody(
            float x, float y, float width, float height,
            boolean isStatic, float density, World world,
            ContactType contactType
    ) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = isStatic ? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x / Consts.PPM, y / Consts.PPM);
        bodyDef.fixedRotation = true;
        Body body = world.createBody(bodyDef);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(width / 2 / Consts.PPM, height / 2 / Consts.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = density;
        body.createFixture(fixtureDef).setUserData(contactType);

        polygonShape.dispose();
        return body;
    }
}
