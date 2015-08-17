package com.mygdx.game.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class HtmlLauncher extends GwtApplication {

    @Override
    public GwtApplicationConfiguration getConfig() {
        return new GwtApplicationConfiguration(400, 400);
    }

    @Override
    public ApplicationListener getApplicationListener() {
        return new MyGdxGame();
    }

    @Override
    public void log(String tag, String message) {
        consoleLog(tag + ": " + message);
        super.log(tag, message);
    }
}