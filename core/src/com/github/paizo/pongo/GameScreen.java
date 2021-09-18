package com.github.paizo.pongo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.github.paizo.pongo.objects.Ball;
import com.github.paizo.pongo.objects.Player;
import com.github.paizo.pongo.objects.PlayerAi;
import com.github.paizo.pongo.objects.Wall;

public class GameScreen extends ScreenAdapter {

    private OrthographicCamera camera;
    private SpriteBatch spriteBatch;

    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private GameContactListener gameContactListener;

    //game objects
    private Player player;
    private PlayerAi playerAi;

    private Ball ball;
    private Wall wallUp;
    private Wall wallBottom;
    private TextureRegion[] numbers;

    public GameScreen(OrthographicCamera camera) {
        this.camera = camera;
        this.camera.position.set(
                new Vector3(
                Boot.INSTANCE.getScreenWidth() / 2,
                Boot.INSTANCE.getScreenHeight() / 2,
                0
                )
        );
        this.spriteBatch = new SpriteBatch();
        this.world = new World(new Vector2(0, 0), false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();
        this.gameContactListener = new GameContactListener(this);
        this.world.setContactListener(this.gameContactListener);
        this.player = new Player(16, Boot.INSTANCE.getScreenHeight() / 2, this);
        this.playerAi = new PlayerAi(Boot.INSTANCE.getScreenWidth() - 16, Boot.INSTANCE.getScreenHeight() / 2, this);
        this.ball = new Ball(this);
        this.wallUp = new Wall(32, this);
        this.wallBottom = new Wall(Boot.INSTANCE.getScreenHeight() - 32, this);
        this.numbers = loadTextureSprite("numbers.png", 10);
    }

    public void update() {
        world.step(1 / 60f, 6, 2 );

        camera.update();
        this.player.update();
        this.playerAi.update();
        this.ball.update();

        spriteBatch.setProjectionMatrix(camera.combined);
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            this.ball.reset();
            this.player.setScore(0);
            this.playerAi.setScore(0);
        }

    }

    @Override
    public void render(float delta) {
        update();

        ScreenUtils.clear(Color.BLACK);

        spriteBatch.begin();
        this.player.render(spriteBatch);
        this.playerAi.render(spriteBatch);
        this.ball.render(spriteBatch);
        this.wallUp.render(spriteBatch);
        this.wallBottom.render(spriteBatch);
        drawNumbers(spriteBatch, player.getScore(), 64, Boot.INSTANCE.getScreenHeight() - 55, 30, 42);
        drawNumbers(spriteBatch, playerAi.getScore(), Boot.INSTANCE.getScreenWidth() - 96, Boot.INSTANCE.getScreenHeight() - 55, 30, 42);
        spriteBatch.end();

//        box2DDebugRenderer.render(world, camera.combined.scl(Consts.PPM));
    }

    private void drawNumbers(SpriteBatch batch, int number, float x, float y, float width, float height) {
        if (number < 10) {
            batch.draw(numbers[number], x, y, width, height);
        } else {
            batch.draw(numbers[Integer.parseInt(("" + number).substring(0,1))], x, y, width, height);
            batch.draw(numbers[Integer.parseInt(("" + number).substring(1,2))], x + 20, y, width, height);
        }
    }

    private TextureRegion[] loadTextureSprite(String filename, int colums) {
        Texture texture = new Texture(filename);
        return TextureRegion.split(texture, texture.getWidth() / colums, texture.getHeight())[0];
    }

    public World getWorld() {
        return world;
    }

    public Ball getBall() {
        return ball;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerAi getPlayerAi() {
        return playerAi;
    }
}
