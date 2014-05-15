package com.kulapps.jump;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import com.kulapps.jump.SceneManager.SceneType;

/**
 * Splash screen 
 * Currently a simple image
 * @author San4
 *
 */
public class SplashScene extends BaseScene
{
	// members
	private Sprite mSplashSprite;
	
    @Override
    public void createScene()
    {
    	// create a sprite
    	mSplashSprite = new Sprite(0, 0, resourcesManager.mSplash_region, vbom)
    	{
    	    @Override
    	    protected void preDraw(GLState pGLState, Camera pCamera) 
    	    {
    	       super.preDraw(pGLState, pCamera);
    	       // enable dithering to improve quality (used with gradient based art)
    	       pGLState.enableDither();
    	    }
    	};

    	// scale 
    	mSplashSprite.setScale(1.5f);
    	// set position 
    	mSplashSprite.setPosition((GAME.SCREEN_WIDTH - resourcesManager.mSplash_region.getWidth()) /2, 
    			(GAME.SCREEN_HEIGHT - resourcesManager.mSplash_region.getHeight()) /2);
    	attachChild(mSplashSprite);
    }

    @Override
    public void onBackKeyPressed()
    {

    }

    @Override
    public SceneType getSceneType()
    {
    	return SceneType.SCENE_SPLASH;
    }

    @Override
    public void disposeScene()
    {
    	mSplashSprite.detachSelf();
    	mSplashSprite.dispose();
        this.detachSelf();
        this.dispose();
    }
}
