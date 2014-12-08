package com.mygame.entities;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygame.core.Constant;
import com.mygame.core.SpriteLoader;

public class Brick extends AbstractEntity {
	
	private static int numOfBricks;
	
	private float width;
	private float height;
	
	public Brick(SpriteLoader spriteLoader, World world) {
		super(spriteLoader, world);
		
		//set color of brick
		initSprite();
		initBox2DBody();


		//Increase numOfBricks
		numOfBricks++;
		
		//Set width and height
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();

	}

	@Override
	public void render(SpriteBatch sb)
	{
		sb.begin();
		this.sprite.setPosition(body.getPosition().x * Constant.PPM - width/2, body.getPosition().y * Constant.PPM - height/2);
		sprite.draw(sb);
		sb.end();
		
	}
	
	public void setPosition(float x, float y)
	{
		body.setTransform(x / Constant.PPM, y / Constant.PPM, 0);
	}
	
	
	/**
	 * Returns the total number of bricks present in the game
	 * @return
	 */
	public static int getNumberOfBricks()
	{
		return numOfBricks;
	}
	/**
	 * Update the number of bricks currently in the game
	 */
	public static void updateNumOfBricks()
	{
		--numOfBricks;
	}
	/**
	 * Reset the number of bricks currently in the game
	 */
	public static void resetNumOfBricks()
	{
		numOfBricks = 0;
	}
	
	private void initBox2DBody()
	{
		//Create bdef, fixture, and shape
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		
		bdef.position.set(235f / Constant.PPM, 450f / Constant.PPM);
		bdef.type = BodyType.StaticBody;
		body = world.createBody(bdef);
		
		shape.setAsBox(32f / Constant.PPM, 15f / Constant.PPM);
		fdef.shape = shape;
		
		//Set mask bits
		fdef.filter.categoryBits = Constant.BIT_BRICK;
		fdef.filter.maskBits = Constant.BIT_BALL;
		
		//create the fixture and set the user data
		body.createFixture(fdef).setUserData("brick");
		//set the user data of the body to the instance of this class
		body.setUserData(this);
	}
	/**
	 * Set a random color for the brick object
	 */
	private void initSprite()
	{
		//Pick a random color for the brick
		BrickColor color = BrickColor.getRandomColor();
		switch(color)
		{
			case BLUE:
				if(spriteLoader.getHashMap().get("element_blue_rectangle_glossy") == null)
				{
					spriteLoader.loadSprite("element_blue_rectangle_glossy");

				}
				sprite = spriteLoader.getSprite("element_blue_rectangle_glossy");
				break;
			case GREEN:
				if(spriteLoader.getHashMap().get("element_green_rectangle_glossy") == null)
				{
					spriteLoader.loadSprite("element_green_rectangle_glossy");

				}
				sprite = spriteLoader.getSprite("element_green_rectangle_glossy");
				break;
			case GREY:
				if(spriteLoader.getHashMap().get("element_grey_rectangle_glossy") == null)
				{
					spriteLoader.loadSprite("element_grey_rectangle_glossy");

				}
				sprite = spriteLoader.getSprite("element_grey_rectangle_glossy");
				break;
			case PURPLE:
				if(spriteLoader.getHashMap().get("element_purple_rectangle_glossy") == null)
				{
					spriteLoader.loadSprite("element_purple_rectangle_glossy");

				}
				sprite = spriteLoader.getSprite("element_purple_rectangle_glossy");
				break;
			case RED:
				if(spriteLoader.getHashMap().get("element_red_rectangle_glossy") == null)
				{
					spriteLoader.loadSprite("element_red_rectangle_glossy");

				}
				sprite = spriteLoader.getSprite("element_red_rectangle_glossy");
				break;
			case YELLOW:
				if(spriteLoader.getHashMap().get("element_yellow_rectangle_glossy") == null)
				{
					spriteLoader.loadSprite("element_yellow_rectangle_glossy");

				}
				sprite = spriteLoader.getSprite("element_yellow_rectangle_glossy");
				break;
		}	
	}
}
