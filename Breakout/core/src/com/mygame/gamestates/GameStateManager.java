package com.mygame.gamestates;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.breakout.Breakout;

public class GameStateManager {
	
	private LinkedList<GameState> gameStates;
	private Breakout breakout;
	
	public GameStateManager(Breakout breakout)
	{
		gameStates = new LinkedList<GameState>();
		
		this.breakout = breakout;
	}
	
	public void handleInput()
	{
		if(!gameStates.isEmpty())
		{
			gameStates.peek().handleInput();

		}
	}
	
	public void update(float dt)
	{
		if(!gameStates.isEmpty())
		{
			gameStates.peek().update(dt);

		}
	}
	
	public void render(SpriteBatch sb)
	{
		if(!gameStates.isEmpty())
		{
			gameStates.peek().render(sb);
		}
	}
	
	public void pushState(GameState state)
	{
		gameStates.push(state);
	}
	
	public void removeState()
	{
		if(!gameStates.isEmpty())
		{
			gameStates.removeLast();
		}
	}
	
	public OrthographicCamera getCamera()
	{
		return breakout.getCamera();
	}
	
	
}
