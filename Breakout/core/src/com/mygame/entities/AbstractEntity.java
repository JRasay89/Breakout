package com.mygame.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygame.core.SpriteLoader;

public abstract class AbstractEntity {
	

	protected SpriteLoader spriteLoader;
	protected Sprite sprite;
	protected Body body;
	protected World world;
	
	public AbstractEntity(SpriteLoader spriteLoader, World world)
	{
		this.spriteLoader = spriteLoader;
		this.world = world;
	}
	
	public abstract void render(SpriteBatch sb);
	
	/**
	 * Get the box2d body that is associated with the instance of this class
	 * @return the box2d body
	 */
	public Body getBody()
	{
		return body;
	}
	
	public Vector2 getPosition()
	{
		return body.getPosition();
	}
	
	public void setSprite(String spriteName)
	{
		this.sprite = this.spriteLoader.getSprite(spriteName);
	}
	

	
	
}
