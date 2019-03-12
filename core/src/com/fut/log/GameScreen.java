package com.fut.log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.math.Rectangle;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import sun.rmi.runtime.Log;

public class GameScreen implements Screen {


    int jj = 0;

    Skin skin;
    final Drop game;
    OrthographicCamera camera;
    SpriteBatch batch;
    Texture dropImage;
    Texture bucketImage;
    Texture controllerImage;
    Sound dropSound;
    Music rainMusic;
    Rectangle bucket;
    Rectangle square;
    Vector3 touchPos;
    Vector3 touchPosControl;
    Array<Item> raindrops;
    long lastDropTime;
    int dropsGatchered;
    boolean pause;
    Texture background;
    int lives = 1;
    Texture digimon_1;
    Texture digimon_2;
    Texture digimon_3;
    Texture digimon_4;
    Texture digimon_5;
    long startTime = 0;
    int nexLevel = 0;
    float k = 1;
    int first = 1000000000;
    int lvl = 0;
    private IActivityRequestHandler handler;


    public GameScreen(Drop gam, Texture[] myarray, IActivityRequestHandler handler) {
        this.handler = handler;

        skin = new Skin(Gdx.files.internal("uiskin.json"));


        startTime = TimeUtils.nanoTime();

        this.game = gam;

        background = new Texture("seventh.jpg");


        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);





        batch = new SpriteBatch();
        dropImage = new Texture("coine.png");
        bucketImage = new Texture("wallet2.png");
        controllerImage = new Texture("button.png");
        dropSound = Gdx.audio.newSound(Gdx.files.internal("sonic.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("goodresult.wav"));
        rainMusic.setLooping(false);
        rainMusic.setVolume(0.5f);

        digimon_1 = myarray[0];
        digimon_2 = myarray[1];
        digimon_3 = myarray[2];
        digimon_4 = myarray[3];
        digimon_5 = myarray[4];

        touchPos = new Vector3();
        touchPosControl = new Vector3();

        bucket = new Rectangle();
        bucket.x = 800 / 2 - 64 / 2;

        bucket.y = 40;
        bucket.width = 100;
        bucket.height = 100;


        square = new Rectangle();
        square.x = 1000;
        square.y = 55;
        square.width = 50;
        square.height = 50;

        raindrops = new Array<Item>();
        spawnRaindrop();


    }

    private void spawnRaindrop() {
        int rand = MathUtils.random(400, 1000);

        Item raindrop = new Item(MathUtils.random(58, 1150 - 110), 1000, 110, 151, rand);
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }


    public void render(float delta) {
            Gdx.gl.glClearColor(0, 0, 0.2f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


            camera.update();

            game.batch.setProjectionMatrix(camera.combined);
            game.batch.begin();
            game.batch.draw(background, 0, 0);


            game.batch.draw(bucketImage, bucket.x, bucket.y);
            game.batch.draw(controllerImage, square.x, square.y);
            for (Rectangle raindrop : raindrops) {
                game.batch.draw(dropImage, raindrop.x, raindrop.y);
            }

            game.font.draw(game.batch, dropsGatchered + "", 100, 1000);
            if (dropsGatchered == 2000) {
                lvl = 1;
            } else if (dropsGatchered == 4000) {
                lvl = 2;
            } else if (dropsGatchered == 6000) {
                lvl = 3;
            } else if (dropsGatchered == 8000) {
                lvl = 4;
            } else if (dropsGatchered == 10000) {
                lvl = 5;
                game.setScreen(new WinScreen(game, handler));

            }

            game.font.draw(game.batch, lvl + "/5 lvl", 1000, 1000);


            if (nexLevel < 2000) {
                game.batch.draw(digimon_1, 1330, 350);


            } else if (nexLevel == 2000) {
                pause = true;
                game.batch.draw(digimon_2, 1330, 350);
                evolution();
                rainMusic.play();
                first = 800000000;


            } else if (nexLevel > 2000 & nexLevel < 4100) {
                game.batch.draw(digimon_2, 1330, 350);
                pause = false;


            } else if (nexLevel == 4100) {
                pause = true;
                game.batch.draw(digimon_3, 1330, 350);
                evolution();
                rainMusic.play();
                first = 600000000;


            } else if (nexLevel > 4100 & nexLevel < 6200) {
                game.batch.draw(digimon_3, 1330, 350);
                pause = false;


            } else if (nexLevel == 6200) {
                pause = true;
                game.batch.draw(digimon_4, 1330, 350);
                evolution();
                rainMusic.play();
                first = 500000000;

            } else if (nexLevel > 6200 & nexLevel < 8300) {
                game.batch.draw(digimon_4, 1330, 350);
                pause = false;
                first = 400000000;


            } else if (nexLevel > 8300) {
                pause = true;
                game.batch.draw(digimon_5, 1330, 350);
                first = 350000000;
            }



            if (lives < 1) {
                game.setScreen(new GameOver(game, handler));

            }


            game.batch.end();

            if (Gdx.input.isTouched()) {

                touchPosControl.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touchPosControl);
                square.x = (int) (touchPosControl.x);


                touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touchPos);
                bucket.x = (int) (touchPos.x * 3.55 - 4860);

            }


            if (bucket.x < 58) bucket.x = 58;
            if (bucket.x > 1115) bucket.x = 1115;

            if (square.x < 1390) square.x = 1390;
            if (square.x > 1680) square.x = 1680;

            if (pause) {

            } else {
                update();
            }




    }

    public void update() {

        if ((TimeUtils.nanoTime() - lastDropTime) > first) spawnRaindrop();
        Iterator<Item> iter = raindrops.iterator();
        while (iter.hasNext()) {

            Item raindrop = iter.next();
            jj++;
            if (jj == 1) iter.remove();

            Gdx.app.log("WARN",String.valueOf(jj));


            raindrop.y -= raindrop.rand * (Gdx.graphics.getDeltaTime()) * k;
            if (raindrop.y - 30 < 0) {

                lives--;
            }

            if (raindrop.overlaps(bucket)) {
                dropsGatchered = dropsGatchered + 100;
                nexLevel = nexLevel + 100;
                dropSound.play();
                iter.remove();
            }
        }


    }



    public void evolution() {
        Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (nexLevel == 2000) {
                    nexLevel = nexLevel + 100;
                } else if (nexLevel == 4100) {
                    nexLevel = nexLevel + 100;
                } else if (nexLevel == 6200) {
                    nexLevel = nexLevel + 100;
                }
                System.out.println(nexLevel);
            }
        }, 2000L);


    }


    @Override
    public void show() {

handler.showAds(false);

    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        dropImage.dispose();
        batch.dispose();
        bucketImage.dispose();
        dropSound.dispose();
        rainMusic.dispose();
        digimon_1.dispose();
        digimon_2.dispose();
        digimon_3.dispose();
        digimon_4.dispose();
        digimon_5.dispose();
        background.dispose();

    }
}
