package com.mygame.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygame.core.Constant;
import com.mygame.core.SpriteLoader;

public class Ball extends AbstractEntity 
{
	
	private float width;
	private float height;
	
	private float maxSpeed;
	private Vector2 velocity;
	private float speed;
	
	public Ball(SpriteLoader spriteLoader, World world) 
	{
		super(spriteLoader, world);
		
		initSprite();
		initBox2DBody();
		
		maxSpeed = 6f;
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();
	}
	
	public void update()
	{
		velocity = body.getLinearVelocity();
		speed = velocity.len();
		//System.out.println(speed);
		
		if(speed > maxSpeed)
		{
			body.setLinearDamping(.5f);
		}
		else if(speed < maxSpeed)
		{
			body.setLinearDamping(0.0f);
		}
	    
	}

	@Override
	public void render(SpriteBatch sb) 
	{
		sb.begin();
		sprite.setPosition(body.getPosition().x * Constant.PPM - width/2, body.getPosition().y * Constant.PPM - height/2);
		sprite.draw(sb);
		sb.end();
	}
	
	private void initSprite()
	{
		BallColor ballColor = BallColor.getRandomColor();
		switch(ballColor)
		{
			case BLUE:
				if(spriteLoader.getHashMap().get("ballBlue") == null)
				{
					spriteLoader.loadSprite("ballBlue");

				}
				sprite = spriteLoader.getSprite("ballBlue");
				break;
			case GREY:
				if(spriteLoader.getHashMap().get("ballGrey") == null)
				{
					spriteLoader.loadSprite("ballGrey");

				}
				sprite = spriteLoader.getSprite("ballGrey");
				break;
		}
	}
	
	private void initBox2DBody()
	{
		//Create bdef, fixturedef, circleshape and polygonshape
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		CircleShape cshape = new CircleShape();

		bdef.position.set(400f / Constant.PPM, 50f / Constant.PPM);
		bdef.type = BodyType.DynamicBody;
		body = world.createBody(bdef);

		cshape.setRadius(12f / Constant.PPM);
		fdef.shape = cshape;
		fdef.density = 1.0f;
		fdef.friction = 0.0f;
		fdef.restitution = 1.0f;
		fdef.filter.categoryBits = Constant.BIT_BALL;
		fdef.filter.maskBits = Constant.BIT_PADDLE | Constant.BIT_BORDER | Constant.BIT_BRICK;
		body.createFixture(fdef).setUserData("ball");
		
		//Set initial linear impulse
		body.applyLinearImpulse(new Vector2(0f, -10f / Constant.PPM), body.getPosition(), true);

		//Set this object as the user data of the body
		body.setUserData(this);		
	}

}
