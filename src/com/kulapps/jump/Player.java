package com.kulapps.jump;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public abstract class Player extends AnimatedSprite
{
	// members 
	private Body body;
	private boolean canRun = false;
	private int footContacts = 0;

	
	public abstract void onDie();
	
	public void increaseFootContacts()
	{
	    footContacts++;
	}

	public void decreaseFootContacts()
	{
	    footContacts--;
	}
	
	private void createPhysics(final Camera camera, PhysicsWorld physicsWorld)
	{        
	    body = PhysicsFactory.createBoxBody(physicsWorld, this, BodyType.DynamicBody, PhysicsFactory.createFixtureDef(0, 0, 0));

	    body.setUserData("player");
	    body.setFixedRotation(true);
	    
	    physicsWorld.registerPhysicsConnector(new PhysicsConnector(this, body, true, false)
	    {
	        @Override
	        public void onUpdate(float pSecondsElapsed)
	        {
	            super.onUpdate(pSecondsElapsed);
	            camera.onUpdate(0.1f);
	            
	            if (getY() <= 0)
	            {                    
	                onDie();
	            }
	            
	            if (canRun)
	            {    
	                body.setLinearVelocity(new Vector2(3, body.getLinearVelocity().y)); 
	            }
	        }
	    });
	}
	
	
	public void setRunning()
	{
	    canRun = true;
	        
	    final long[] PLAYER_ANIMATE = new long[] { 100, 100, 100 };
	        
	    animate(PLAYER_ANIMATE, 0, 2, true);
	}
	
	public void jump()
	{
		if (footContacts < 1) 
	    {
	        return; 
	    }
	    body.setLinearVelocity(new Vector2(body.getLinearVelocity().x, 12)); 
	}
	
	/**
	 * Ctor
	 * @param pX
	 * @param pY
	 * @param vbo
	 * @param camera
	 * @param physicsWorld
	 */
    public Player(float pX, float pY, VertexBufferObjectManager vbo, Camera camera, PhysicsWorld physicsWorld)
    {
        super(pX, pY, ResourceManager.getInstance().player_region, vbo);
        createPhysics(camera, physicsWorld);
        camera.setChaseEntity(this);
    }
}