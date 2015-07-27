package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    TextureRegion sky;
    TextureRegion cloudPreMultiplied;
    TextureRegion cloud;

    @Override
    public void create() {
        batch = new SpriteBatch();
        TextureAtlas atlas = new TextureAtlas("background-1-1-1920.txt");

        sky = atlas.findRegion("sky-bgnd");
        cloudPreMultiplied = atlas.findRegion("cloud");

        atlas = new TextureAtlas("background-2-1-1920.txt");
        cloud = atlas.findRegion("cloud");


    }

    @Override
    public void render() {
        // Make sure alpha is 1
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glColorMask(true, true, true, true);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Don't overwrite the alpha value any more
        Gdx.gl.glColorMask(true, true, true, false);

        batch.begin();

        // Draw cloud with premultiplied alpha
        batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
        batch.draw(cloudPreMultiplied, 20, Gdx.graphics.getHeight() - cloudPreMultiplied.getRegionHeight() - 20);

        // Draw cloud without premultiplied alpha
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        batch.draw(cloud, Gdx.graphics.getWidth() - cloud.getRegionWidth() - 20, Gdx.graphics.getHeight() - cloud.getRegionHeight() - 20);
        batch.end();

        // Double check alpha is still 1
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glColorMask(false, false, false, true);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
