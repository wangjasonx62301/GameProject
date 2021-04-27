package com.prog.crystalknight;

import com.badlogic.gdx.ApplicationListener;
import com.prog.crystalknight.game.WorldController;
import com.prog.crystalknight.game.WorldRenderer;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.assets.AssetManager;
import com.prog.crystalknight.game.Assets;

public class CrystalKnightMain implements ApplicationListener{
	
	private static final String TAG = CrystalKnightMain.class.getName();
	
	private WorldController worldController;
	private WorldRenderer worldRenderer;
	
	// For android
	private boolean paused;
	
	@Override 
	public void create() {
		
		// Set libgdx log level to debug
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		
		// load assets (test)
		Assets.instance.init(new AssetManager());
		
		
		// initialize controller and renderer
		
		worldController = new WorldController();
		worldRenderer = new WorldRenderer(worldController);
		
		// Game world is active
		paused = false;
		
		
	}
	
	@Override
	public void render() {
		
		// don't update when paused
		if(!paused) {
			// update game world by the time that has passed
			// since last rendered frame
			worldController.update(Gdx.graphics.getDeltaTime());
		}
		// set the clear screen color
		Gdx.gl.glClearColor(113.0f / 255.0f, 54.0f / 255.0f, 81.0f / 255.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);  // put color on screen
	
		// Render game world to screen
		worldRenderer.render();
	}
	
	@Override
	public void dispose() {
		worldRenderer.dispose();
		Assets.instance.dispose();
	}
	
	@Override
	public void resize(int width, int height) {
		
		worldRenderer.resize(width, height);
		
	}
	
	@Override
	public void pause() {
		paused = true;
	}
	
	@Override
	public void resume() {
		paused = false;
	}
}

