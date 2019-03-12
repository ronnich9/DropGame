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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;



public class MainMenuScreen implements Screen {

    Stage stage;
    final Drop game;
    OrthographicCamera camera;
    private Texture button_pressed_real;
    private Texture button_active_real;
    private Texture button_pressed_barca;
    private Texture button_active_barca;
    private Texture button_pressed_bd;
    private Texture button_active_bd;
    private Texture button_pressed_chelsea;
    private Texture button_active_chelsea;
    private Texture button_pressed_ars;
    private Texture button_active_ars;
    private Texture button_pressed_bayern;
    private Texture button_active_bayern;
    private Texture button_pressed_psg;
    private Texture button_active_psg;
    private Texture button_pressed_liver;
    private Texture button_active_liver;
    private Texture button_pressed_juve;
    private Texture button_active_juve;
    private Texture button_pressed_mc;
    private Texture button_active_mc;
    ImageButton imageButton_real;
    ImageButton imageButton_barca;
    ImageButton imageButton_MU;
    ImageButton imageButton_chelsea;
    ImageButton imageButton_ars;
    ImageButton imageButton_bayern;
    ImageButton imageButton_psg;
    ImageButton imageButton_liver;
    ImageButton imageButton_juve;
    ImageButton imageButton_mc;
    Texture digimon_1;
    Texture digimon_2;
    Texture digimon_3;
    Texture digimon_4;
    Texture digimon_5;
    Texture background;


    IActivityRequestHandler handler;


    public MainMenuScreen(Drop gam, IActivityRequestHandler handler) {
        this.handler = handler;
        this.game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);

