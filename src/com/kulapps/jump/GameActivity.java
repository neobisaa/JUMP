package com.kulapps.jump;

import java.io.IOException;

import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;

/**
 * Main Game Activity - launched at app start
 * 
 * @author San4
 * 
 */
public class GameActivity extends BaseGameActivity {
	
	// members
	private Camera mCamera;

	// ResourceManager
	private ResourceManager mResMgr;

	
	public EngineOptions onCreateEngineOptions() {
		mCamera = new Camera(0, 0, GAME.SCREEN_WIDTH, GAME.SCREEN_HEIGHT);
		EngineOptions engineOptions = new EngineOptions(true,
				ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(
						800, 480), this.mCamera);
		engineOptions.getAudioOptions().setNeedsMusic(true).setNeedsSound(true);
		engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
		return engineOptions;
	}

	public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback)	throws IOException {
		ResourceManager.prepareManager(mEngine, this, mCamera, getVertexBufferObjectManager());
	    mResMgr = ResourceManager.getInstance();
	    pOnCreateResourcesCallback.onCreateResourcesFinished();
	}

	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)	throws IOException {
		// create and set splash screen at start  
		SceneManager.getInstance().createSplashScene(pOnCreateSceneCallback);
	}

	public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws IOException {
		mEngine.registerUpdateHandler(new TimerHandler(2f,
				new ITimerCallback() {
					public void onTimePassed(final TimerHandler pTimerHandler) {
						mEngine.unregisterUpdateHandler(pTimerHandler);
						// load menu resources, create menu scene
						// set menu scene using scene manager
						// disposeSplashScene();
						// READ NEXT ARTICLE FOR THIS PART.
					}
				}));
		pOnPopulateSceneCallback.onPopulateSceneFinished();

	}

	@Override
	public Engine onCreateEngine(EngineOptions pEngineOptions) {
		return new LimitedFPSEngine(pEngineOptions, 60);
	}
}