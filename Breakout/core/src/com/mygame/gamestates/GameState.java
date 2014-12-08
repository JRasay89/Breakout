package com.mygame.gamestates;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.core.SpriteLoader;

public abstract class GameState
{
	
	protected GameStateManager gsm;
	protected SpriteLoader spriteLoader;
	
	protected OrthographicCamera camera;
	
	protected BitmapFont font;
	
	public GameState(GameStateManager gsm, SpriteLoader spriteLoader,  BitmapFont font)
	{
		this.spriteLoader = spriteLoader;

		this.gsm = gsm;		
		camera = gsm.getCamera();
		
		this.font = font;
	}
	
	public abstract void handleInput();
	
	public abstract void update(float dt);
	
	public abstract void render(SpriteBatch sb);
	
	/**
	 * Getter method, returns the game state manager
	 * @return GameStateManager
	 */
	public GameStateManager getGameStateManager()
	{
		return gsm;
	}
	
	public SpriteLoader getSpriteLoader()
	{
		return spriteLoader;
	}
	
	public BitmapFont getFont()
	{
		return font;
	}
}
