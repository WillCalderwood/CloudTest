package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

    public class MyGdxGame extends ApplicationAdapter {
        SpriteBatch batch;
        Texture testTexture;
        private ShapeRenderer shapeRenderer;
        private FrameBuffer fb;

        @Override
        public void create() {
            batch = new SpriteBatch();
            shapeRenderer = new ShapeRenderer();
            fb = new FrameBuffer(Pixmap.Format.RGBA8888,
                    Gdx.graphics.getWidth(),
                    Gdx.graphics.getHeight(),
                    false);

            fb.begin();
            drawGradient();
    //        PixmapIO.writePNG(Gdx.files.local("test.png"),
    //                ScreenUtils.getFrameBufferPixmap(0, 0, fb.getWidth(), fb.getHeight()));
            fb.end();

            testTexture = new Texture(Gdx.files.internal("test.png"));
        }

        private void drawGradient() {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

            for (int i = 0; i < Gdx.graphics.getHeight(); i++) {
                float alpha = (float) i / Gdx.graphics.getHeight();
                shapeRenderer.setColor(1f * alpha, 1f * alpha, 1f * alpha, alpha);
                shapeRenderer.line(0, i, Gdx.graphics.getWidth(), i);
            }
            shapeRenderer.end();
        }

        @Override
        public void render() {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            batch.begin();
            batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
            batch.draw(testTexture,
                    0,
                    Gdx.graphics.getHeight(),
                    Gdx.graphics.getWidth() * 0.475f,
                    -Gdx.graphics.getHeight());

            batch.draw(fb.getColorBufferTexture(),
                    Gdx.graphics.getWidth() * 0.525f,
                    Gdx.graphics.getHeight(),
                    Gdx.graphics.getWidth() * 0.475f,
                    -Gdx.graphics.getHeight());

            batch.end();
        }
    }
