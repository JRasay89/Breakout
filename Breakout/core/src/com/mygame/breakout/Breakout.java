package com.mygame.breakout;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.physics.box2d.World;
import com.mygame.core.Audio;
import com.mygame.core.Constant;
import com.mygame.core.Input;
import com.mygame.core.MyInputAdapter;
import com.mygame.core.SpriteLoader;
import com.mygame.gamestates.GameOverState;
//import game state manager and the states
import com.mygame.gamestates.GameStateManager;
import com.mygame.gamestates.PlayState;
import com.mygame.gamestates.StartMenuState;

public class Breakout extends ApplicationAdapter {
	SpriteBatch sb;

	private OrthographicCamera camera;
	private float accumulator = 0;
	
	//Game state manager and states
	GameStateManager gsm;

	//Sprite sheet
	private SpriteLoader spriteLoader;
	
	//Font
	private FreeTypeFontGenerator generator;
	private FreeTypeFontParameter parameter;
	private BitmapFont font24;

	
	@Override
	public void create () {
		
		sb = new SpriteBatch();
		
		//Load Spritesheet
		loadSpriteSheet("Puzzle/puzzle.txt");
		spriteLoader.loadSprite("background");
		
		//load sfx
		Audio.loadSound("sfx/ping_pong_8bit_beeep.ogg", "beeep");
		Audio.loadSound("sfx/ping_pong_8bit_plop.ogg", "plop");
		Audio.loadSound("sfx/ping_pong_8bit_peeeeeep.ogg", "peeeeeep");
		
		//Load ttf font
		loadFont("Fonts/kenpixel.ttf");
		
		//camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Constant.WIDTH, Constant.HEIGHT);

		//Initialize the game state manager and the game states
		gsm = new GameStateManager(this);
  
		//Add the states in the game state manager
		gsm.pushState(new StartMenuState(gsm, spriteLoader, font24));
		
		
		Gdx.input.setInputProcessor(new MyInputAdapter());
		
	}

	@Override
	public void render () 
	{
		
		//Camera update
		camera.update();
		sb.setProjectionMatrix(camera.combined);
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//delta time
		accumulator += Gdx.graphics.getDeltaTime();
		while (accumulator >= Constant.TIME_STEP)
		{	
			accumulator -= Constant.TIME_STEP;
			
			gsm.handleInput();
			
			gsm.update(Constant.TIME_STEP);
			
			sb.begin();
			spriteLoader.getSprite("background").draw(sb);
			sb.end();
			
			gsm.render(sb);
			
			Input.update();
		}
		
		
	}
	
	public OrthographicCamera getCamera()
	{
		return camera;
	}
	
	private void loadSpriteSheet(String fileName)
	{
		spriteLoader = new SpriteLoader(fileName);

	}
	
	/**
	 * Load the given font file
	 * @param fileName is the font that will be loaded
	 */
	private void loadFont(String fileName)
	{
		//Create a new font generator using the given font file
		generator = new FreeTypeFontGenerator(Gdx.files.internal(fileName));
		//Create the parameter and set the size
		parameter = new FreeTypeFontParameter();
		parameter.size = 24;
		//Create the new font with the given parameter
		font24 = generator.generateFont(parameter);
		//Dispose the font generator
		generator.dispose();
	}

}
