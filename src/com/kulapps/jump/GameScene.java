package com.kulapps.jump;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;

import com.badlogic.gdx.math.Vector2;
import com.kulapps.jump.SceneManager.SceneType;

public class GameScene extends BaseScene
{
	// physics related 
	private PhysicsWorld physicsWorld;
	
	// HUD related
	private HUD gameHUD;
	// score related
	private Text scoreText;
	private int score = 0;

	private void addToScore(int i)
	{
	    score += i;
	    scoreText.setText("Score: " + score);
	}

	private void createHUD()
	{
	    gameHUD = new HUD();
	    
	    // add score text	    
	    scoreText = new Text(20, 420, resourcesManager.font, "Score: 0123456789", new TextOptions(HorizontalAlign.LEFT), vbom);
	    //scoreText.setAnchorCenter(0, 0); TODO    
	    scoreText.setText("Score: 0");
	    gameHUD.attachChild(scoreText);
	    
	    camera.setHUD(gameHUD);
	}
	
	private void createPhysics()
	{
	    physicsWorld = new FixedStepPhysicsWorld(GAME.FPS, new Vector2(GAME.GRAVITY_X, GAME.GRAVITY_Y), false); 
	    registerUpdateHandler(physicsWorld);
	}
	
    @Override
	public void createScene() {
		setBackground(new Background(Color.BLUE));
		createHUD();
		createPhysics();
	}

    @Override
    public void onBackKeyPressed()
    {
        SceneManager.getInstance().loadMenuScene(engine);
    }

    @Override
    public SceneType getSceneType()
    {
        return SceneType.SCENE_GAME;
    }

    @Override
    public void disposeScene()
    {
    	camera.setHUD(null);
        camera.setCenter(400, 240);

        // TODO code responsible for disposing scene
       
    }
}