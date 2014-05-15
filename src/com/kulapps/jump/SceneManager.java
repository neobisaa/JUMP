package com.kulapps.jump;

import org.andengine.engine.Engine;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

/**
 * Singleton
 * Responsible for changing scenes
 * 
 * @author San4
 */
public class SceneManager {
	
    // all available scenes 
    private BaseScene splashScene;
    private BaseScene menuScene;
    private BaseScene gameScene;
    private BaseScene loadingScene;
    
    // members
    // Singleton instance
    private static final SceneManager mInstance = new SceneManager();
    
    private SceneType currentSceneType = SceneType.SCENE_SPLASH;
    
    private BaseScene currentScene;
    
    private Engine engine = ResourceManager.getInstance().mEngine;
    
    // Scene types ENUM
    public enum SceneType
    {
        SCENE_SPLASH,
        SCENE_MENU,
        SCENE_GAME,
        SCENE_LOADING,
    }
    
    // methods
    /**
     * Sets the scene via Engine
     * 
     * @param scene 	a scene to set
     */
    public void setScene(BaseScene scene)
    {
        engine.setScene(scene);
        currentScene = scene;
        currentSceneType = scene.getSceneType();
    }
    
    /**
     * Sets scene passed scene type not object itself
     * Maps between scene type to existing scenes 
     * 
     * @param sceneType		screen type to set
     */
    public void setScene(SceneType sceneType)
    {
        switch (sceneType)
        {
            case SCENE_MENU:
                setScene(menuScene);
                break;
            case SCENE_GAME:
                setScene(gameScene);
                break;
            case SCENE_SPLASH:
                setScene(splashScene);
                break;
            case SCENE_LOADING:
                setScene(loadingScene);
                break;
            default:
                break;
        }
    }
    
    // scene creators/disposers
    public void createSplashScene(OnCreateSceneCallback pOnCreateSceneCallback)
    {
        ResourceManager.getInstance().loadSplashScreen();
        splashScene = new SplashScene();
        currentScene = splashScene;
        pOnCreateSceneCallback.onCreateSceneFinished(splashScene);
    }
    
    private void disposeSplashScene()
    {
        ResourceManager.getInstance().unloadSplashScreen();
        splashScene.disposeScene();
        splashScene = null;
    }
    
    /**
     * Creates Menu Scene
     */
    public void createMenuScene()
    {
        ResourceManager.getInstance().loadMenuResources();
        menuScene = new MainMenuScene();
        loadingScene = new LoadingScene();
        SceneManager.getInstance().setScene(menuScene);
        disposeSplashScene();        
    }
   
    /**
     * Loads loading scene, waits for Game Scene resources to load
     * Also - unloads menu textures
     * 
     * @param mEngine	engine
     */
    public void loadGameScene(final Engine mEngine)
    {
        setScene(loadingScene);
        // unload menu scene textures
        ResourceManager.getInstance().unloadMenuTextures();
        // register update handler - timer to check once Game resources are loaded
        mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() 
        {
            public void onTimePassed(final TimerHandler pTimerHandler) 
            {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                ResourceManager.getInstance().loadGameResources();
                // create and set game scene
                gameScene = new GameScene();                
                setScene(gameScene);
            }
        }));
    }
    
    /**
     * Called once going back to menu from game scene
     * 
     * @param mEngine		engine
     */
    public void loadMenuScene(final Engine mEngine)
    {
    	// set loading scene
        setScene(loadingScene);
        // dispose of game scene
        gameScene.disposeScene();
        ResourceManager.getInstance().unloadGameTextures();
        // start timer to load menu resources
        mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() 
        {
            public void onTimePassed(final TimerHandler pTimerHandler) 
            {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                ResourceManager.getInstance().loadMenuTextures();
                // set menu scene
                setScene(menuScene);
            }
        }));
    }
       
    // getters & setters

    /**
     * Returns Singleton instance
     * 
     * @return		SceneManager object
     */
    public static SceneManager getInstance()
    {
        return mInstance;
    }
    
    /**
     * Returns current scene type
     * 
     * @return 		type of a current scene
     */
    public SceneType getCurrentSceneType()
    {
        return currentSceneType;
    }
    
    /**
     * Returns currently set scene object
     * 
     * @return		current scene object
     */
    public BaseScene getCurrentScene()
    {
        return currentScene;
    }
}