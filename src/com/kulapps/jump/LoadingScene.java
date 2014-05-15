package com.kulapps.jump;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;

import org.andengine.util.adt.color.Color;

import com.kulapps.jump.SceneManager.SceneType;

public class LoadingScene extends BaseScene
{
    @Override
    public void createScene()
    {
    	setBackground(new Background(Color.WHITE));
        attachChild(new Text((GAME.SCREEN_WIDTH - 100 )/2 , GAME.SCREEN_HEIGHT/2 , resourcesManager.font, "Loading...", vbom));
    }

    @Override
    public void onBackKeyPressed()
    {
        return;
    }

    @Override
    public SceneType getSceneType()
    {
        return SceneType.SCENE_LOADING;
    }

    @Override
    public void disposeScene()
    {

    }
}