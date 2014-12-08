package com.mygame.gamestates;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygame.core.Audio;
import com.mygame.core.Constant;
import com.mygame.core.SpriteLoader;
import com.mygame.entities.Ball;
import com.mygame.entities.Brick;
import com.mygame.entities.MyContactListener;
//Entities
import com.mygame.entities.Paddle;

public class PlayState extends GameState {
	
	private World world;
	private MyContactListener cL;	
	
	//Camera
	private OrthographicCamera b2dcam;
	private Box2DDebugRenderer b2dr;
	private boolean debug;

	//Entities
	private Paddle paddle;
	private Ball ball;
	private Array<Brick> bricks;
	
	public PlayState(GameStateManager gsm, SpriteLoader spriteLoader, BitmapFont font) 
	{
		super(gsm, spriteLoader, font);
		debug = false;
		
		world = new World(new Vector2(0, -9.81f), true);
		cL = new MyContactListener();
		world.setContactListener(cL);
		b2dr = new Box2DDebugRenderer();

		
		b2dcam = new OrthographicCamera();
		b2dcam.setToOrtho(false, Constant.WIDTH / Constant.PPM, Constant.HEIGHT/Constant.PPM);
		b2dcam.update();
	

		//Create Ball
		ball = new Ball(spriteLoader, world);
		
		//Create Paddle
		paddle = new Paddle(spriteLoader, world, ball);

		//Create Bricks
		bricks = new Array<Brick>();
		setupBricks();

		//Create Border
		createBorder();
		
	}

	public void handleInput()
	{
		paddle.handleInput();
		

	}
	
	public void update(float dt)
	{
		
		world.step(dt, 6 , 2);
		
		ball.update();
		
		//Remove bricks that the ball has hit
		Array<Body> bodies = cL.getBodiesToRemove();
		for(int i = 0; i < bodies.size; i++)
		{
			Body body = bodies.get(i);
			bricks.removeValue((Brick)body.getUserData(), true);
			world.destroyBody(body);
		}
		bodies.clear();
		
		//Check if the player lose or won
		if(ball.getPosition().y < 0)
		{
			Audio.play("peeeeeep");
			getGameStateManager().pushState(new GameOverState(gsm, spriteLoader, font));
			getGameStateManager().removeState();
		}
		else if(Brick.getNumberOfBricks() == 0)
		{
			getGameStateManager().pushState(new WinningState(gsm, spriteLoader, font));
			getGameStateManager().removeState();

		}

	}

	public void render(SpriteBatch sb) 
	{
		//Draw Paddle
		paddle.render(sb);
		//Draw Ball
		ball.render(sb);
		//Draw Bricks
		for(int i = 0; i < bricks.size; i++)
		{
			bricks.get(i).render(sb);
		}
		
		sb.begin();
		font.draw(sb, "Number of bricks: " + Brick.getNumberOfBricks(), 450f, 590f);
		sb.end();
		
		if(debug)
		{
			b2dr.render(world, b2dcam.combined);

		}

	}
	
	/**
	 * Create the border for the game
	 */
	public void createBorder()
	{	
		Body body;
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		EdgeShape eshape = new EdgeShape();

		bdef.position.set(0f / Constant.PPM, 0f / Constant.PPM);
		bdef.type = BodyType.StaticBody;
		body = world.createBody(bdef);
		fdef.shape = eshape;
		fdef.filter.categoryBits = Constant.BIT_BORDER;
		fdef.filter.maskBits = Constant.BIT_BALL;
        
		//left
		eshape.set(new Vector2(0f  / Constant.PPM, 0f / Constant.PPM), new Vector2(0f / Constant.PPM, 600f / Constant.PPM));
		body.createFixture(fdef).setUserData("border");
		//top
		eshape.set(new Vector2(0f  / Constant.PPM, 600f / Constant.PPM), new Vector2(800  / Constant.PPM, 600  / Constant.PPM));
		body.createFixture(fdef).setUserData("border");
		//bottom
		//eshape.set(new Vector2(0f  / Constant.PPM, 0f / Constant.PPM), new Vector2(800f / Constant.PPM, 0f / Constant.PPM));
		//body.createFixture(fdef).setUserData("border");
		//right
		eshape.set(new Vector2(800 / Constant.PPM, 0/ Constant.PPM), new Vector2(800 / Constant.PPM, 600 / Constant.PPM));
		body.createFixture(fdef).setUserData("border");
		
	}
	
	/**
	 * Setup the bricks
	 */
	public void setupBricks()
	{
		float x = 50f;
		float y = 450f;
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				bricks.add(new Brick(spriteLoader, world));
				
				x += 64f;
				bricks.peek().setPosition(x, y);
			}
			
			x = 50f;
			y += 32;
		}
	}

}
