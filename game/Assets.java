package com.prog.crystalknight.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.prog.crystalknight.util.Constants;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;


public class Assets implements Disposable, AssetErrorListener{
	
	public static final String TAG = Assets.class.getName();
	
	// objects calling
	public static final Assets instance = new Assets();
	
	private AssetManager assetManager;
	
	// prevent instantiation from  other class
	private Assets() {
		
	}
	
	// object 
	public AssetKnight knight;
	public AssetRock rock;
	public AssetCoin crystalCoin;
	public AssetLevelDecoration levelDecoration;
	
	
	public void init(AssetManager assetManager) {
		this.assetManager = assetManager;
		
		// set manager error handler
		assetManager.setErrorListener(this);
		
		// loading atlas
		assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
		
		// start loading and wait until finished
		assetManager.finishLoading();
		Gdx.app.debug(TAG, "# of assets load : " + assetManager.getAssetNames().size);
		
		for(String a : assetManager.getAssetNames()) {
			Gdx.app.debug(TAG, "asset : " + a);
		}
		
		TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
		
		// enable filtering for pixel smoothing
		for(Texture t : atlas.getTextures()) {
			t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
		// create objects
		knight = new AssetKnight(atlas);
		rock = new AssetRock(atlas);
		crystalCoin = new AssetCoin(atlas);
		levelDecoration = new AssetLevelDecoration(atlas);
		
	}
	
	@Override
	public void dispose() {
		assetManager.dispose();
	}
	
	// debugging
	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		Gdx.app.error(TAG, "Couldn't loadd asset '" + asset.fileName + "'", (Exception)throwable);
	}

	// All objects
	
	public class AssetKnight{
		public final AtlasRegion knight;
		
		public AssetKnight(TextureAtlas atlas) {
			knight = atlas.findRegion("knight");
		}
	}

	public class AssetRock{
		public final AtlasRegion edge;
		public final AtlasRegion middle;
		public AssetRock(TextureAtlas atlas) {
			edge = atlas.findRegion("rock_edge");
			middle = atlas.findRegion("rock_middle");
		}
	}
	
	public class AssetCoin{
		public final AtlasRegion crystalCoin;
		
		public AssetCoin(TextureAtlas atlas) {
			crystalCoin = atlas.findRegion("coin");
		}
	}
	
	public class AssetLevelDecoration{
		public final AtlasRegion cloud01;
		public final AtlasRegion cloud02;
		public final AtlasRegion cloud03;
		public final AtlasRegion cloud04;
		public final AtlasRegion mountain01;
		public final AtlasRegion mountain02;
		public final AtlasRegion waterOverlay;
		
		public AssetLevelDecoration(TextureAtlas atlas) {
			cloud01 = atlas.findRegion("cloud01");
			cloud02 = atlas.findRegion("cloud02");
			cloud03 = atlas.findRegion("cloud03");
			cloud04 = atlas.findRegion("cloud04");
			mountain01 = atlas.findRegion("Wasteland_Mountains01");
			mountain02 = atlas.findRegion("Wasteland_Mountains02");
			waterOverlay = atlas.findRegion("waterOverFlow");
		}
	}
	
}
