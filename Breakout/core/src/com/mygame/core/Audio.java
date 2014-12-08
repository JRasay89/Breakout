package com.mygame.core;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Audio 
{
	private static HashMap<String, Sound> sounds;
	
	static
	{
		sounds = new HashMap<String, Sound>();
	}
	
	public static void loadSound(String fileName, String name)
	{
		Sound sound = Gdx.audio.newSound(Gdx.files.internal(fileName));
		sounds.put(name, sound);
	}
	
	public static void play(String name)
	{
		sounds.get(name).play();
	}
}
