package com.kulapps.jump;

import org.andengine.engine.Engine;
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
        setScene(menuScene);
        disposeSplashScene();
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