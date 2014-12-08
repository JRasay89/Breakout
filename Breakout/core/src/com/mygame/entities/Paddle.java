package com.mygame.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

import com.mygame.core.Constant;
import com.mygame.core.Input;
import com.mygame.core.SpriteLoader;

public class Paddle extends AbstractEntity 
{

	private float width;
	private float height;
	
	private Ball ball;
	
	//Constructor
	public Paddle(SpriteLoader spriteLoader, World world, Ball ball)
	{
		super(spriteLoader, world);
		this.ball = ball;
		
		initSprite();
		initBox2DBody();
	
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();

	}
	
	public void handleInput()
	{
		if(Input.isKeyHeld(Input.LEFT))
		{
			body.setTransform(body.getPosition().x + (-7.5f/Constant.PPM), body.getPosition().y, 0);

			if((body.getPosition().x * Constant.PPM) <= 50)
			{
				body.setTransform(50f/Constant.PPM, body.getPosition().y, 0);
			}
		}
		
		if (Input.isKeyHeld(Input.RIGHT))
		{
			
			body.setTransform(body.getPosition().x + (6.5f/Constant.PPM), body.getPosition().y, 0);

			if((body.getPosition().x * Constant.PPM) >= 750)
			{
				body.setTransform(750f/Constant.PPM, body.getPosition().y, 0);
			}

		}
		
	}
	
	public void update(float dt)
	{
		
	}
	
	public void render(SpriteBatch sb)
	{
		sb.begin();
		this.sprite.setPosition(body.getPosition().x * Constant.PPM - width/2, body.getPosition().y * Constant.PPM - height/2);
		this.sprite.draw(sb);
		sb.end();
	}
	
	public void bounceBall()
	{
		//If ball hits the middle of the paddle
		if(ball.getBody().getPosition().x == body.getPosition().x)
		{
			Vector2 force = new Vector2(0f /Constant.PPM, 12.5f / Constant.PPM);
			ball.getBody().applyLinearImpulse(force, ball.getBody().getPosition(), true);
		}
		//If ball hits the left side of the paddle
		if(ball.getBody().getPosition().x < body.getPosition().x)
		{	
			Vector2 force = new Vector2(-10f /Constant.PPM, 12.5f / Constant.PPM);
			ball.getBody().applyLinearImpulse(force, ball.getBody().getPosition(), true);

		}
		//If the ball hits the right side of the paddle
		if(ball.getBody().getPosition().x > body.getPosition().x)
		{	
			Vector2 force = new Vector2(10f /Constant.PPM, 12.5f / Constant.PPM);
			ball.getBody().applyLinearImpulse(force, ball.getBody().getPosition(), true);
			//System.out.println("bounce right");     
		}
		
	}
	
	private void initBox2DBody()
	{
		//Create body
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();

		bdef.position.set(400f / Constant.PPM, 25f / Constant.PPM);
		bdef.type = BodyType.KinematicBody;
		body = world.createBody(bdef);
		
		shape.setAsBox(50f / Constant.PPM , 15f / Constant.PPM);
		
		fdef.shape = shape;
		fdef.filter.categoryBits = Constant.BIT_PADDLE;
		fdef.filter.maskBits = Constant.BIT_BALL;
		body.createFixture(fdef).setUserData("paddle");
		
		body.setUserData(this);
	}
	
	private void initSprite()
	{
		PaddleColor paddleColor = PaddleColor.getRandomColor();
		
		switch(paddleColor)
		{
			case BLUE:
				if(spriteLoader.getHashMap().get("paddleBlu") == null)
				{
					spriteLoader.loadSprite("paddleBlu");
				}
				sprite = spriteLoader.getSprite("paddleBlu");
				break;
				
			case RED:
				if(spriteLoader.getHashMap().get("paddleRed") == null)
				{
					spriteLoader.loadSprite("paddleRed");
				}
				sprite = spriteLoader.getSprite("paddleRed");	
				break;
		}
	}
}
