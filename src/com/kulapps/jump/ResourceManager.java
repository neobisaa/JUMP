package com.kulapps.jump;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import android.graphics.Color;

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
	public BoundCamera mCamera;
	public VertexBufferObjectManager mVBOM;

	// ---------------------------------------------
	// ------- Textures and texture regions --------	
	// ---------------------------------------------
	
	// SPLASH SCENE 
	public ITextureRegion mSplash_region;
	private BitmapTextureAtlas mSplashTextureAtlas;
	
	// MENU SCENE
	public ITextureRegion menu_background_region;
	public ITextureRegion play_region;
	public ITextureRegion options_region;
	private BuildableBitmapTextureAtlas menuTextureAtlas;
	
	// LOADING SCENE
	public Font font;
		
	// GAME SCENE 
	// Textures
	public BuildableBitmapTextureAtlas gameTextureAtlas;
	    
	// Texture Regions
	public ITextureRegion platform1_region;
	public ITextureRegion platform2_region;
	public ITextureRegion platform3_region;
	public ITextureRegion coin_region;
	
	// Player 
	public ITiledTextureRegion player_region;
	
	// Level Complete
	public ITextureRegion complete_window_region;
	public ITiledTextureRegion complete_stars_region;
	
	// ------------------------------------
	// --------  MENU Resources -----------	
	// ------------------------------------
	
	/**
	 * Load all Menu resources
	 */
	public void loadMenuResources() {
		loadMenuGraphics();
		loadMenuFonts();
		loadMenuAudio();
	}

	/**
	 * Loads menu graphics
	 */
	private void loadMenuGraphics() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
		menuTextureAtlas = new BuildableBitmapTextureAtlas(mActivity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		menu_background_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, mActivity, "menu_back.png");
		play_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, mActivity, "play.png");
		options_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, mActivity, "options.png");
		       
		try 
		{
		    this.menuTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
		    this.menuTextureAtlas.load();
		} 
		catch (final TextureAtlasBuilderException e)
		{
		        Debug.e(e);
		}
	}

	/**
	 * Loads menu audio
	 */
	private void loadMenuAudio() {

	}
	
	/**
	 * Loads font for "Loading" scene
	 */
	private void loadMenuFonts()
	{
	    FontFactory.setAssetBasePath("fonts/");
	    final ITexture mainFontTexture = new BitmapTextureAtlas(mActivity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

	    font = FontFactory.createStrokeFromAsset(mActivity.getFontManager(), mainFontTexture, mActivity.getAssets(), "comicate.ttf", 50, true, Color.WHITE, 2, Color.BLACK);
	    font.load();
	}

	/**
	 * Unload Menu textures
	 */
	public void unloadMenuTextures()
	{
	    menuTextureAtlas.unload();
	}
	    
	/**
	 * Load menu textures
	 */
	public void loadMenuTextures()
	{
	    menuTextureAtlas.load();
	}
	
	// ------------------------------------
	// --------  GAME Resources -----------
	// ------------------------------------
	
	/**
	 * Loads all game resources
	 */
	public void loadGameResources() {
		loadGameGraphics();
		loadGameFonts();
		loadGameAudio();
	}
	
	/**
	 * Loads game textures
	 */
	private void loadGameGraphics() {
		 BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game/");
		    gameTextureAtlas = new BuildableBitmapTextureAtlas(mActivity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		    
		    platform1_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, mActivity, "platform1.png");
		    platform2_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, mActivity, "platform2.png");
		    platform3_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, mActivity, "platform3.png");
		    coin_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, mActivity, "coin.png");
		    complete_window_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, mActivity, "complete.png");
		    complete_stars_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, mActivity, "star.png", 2, 1);
		    player_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, mActivity, "player.png", 3, 1);
		   
		    try 
		    {
		        this.gameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
		        this.gameTextureAtlas.load();
		    } 
		    catch (final TextureAtlasBuilderException e)
		    {
		        Debug.e(e);
		    }
	}

	/**
	 * Loads game fonts
	 */
	private void loadGameFonts() {

	}

	/**
	 * Loads game audio
	 */
	private void loadGameAudio() {

	}

	/**
	 * Unloads all Game Scene Textures
	 */
	public void unloadGameTextures()
	{
	    // TODO 
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
			BoundCamera camera, VertexBufferObjectManager vbom) {
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
