package com.mygame.core;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class MyInputAdapter extends InputAdapter {
	
	public boolean keyDown(int k)
	{
		
		if(k == Keys.SPACE)
		{
			Input.setKeys(Input.SPACE, true);
		}
		if(k == Keys.LEFT)
		{
			Input.setKeys(Input.LEFT, true);

		}
		if(k == Keys.RIGHT)
		{
			Input.setKeys(Input.RIGHT, true);

		}
		if(k == Keys.ENTER)
		{
			Input.setKeys(Input.ENTER, true);
		}
		if(k == Keys.ESCAPE)
		{
			Input.setKeys(Input.ESCAPE, true);
		}
		
		return true;
		
	}
	
	public boolean keyUp(int k)
	{
		
		if(k == Keys.SPACE)
		{
			Input.setKeys(Input.SPACE, false);
		}	
		if(k == Keys.LEFT)
		{
			Input.setKeys(Input.LEFT, false);

		}
		if(k == Keys.RIGHT)
		{
			Input.setKeys(Input.RIGHT, false);

		}
		if(k == Keys.ENTER)
		{
			Input.setKeys(Input.ENTER, false);
		}
		if(k == Keys.ESCAPE)
		{
			Input.setKeys(Input.ESCAPE, false);
		}
		return true;
	}
}
