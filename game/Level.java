package com.prog.crystalknight.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.prog.crystalknight.game.objects.AbstractGameObject;
import com.prog.crystalknight.game.objects.Clouds;
import com.prog.crystalknight.game.objects.Mountains;
import com.prog.crystalknight.game.objects.Rock;
import com.prog.crystalknight.game.objects.WaterOverlay;

public class Level {
	public static final String TAG = Level.class.getName();
	
	public enum BLOCK_TYPE{
		EMPTY(0, 0, 0), // black
		ROCK(0, 255, 0), // green
		PLAYER_SPAWNPOINT(255, 255, 255),
		ITEM_GOLD_COIN(255, 255, 0);
		
		private int color;
		
		private BLOCK_TYPE(int r, int g, int b) {
			color = r << 24 | g << 16 | b << 8 | 0xff;
		}
		
		public boolean sameColor(int color) {
			return this.color == color;
		}
		
		public int getColor() {
			return color;
		}
		
	}
	
	// object
	public Array<Rock> rocks;
	
	// decoration
	public Clouds clouds;
	public Mountains mountains;
	public WaterOverlay waterOverlay;
	
	public Level(String filename) {
		init(filename);
	}
		
	private void init(String filename) {
		// objects
		rocks = new Array<Rock>();
		
		// load image file
		Pixmap pixmap = new Pixmap(Gdx.files.internal(filename));
		
		// scan color
		int lastPixel = -1;
		
		for(int pixelY = 0; pixelY < pixmap.getHeight(); pixelY++) {
			for(int pixelX = 0; pixelX < pixmap.getWidth(); pixelX++) {
				AbstractGameObject obj = null;
				float offsetHeight = 0;
				
				// height grows from bottom to top
				float baseHeight = pixmap.getHeight() - pixelY;
				// get color of current pixel
				int currentPixel = pixmap.getPixel(pixelX, pixelY);
				// find matching color
				
				// empty
				if(BLOCK_TYPE.EMPTY.sameColor(currentPixel)) {
					// nothing
				}
				
				// rock
				if(BLOCK_TYPE.ROCK.sameColor(currentPixel)) {
					if(lastPixel != currentPixel) {
						obj = new Rock();
						float heightIncreaseFactor = 0.25f;
						offsetHeight = -2.5f;
						obj.position.set(pixelX, baseHeight * obj.dimension.y * heightIncreaseFactor + offsetHeight);
						rocks.add((Rock)obj);
						
					} else {
						rocks.get(rocks.size - 1).increaseLength(1);
					}
				}
				// spawn point
				else if (BLOCK_TYPE.PLAYER_SPAWNPOINT.sameColor(currentPixel)) {
					
				}
				// crystal
				else if (BLOCK_TYPE.ITEM_GOLD_COIN.sameColor(currentPixel)) {
					
				}
				// unknown objects
				else {
					int r = 0xff & (currentPixel >>> 24); // red
					int g = 0xff & (currentPixel >>> 16); // green
					int b = 0xff & (currentPixel >>> 8); // blue
					int a = 0xff & currentPixel; // alpha
					Gdx.app.error(TAG, "Unknown object at x : " + pixelX + ", y : " + pixelY + ", r : " + r + ", g : " + g + ", b : " + b + ", a : " + a);
				}
				lastPixel = currentPixel;
			}
		}
		
		// decoration
		clouds = new Clouds(pixmap.getWidth());
		clouds.position.set(0, 2);
		mountains = new Mountains(pixmap.getWidth());
		mountains.position.set(-1, -1);
		waterOverlay = new WaterOverlay(pixmap.getWidth());
		waterOverlay.position.set(0, -3.75f);
		
		// free 
		pixmap.dispose();
		Gdx.app.debug(TAG, "level : " + filename + " loaded");
		
	}
	
	public void render(SpriteBatch batch) {
		// draw mountains
		mountains.render(batch);
		
		// rocks
		for(Rock rock : rocks) {
			rock.render(batch);
		}
		
		// water
		waterOverlay.render(batch);
		
		// cloud
		clouds.render(batch);
		
	}
}
