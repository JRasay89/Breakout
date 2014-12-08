package com.mygame.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.core.Input;
import com.mygame.core.SpriteLoader;
import com.mygame.entities.Brick;

public class GameOverState extends GameState {

	public GameOverState(GameStateManager gsm, SpriteLoader spriteLoader, BitmapFont font)
	{
		super(gsm, spriteLoader, font);
		
	}

	@Override
	public void handleInput()
	{
		if(Input.isKeyHit(Input.ENTER))
		{
			Brick.resetNumOfBricks();
			getGameStateManager().pushState(new PlayState(gsm, spriteLoader, font));
			getGameStateManager().removeState();
		}
		if(Input.isKeyHit(Input.ESCAPE))
		{
			Gdx.app.exit();
		}
	}

	@Override
	public void update(float dt)
	{
		
	}

	@Override
	public void render(SpriteBatch sb) 
	{
		sb.begin();
		getFont().draw(sb, "GAME OVER!", 325f, 400f);
		getFont().draw(sb, "Press enter to play again", 200f, 360f);
		getFont().draw(sb, "Press escape to quit", 245f, 320f);
		sb.end();
	}

}
