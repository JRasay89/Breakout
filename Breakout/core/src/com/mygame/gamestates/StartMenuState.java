package com.mygame.gamestates;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.core.Input;
import com.mygame.core.SpriteLoader;

public class StartMenuState extends GameState
{

	public StartMenuState(GameStateManager gsm, SpriteLoader spriteLoader, BitmapFont font) 
	{
		super(gsm, spriteLoader, font);
		
	}

	@Override
	public void handleInput() 
	{
		if(Input.isKeyHit(Input.ENTER))
		{
			getGameStateManager().pushState(new PlayState(gsm, spriteLoader, font));
			getGameStateManager().removeState();
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
		getFont().draw(sb, "Press enter to play!", 240f, 350f);
		sb.end();
	}

}
