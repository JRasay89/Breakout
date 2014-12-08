package com.mygame.entities;

import java.util.Random;

public enum BrickColor
{
	BLUE,
	GREEN,
	GREY,
	PURPLE,
	RED,
	YELLOW;
	
	/**
	 * Pick a random value of the color enum.
	 * @return a random Color
	 */
	public static BrickColor getRandomColor()
	{
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}
}