        background = new Texture("eughth.jpg");

    }

    public Texture [] helperArray(int i) {
        if (i == 2) {
            digimon_1 = new Texture(Gdx.files.internal("barca_1.png"));
            digimon_2 = new Texture(Gdx.files.internal("barca_2.png"));
            digimon_3 = new Texture(Gdx.files.internal("barca_3.png"));
            digimon_4 = new Texture(Gdx.files.internal("barca_4.png"));
            digimon_5 = new Texture(Gdx.files.internal("barca_5.png"));
        } else if (i == 1) {
            digimon_1 = new Texture(Gdx.files.internal("real_1.png"));
            digimon_2 = new Texture(Gdx.files.internal("real_2.png"));
            digimon_3 = new Texture(Gdx.files.internal("real_3.png"));
            digimon_4 = new Texture(Gdx.files.internal("real_4.png"));
            digimon_5 = new Texture(Gdx.files.internal("real_5.png"));
        } else if (i == 3) {
            digimon_1 = new Texture(Gdx.files.internal("bd_1.png"));
            digimon_2 = new Texture(Gdx.files.internal("bd_2.png"));
            digimon_3 = new Texture(Gdx.files.internal("bd_3.png"));
            digimon_4 = new Texture(Gdx.files.internal("bd_4.png"));
            digimon_5 = new Texture(Gdx.files.internal("bd_5.png"));
        } else if (i == 4) {
            digimon_1 = new Texture(Gdx.files.internal("chelsea_1.png"));
            digimon_2 = new Texture(Gdx.files.internal("chelsea_2.png"));
            digimon_3 = new Texture(Gdx.files.internal("chelsea_3.png"));
            digimon_4 = new Texture(Gdx.files.internal("chelsea_4.png"));
            digimon_5 = new Texture(Gdx.files.internal("chelsea_5.png"));
        } else if (i == 5) {
            digimon_1 = new Texture(Gdx.files.internal("ars_1.png"));
            digimon_2 = new Texture(Gdx.files.internal("ars_2.png"));
            digimon_3 = new Texture(Gdx.files.internal("ars_3.png"));
            digimon_4 = new Texture(Gdx.files.internal("ars_4.png"));
            digimon_5 = new Texture(Gdx.files.internal("ars_5.png"));
        } else if (i == 6) {
            digimon_1 = new Texture(Gdx.files.internal("bayern_1.png"));
            digimon_2 = new Texture(Gdx.files.internal("bayern_2.png"));
            digimon_3 = new Texture(Gdx.files.internal("bayern_3.png"));
            digimon_4 = new Texture(Gdx.files.internal("bayern_4.png"));
            digimon_5 = new Texture(Gdx.files.internal("bayern_5.png"));
        } else if (i == 7) {
            digimon_1 = new Texture(Gdx.files.internal("psg_1.png"));
            digimon_2 = new Texture(Gdx.files.internal("psg_2.png"));
            digimon_3 = new Texture(Gdx.files.internal("psg_3.png"));
            digimon_4 = new Texture(Gdx.files.internal("psg_4.png"));
            digimon_5 = new Texture(Gdx.files.internal("psg_5.png"));
        } else if (i == 8) {
            digimon_1 = new Texture(Gdx.files.internal("liver_1.png"));
            digimon_2 = new Texture(Gdx.files.internal("liver_2.png"));
            digimon_3 = new Texture(Gdx.files.internal("liver_3.png"));
            digimon_4 = new Texture(Gdx.files.internal("liver_4.png"));
            digimon_5 = new Texture(Gdx.files.internal("liver_5.png"));
        } else if (i == 9) {
            digimon_1 = new Texture(Gdx.files.internal("juve_1.png"));
            digimon_2 = new Texture(Gdx.files.internal("juve_2.png"));
            digimon_3 = new Texture(Gdx.files.internal("juve_3.png"));
            digimon_4 = new Texture(Gdx.files.internal("juve_4.png"));
            digimon_5 = new Texture(Gdx.files.internal("juve_5.png"));
        } else if (i == 10) {
            digimon_1 = new Texture(Gdx.files.internal("mc_1.png"));
            digimon_2 = new Texture(Gdx.files.internal("mc_2.png"));
            digimon_3 = new Texture(Gdx.files.internal("mc_3.png"));
            digimon_4 = new Texture(Gdx.files.internal("mc_4.png"));
            digimon_5 = new Texture(Gdx.files.internal("mc_5.png"));
        }

        final Texture [] myArray = {digimon_1, digimon_2, digimon_3, digimon_4, digimon_5};

        return myArray;
    }

    @Override
    public void show() {

        handler.showAds(false);

        stage = new Stage(new FitViewport(1920, 1080, camera));

        Gdx.input.setInputProcessor(stage);

        button_active_real = new Texture("real_11.png");
        button_pressed_real = new Texture("real_21.png");

        button_active_barca = new Texture("barca_11.png");
        button_pressed_barca = new Texture("barca_21.png");

        button_active_bd = new Texture("bd_11.png");
        button_pressed_bd = new Texture("bd_21.png");

        button_active_chelsea = new Texture("chelsea_11.png");
        button_pressed_chelsea = new Texture("chelsea_21.png");

        button_active_ars = new Texture("ars_11.png");
        button_pressed_ars = new Texture("ars_21.png");

        button_active_bayern = new Texture("bayern_11.png");
        button_pressed_bayern = new Texture("bayern_21.png");

        button_active_psg = new Texture("psg_11.png");
        button_pressed_psg = new Texture("psg_21.png");

        button_active_liver = new Texture("liver_11.png");
        button_pressed_liver = new Texture("liver_21.png");

        button_active_juve = new Texture("juve_11.png");
        button_pressed_juve = new Texture("juve_21.png");

        button_active_mc = new Texture("mc_11.png");
        button_pressed_mc = new Texture("mc_21.png");


        imageButton_real = new ImageButton(new TextureRegionDrawable(new TextureRegion(button_active_real)),
                new TextureRegionDrawable(new TextureRegion(button_pressed_real)));
        imageButton_real.setPosition(80, 500);
        imageButton_real.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, helperArray(1), handler));
                dispose();

            }
        });

        imageButton_barca = new ImageButton(new TextureRegionDrawable(new TextureRegion(button_active_barca)),
                new TextureRegionDrawable(new TextureRegion(button_pressed_barca)));
        imageButton_barca.setPosition(430, 500);
        imageButton_barca.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, helperArray(2), handler));
                dispose();

            }
        });

        imageButton_MU = new ImageButton(new TextureRegionDrawable(new TextureRegion(button_active_bd)),
                new TextureRegionDrawable(new TextureRegion(button_pressed_bd)));
        imageButton_MU.setPosition(780, 500);
        imageButton_MU.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, helperArray(3), handler));
                dispose();

            }
        });

        imageButton_chelsea = new ImageButton(new TextureRegionDrawable(new TextureRegion(button_active_chelsea)),
                new TextureRegionDrawable(new TextureRegion(button_pressed_chelsea)));
        imageButton_chelsea.setPosition(1130, 500);
        imageButton_chelsea.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, helperArray(4), handler));
                dispose();

            }
        });

        imageButton_ars = new ImageButton(new TextureRegionDrawable(new TextureRegion(button_active_ars)),
                new TextureRegionDrawable(new TextureRegion(button_pressed_ars)));
        imageButton_ars.setPosition(1500, 500);
        imageButton_ars.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, helperArray(5), handler));
                dispose();

            }
        });

        imageButton_bayern = new ImageButton(new TextureRegionDrawable(new TextureRegion(button_active_bayern)),
                new TextureRegionDrawable(new TextureRegion(button_pressed_bayern)));
        imageButton_bayern.setPosition(80, 100);
        imageButton_bayern.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, helperArray(6), handler));
                dispose();

            }
        });

        imageButton_psg = new ImageButton(new TextureRegionDrawable(new TextureRegion(button_active_psg)),
                new TextureRegionDrawable(new TextureRegion(button_pressed_psg)));
        imageButton_psg.setPosition(430, 100);
        imageButton_psg.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, helperArray(7), handler));
                dispose();

            }
        });

        imageButton_liver = new ImageButton(new TextureRegionDrawable(new TextureRegion(button_active_liver)),
                new TextureRegionDrawable(new TextureRegion(button_pressed_liver)));
        imageButton_liver.setPosition(780, 100);
        imageButton_liver.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, helperArray(8), handler));
                dispose();

            }
        });

        imageButton_juve = new ImageButton(new TextureRegionDrawable(new TextureRegion(button_active_juve)),
                new TextureRegionDrawable(new TextureRegion(button_pressed_juve)));
        imageButton_juve.setPosition(1130, 100);
        imageButton_juve.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, helperArray(9), handler));
                dispose();

            }
        });

        imageButton_mc = new ImageButton(new TextureRegionDrawable(new TextureRegion(button_active_mc)),
                new TextureRegionDrawable(new TextureRegion(button_pressed_mc)));
        imageButton_mc.setPosition(1500, 100);
        imageButton_mc.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, helperArray(10), handler));
                dispose();

            }
        });

        stage.addActor(imageButton_real);
        stage.addActor(imageButton_barca);
        stage.addActor(imageButton_MU);
        stage.addActor(imageButton_chelsea);
        stage.addActor(imageButton_ars);
        stage.addActor(imageButton_bayern);
        stage.addActor(imageButton_psg);
        stage.addActor(imageButton_liver);
        stage.addActor(imageButton_juve);
        stage.addActor(imageButton_mc);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.batch.end();


        stage.draw();

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

    }
}
