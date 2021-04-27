package com.prog.crystalknight.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.prog.crystalknight.game.Assets;

public class WaterOverlay extends AbstractGameObject{
	
	private TextureRegion regWaterOverlay;
	private int length;
	
	public WaterOverlay(int length) {
		this.length = length;
		init();
	}
	
	private void drawWater(SpriteBatch batch, float offsetX, float offsetY) {
		TextureRegion reg = null;
		
		float xRel = dimension.x * offsetX;
		float yRel = dimension.y * offsetY;
		
		// water span
		int waterLength = 0;
		waterLength += MathUtils.ceil(length / (2 * dimension.x));
		waterLength += MathUtils.ceil(0.5f + offsetX);
		for(int i = -5; i < waterLength; i++) {
			reg = regWaterOverlay;
			batch.draw(reg.getTexture(), origin.x + xRel - 10, position.y + origin.y + yRel + 0.1f, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
		
			xRel += dimension.x;
		}
	}
	
	private void init() {
		dimension.set(15, 2);
		
		regWaterOverlay = Assets.instance.levelDecoration.waterOverlay;
		
		origin.x = -dimension.x * 2;
		length += dimension.x * 2;
	}
	
	
	
	@Override
	public void render(SpriteBatch batch) {
		drawWater(batch, 0.5f, 0.5f);
	}
	
}
