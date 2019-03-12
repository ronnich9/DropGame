package com.fut.log;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class Drop extends Game implements Runnable{

    SpriteBatch batch;
    BitmapFont font;
    IActivityRequestHandler handler;

    public Drop(IActivityRequestHandler handler){
        this.handler = handler;

    }

    @Override
    public void create() {

        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("myfont.fnt"));
        this.setScreen(new MainMenuScreen(this, handler));


        handler.showAds(false);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        font.dispose();
    }

    @Override
    public void run() {

    }
}
