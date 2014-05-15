package com.kulapps.jump;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Singleton class Responsible to load all game related resources
 * 
 * @author San4
 */
public class ResourceManager {
	// Singleton instance
	private static final ResourceManager mInstance = new ResourceManager();

	public Engine mEngine;
	public GameActivity mActivity;
	public Camera mCamera;
	public VertexBufferObjectManager mVBOM;

	// ---------------------------------------------
	// ------- Textures and texture regions --------	
	// ---------------------------------------------
	public ITextureRegion mSplash_region;
	private BitmapTextureAtlas mSplashTextureAtlas;
	
	
	// ---------------------------------------------
	// CLASS LOGIC
	// ---------------------------------------------

	public void loadMenuResources() {
		loadMenuGraphics();
		loadMenuAudio();
	}

	public void loadGameResources() {
		loadGameGraphics();
		loadGameFonts();
		loadGameAudio();
	}

	private void loadMenuGraphics() {

	}

	private void loadMenuAudio() {

	}

	private void loadGameGraphics() {

	}

	private void loadGameFonts() {

	}

	private void loadGameAudio() {

	}

	/**
	 * Loads splash screen image
	 */
	public void loadSplashScreen() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		mSplashTextureAtlas = new BitmapTextureAtlas(mActivity.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
		mSplash_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mSplashTextureAtlas, mActivity, "splash.png", 0, 0);
		mSplashTextureAtlas.load();
	}

	/**
	 * Unloads splash screen graphics
	 */
	public void unloadSplashScreen() {
		mSplashTextureAtlas.unload();
		mSplash_region = null;
	}

	/**
	 * @param engine
	 * @param activity
	 * @param camera
	 * @param vbom
	 *            We use this method at beginning of game loading, to prepare
	 *            Resources Manager properly, setting all needed parameters, so
	 *            we can latter access them from different classes (eg. scenes)
	 */
	public static void prepareManager(Engine engine, GameActivity activity,
			Camera camera, VertexBufferObjectManager vbom) {
		getInstance().mEngine = engine;
		getInstance().mActivity = activity;
		getInstance().mCamera = camera;
		getInstance().mVBOM = vbom;
	}

	/**
	 * Singleton getter
	 * 
	 * @return ResourceManager instance
	 */
	public static ResourceManager getInstance() {
		return mInstance;
	}
}
