package com.neomentis.feelalive.window;

import java.awt.Canvas;
import java.awt.image.BufferedImage;

public class Menu extends Canvas {

	private static final long serialVersionUID = 1L;
	public static int WIDTH, HEIGHT;
	private BufferedImage nightSky = null;
	private boolean running = false;
	
	private void init() 
	{
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		nightSky = loader.loadImage("/night_sky.png"); //loading the background
		
		
		
		this.requestFocus();
	}
	
	public synchronized void start(){
		if(running)
			return;
		
		running = true;
	}

	/*
	public static void main(String args[]){ 
		new Window(800, 600, "Feel Alive", new Menu());
	}
	*/
}
