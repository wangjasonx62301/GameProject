package com.prog.crystalknight.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.prog.crystalknight.game.Assets;

public class Mountains extends AbstractGameObject{
	
	private TextureRegion regMountain01;
	private TextureRegion regMountain02;
	
	private int length;
	
	public Mountains(int length) {
		this.length = length;
		init();
	}
	
	public void init() {
		dimension.set(10, 2);
		
		regMountain01 = Assets.instance.levelDecoration.mountain01;
		regMountain02 = Assets.instance.levelDecoration.mountain02;
	
		// shift mountain + extends length
		origin.x = -dimension.x * 2;
		length += dimension.x * 2;
	}
	
	private void drawMountain(SpriteBatch batch, float offsetX, float offsetY) {
		TextureRegion reg = null;
		
		float xRel = dimension.x * offsetX;
		float yRel = dimension.y * offsetY;
		
		// mountain span whole level
		int mountainLength = 0;
		mountainLength += MathUtils.ceil(length / (2 * dimension.x));
		mountainLength += MathUtils.ceil(0.5f + offsetX);
		for(int i = 0; i < mountainLength + 6; i++) {
			// draw front
			reg = regMountain01;
			batch.draw(reg.getTexture(), origin.x + xRel, position.y + origin.y + yRel - 0.6f, origin.x, origin.y, dimension.x + 0.02f, dimension.y + 4, scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
			// draw back
			reg = regMountain02;
			batch.draw(reg.getTexture(), origin.x + xRel, position.y + origin.y + yRel - 1.2f, origin.x, origin.y, dimension.x + 0.02f, dimension.y + 3, scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
			
			xRel += dimension.x;
		}
	}
	
	@Override
	public void render(SpriteBatch batch) {
		drawMountain(batch, 0.5f, 0.5f);
	}
}
