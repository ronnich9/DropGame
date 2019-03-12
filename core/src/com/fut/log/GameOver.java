package com.fut.log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;



public class GameOver implements Screen {

    Stage stage;
    final Drop game;
    OrthographicCamera camera;
    Texture background;
    private Texture button_pressed;
    private Texture button_active;
    private Texture button_pressed_rate;
    private Texture button_active_rate;
    ImageButton imageButton;
    ImageButton imageButton_rate;
    MainMenuScreen mainMenuScreen;

    Integer worldTimer;
    float timeCount;

    IActivityRequestHandler handler;
    Label loadingLabel;
    Skin skin;




    public GameOver(Drop gam, IActivityRequestHandler handler) {

        worldTimer = 3;
        timeCount = 0;

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        this.handler = handler;
        this.game = gam;
        mainMenuScreen = new MainMenuScreen(gam, handler);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
        background = new Texture("game_over.png");
    }


    @Override
    public void show() {

        handler.showAds(true);

        stage = new Stage(new FitViewport(1920, 1080, camera));

        loadingLabel = new Label("Loading (0:0" + worldTimer + ")", skin);
        loadingLabel.setPosition(700, 20);

        Gdx.input.setInputProcessor(stage);

        button_active = new Texture("new_game_u.png");
        button_pressed = new Texture("new_game_d.png");

        button_active_rate = new Texture("rate_us_up.png");
        button_pressed_rate = new Texture("rate_us_down.png");

        imageButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(button_active)),
                new TextureRegionDrawable(new TextureRegion(button_pressed)));
        imageButton.setPosition(50, 25);
        imageButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game, handler));
                dispose();

            }
        });

        imageButton_rate = new ImageButton(new TextureRegionDrawable(new TextureRegion(button_active_rate)),
                new TextureRegionDrawable(new TextureRegion(button_pressed_rate)));
        imageButton_rate.setPosition(1530, 700);
        imageButton_rate.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    Gdx.net.openURI("market://details?id=com.fut.log");
                } catch (Exception e) {
                    Gdx.net.openURI("http://play.google.com/store/apps/details?id=com.fut.log");
                }

            }
        });

        imageButton.setVisible(false);

        stage.addActor(imageButton);
        stage.addActor(loadingLabel);
        stage.addActor(imageButton_rate);


    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);
        stage.act(Gdx.graphics.getDeltaTime());

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.font.draw(game.batch, "YOU LOST", 800, 1015);
        game.batch.end();

        stage.draw();
    }

    public void update (float dt) {
        timeCount += dt;
        if (timeCount >= 1 & worldTimer >=0) {
            worldTimer--;
            loadingLabel.setText("Loading (0:0" + worldTimer + ")");
            timeCount = 0;
        }

        if (worldTimer < 0) {
            loadingLabel.setText("");
            imageButton.setVisible(true);
        }

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
        stage.dispose();
        background.dispose();
        button_active.dispose();
        button_pressed.dispose();
        button_active_rate.dispose();
        button_pressed_rate.dispose();

    }
}
