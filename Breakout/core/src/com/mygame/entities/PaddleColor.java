package com.mygame.entities;

import java.util.Random;

public enum PaddleColor 
{
	BLUE,
	RED;
	
	/**
	 * Pick a random value of the PaddleColor enum.
	 * @return a random PaddleColor
	 */
	public static PaddleColor getRandomColor()
	{
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}
}
