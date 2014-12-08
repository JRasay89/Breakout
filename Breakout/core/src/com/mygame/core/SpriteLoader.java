package com.mygame.core;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class SpriteLoader 
{
	private TextureAtlas textureAtlas;
	private HashMap<String, Sprite> sprites;
	
	public SpriteLoader(String textureAtlasPath)
	{
		textureAtlas = new TextureAtlas(textureAtlasPath);
		sprites = new HashMap<String, Sprite>();
	}
	
	public void loadSprite(String name)
	{
		Sprite sprite = textureAtlas.createSprite(name);
		sprites.put(name, sprite);
	}
	
	public Sprite getSprite(String name)
	{
		return sprites.get(name);
	}
	
	public HashMap<String, Sprite> getHashMap()
	{
		return sprites;
	}
}
