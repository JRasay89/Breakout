package com.mygame.core;

public class Input
{	
	//Instance variables
	private static boolean[] keys;
	private static boolean[] prevKeys;
	
	private static int numKeys = 5;
	
	//Keys
	public static int SPACE = 0;
	public static int LEFT = 1;
	public static int RIGHT = 2;
	public static int ENTER = 3;
	public static int ESCAPE = 4;
	
	//constructor
	static
	{
		keys = new boolean[numKeys];
		prevKeys = new boolean[numKeys];
	}
	
	
	//methods	
	public static void update()
	{
		for(int i = 0; i < numKeys; i++)
		{
			prevKeys[i] = keys[i];
		}
	}
	/**
	 * Set the key value of a key to true or false
	 * @param keyIndex is the index of the selected key on the keyboard
	 * @param b is the value of the selected key
	 */
	public static void setKeys(int key, boolean b)
	{
		keys[key] = b;
	}
	
	/**
	 * Check to see if key on the keyboard is held
	 * @return true or false
	 */
	public static boolean isKeyHeld(int key)
	{
		if (key < 0 || key > numKeys )
		{
			return false;
		}
		
		return keys[key];
	}
	
	/**
	 * Check to see if the key on the keyboard was hit
	 * @return true or false
	 */
	public static boolean isKeyHit(int key)
	{
		if (key < 0 || key > numKeys )
		{
			return false;
		}
		
		//System.out.println(keys[key] && !prevKeys[key]);
		return keys[key] && !prevKeys[key];
	}
	
	/**
	 * Check to see if the key on the keyboard is up
	 * @return true or false
	 */
	public static boolean isKeyUp(int key)
	{
		if (key < 0 || key > numKeys )
		{
			return false;
		}
		
		return !keys[key] && prevKeys[key];

	}
	

	
	
	

}
