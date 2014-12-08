package com.mygame.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;
import com.mygame.core.Audio;

/**
 * This will handle any collision that occurs between bodies in the world 
 * @author John
 *
 */
public class MyContactListener implements ContactListener {
	
	private Array<Body> bodiesToRemove;
	
	public MyContactListener()
	{
		super();
		
		bodiesToRemove = new Array<Body>();
	}
	
	@Override
	public void beginContact(Contact contact) 
	{
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		if(fa.getUserData() != null && fa.getUserData().equals("paddle"))
		{
			Audio.play("beeep");
		}
		
		if(fb.getUserData() != null && fb.getUserData().equals("paddle"))
		{
			Audio.play("beeep");
		}
		
		
		if(fa.getUserData() != null && fa.getUserData().equals("brick"))
		{
			Audio.play("plop");
			bodiesToRemove.add(fa.getBody());
			Brick.updateNumOfBricks();
		}
		
		if(fb.getUserData() != null && fb.getUserData().equals("brick"))
		{
			Audio.play("plop");
			bodiesToRemove.add(fb.getBody());
			Brick.updateNumOfBricks();

		}
		
	}

	@Override
	public void endContact(Contact contact) 
	{
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		if(fa.getUserData() != null && fa.getUserData().equals("paddle"))
		{
			Paddle paddle = (Paddle) fa.getBody().getUserData();
			paddle.bounceBall();
		}
		
		if(fb.getUserData() != null && fb.getUserData().equals("paddle"))
		{
			//System.out.println("fb end is paddle");
			Paddle paddle = (Paddle) fb.getBody().getUserData();
			paddle.bounceBall();
		}
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) 
	{
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) 
	{
		
	}
	
	public Array<Body> getBodiesToRemove()
	{
		return bodiesToRemove;
	}

}
