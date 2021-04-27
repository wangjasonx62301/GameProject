package com.prog.crystalknight.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.prog.crystalknight.util.CameraHelper;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.prog.crystalknight.game.objects.Rock;
import com.prog.crystalknight.util.Constants;


public class WorldController extends InputAdapter{
	
	private static final String TAG = WorldController.class.getName();
	
	// for testing
	// public Sprite[] testSprites;
	// public int selectedSprite;
	
	
	// debugging camera
	public CameraHelper cameraHelper;
	
	// for level
	public Level level;
	public int lives;
	public int score;
	
	public WorldController() {
		init();
	}
	
	public void initLevel() {
		score = 0;
		level = new Level(Constants.LEVEL_01);
	}
	
	private void init() {
		Gdx.input.setInputProcessor(this);
		cameraHelper = new CameraHelper();
		lives = Constants.LIVES_START;
		initLevel();
		// initTestObjects();
	}
	
	// for testing only
	/**
	private void initTestObjects() {
		// create new 5 sprites
		testSprites = new Sprite[5];
		
		// create texture regions for testing
		
		Array<TextureRegion> regions = new Array<TextureRegion>();
		regions.add(Assets.instance.knight.knight);
		regions.add(Assets.instance.crystalCoin.crystalCoin);
		
		
		
		// create  new sprites using texture region
		for(int i = 0; i < testSprites.length; i++) {
			
			Sprite spr = new Sprite(regions.random());
			
			// define sprite size to be 1m x 1m 
			spr.setSize(1, 1);
			
			// set origin to sprite's center
			spr.setOrigin(spr.getWidth() / 2.0f, spr.getHeight() / 2.0f);
			
			// calculate random position for sprite
			float randomX = MathUtils.random(-2.0f, 2.0f);
			float randomY = MathUtils.random(-2.0f, 2.0f);
			spr.setPosition(randomX, randomY);
			
			// put new sprite into array
			
			testSprites[i] = spr;
		}
		
		// set first sprite as selected one
		selectedSprite = 0;
		
	}
	*/
	
	// same for testing
	
	private Pixmap createProceduralPixmap(int width, int height) {
		Pixmap pixmap = new Pixmap(width, height, Format.RGB888);
		
		// fill square color
		
		pixmap.setColor(1.0f, 0.5f, 0.5f, 0.5f);
		pixmap.fill();
		
		// draw a X shape
		pixmap.setColor(1, 0, 1, 1);
		pixmap.drawLine(0, 0, width, height);
		pixmap.drawLine(width, 0, 0, height);
		
		// draw a cyan-colored border
		pixmap.setColor(0, 0.5f, 0.5f, 1);
		pixmap.drawRectangle(0, 0, width, height);
		return pixmap;
		
	}
	
	public void update(float deltaTime) {
		handleDebugInput(deltaTime);
		// updateTestObjects(deltaTime);
		cameraHelper.update(deltaTime);
	}
	
	// test
	/**
	private void updateTestObjects(float deltaTime) {
		// get current rotation from selected
		float rotation = testSprites[selectedSprite].getRotation();
		// rotate sprite by 90 degrees per second
		rotation += 90 * deltaTime;
		// wrap around at 360 degrees
		rotation %= 360;
		// set new rotation value to selected sprite
		testSprites[selectedSprite].setRotation(rotation);
	}
	*/
	
	// testing
	
	private void handleDebugInput(float deltaTime) {
		if(Gdx.app.getType() != ApplicationType.Desktop) {
			return;
		}
		
		// selected sprite controls
		
		float sprMoveSpeed = 5 * deltaTime;
		
		// camera move
		float camMoveSpeed = 5 * deltaTime;
		float camMoveSpeedAccelerationFactor = 5;
		if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
			camMoveSpeed *= camMoveSpeedAccelerationFactor ;
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			moveCamera(-camMoveSpeed, 0);
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			moveCamera(camMoveSpeed, 0);	
		}
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			moveCamera(0, camMoveSpeed);		
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			moveCamera(0, -camMoveSpeed);		
		}
		
		if(Gdx.input.isKeyPressed(Keys.BACKSPACE)) {
			cameraHelper.setPosition(0, 0);
		}
		
		// zoom in/out
		
		float camZoomSpeed = 1 * deltaTime;
		float camZoomSpeedAccelerationFactor = 5;
		if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
			camZoomSpeed *= camZoomSpeedAccelerationFactor;
		}
		if(Gdx.input.isKeyPressed(Keys.COMMA)) {
			cameraHelper.addZoom(camZoomSpeed);
		}
		if(Gdx.input.isKeyPressed(Keys.PERIOD)) {
			cameraHelper.addZoom(-camZoomSpeed);
		}
		if(Gdx.input.isKeyPressed(Keys.SLASH)) {
			cameraHelper.setZoom(1);
		}
		
		
		
	}
	
	private void moveCamera(float x, float y) {
		x += cameraHelper.getPosition().x;
		y += cameraHelper.getPosition().y;
		cameraHelper.setPosition(x, y);
	} 
	
	
	// test
	/**
	private void moveSelectedSprite(float x, float y) {
		testSprites[selectedSprite].translate(x, y);
	}
	*/
	
	@Override
	public boolean keyUp(int keycode) {
		
		// reset 
		if(keycode == Keys.R) {
			init();
			Gdx.app.debug(TAG, "GAme reset");
		}
		
		// select next sprite
		/**
		else if(keycode == Keys.SPACE) {
			selectedSprite = (selectedSprite + 1) % testSprites.length;
			// update camera to follow
			if(cameraHelper.hasTarget()) {
				cameraHelper.setTarget(testSprites[selectedSprite]);
			}
			Gdx.app.debug(TAG, "Sprite #" + selectedSprite + " selected");
		}
		*/
		
		// toggle camera
		
		else if (keycode == Keys.ENTER) {
			// cameraHelper.setTarget(cameraHelper.hasTarget() ? null : testSprites[selectedSprite]);
			Gdx.app.debug(TAG, "Camera follow enabled : " + cameraHelper.hasTarget());
		}
		
		return false;
	}
	
}
