package com.mygame.entities;

import java.util.Random;

public enum BallColor 
{
	BLUE,
	GREY;
	
	/**
	 * Pick a random value of the BallColor enum.
	 * @return a random BallColor
	 */
	public static BallColor getRandomColor()
	{
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}
}
