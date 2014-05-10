package com.neomentis.feelalive.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.neomentis.feelalive.framework.KeyInput;
import com.neomentis.feelalive.framework.ObjectId;
import com.neomentis.feelalive.framework.Texture;
import com.neomentis.feelalive.objects.Block;
import com.neomentis.feelalive.objects.Player;

public class Game extends Canvas implements Runnable
{

	private static final long serialVersionUID = 3905480252076443641L;

	private boolean running = false;
	private Thread thread;
	
	public static int WIDTH, HEIGHT;
	
	private BufferedImage level = null, city = null;
	
	//Object
	Handler handler;
	Camera cam;
	static Texture tex;
	
	private void init() 
	{
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		tex = new Texture();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/level.png"); //loading the level
		
		handler = new Handler();
		cam = new Camera(0,0);
		
		LoadImageLevel(level);
		
		//handler.addObject(new Player(100, 100, handler, ObjectId.Player));
		//handler.createLevel();
		
		this.addKeyListener(new KeyInput(handler));
	}
	
	public synchronized void start(){
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void run()
	{
		init();
		this.requestFocus();
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	private void tick()
	{
		handler.tick();
		for(int i = 0; i < handler.object.size(); i++)
		{
			if(handler.object.get(i).getId() == ObjectId.Player)
			{
				cam.tick(handler.object.get(i));
			}
		}
	}
	/**
	 * setting the amount of buffers we want prepared behind first buffer
	 * 
	 * if computer is fast enough if can buffer more than one image at a time,
	 * this extra buffer can increase FPS and increase optimization
	 * the reason we don't do more than 3 is because no computer can run up to
	 * 100 buffers at a time, 3 is about what the average gaming super computer 
	 * in 2013 was capping out at.
	 * 
	 *  3 is about the most you can get loaded per say
	 * - Brandon
	 */
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		//////////////////////////////////
		
		g.setColor(new Color(25, 191, 255));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		/* single background image
			g.drawImage(city, 0, 0, null);
		*/
		
		g2d.translate(cam.getX(), cam.getY()); //begin of cam
		
		/** for repeating backgrounds
			for(int xx = 0; xx < clouds.getWidth() * 5; xx += clouds.getWidth())
				g.drawImage(clouds, xx, 50, this);
		 **/
			handler.render(g);
			
		g2d.translate(-cam.getX(), -cam.getY()); //end of cam
		
		
		//////////////////////////////////
		
		g.dispose();
		bs.show();
	}
	
	private void LoadImageLevel(BufferedImage image)
	{
		int w = image.getWidth();
		int h = image.getHeight();
		
		System.out.println("width, height: " + w + " " + h);
		
		for(int xx = 0; xx < h; xx++) {
			for(int yy = 0; yy < w; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff; 
				
				//Dirt and Grass
				if(red == 255 && green == 255 && blue == 255) handler.addObject(new Block(xx*32, yy*32, 0, ObjectId.Block));
				if(red == 0 && green == 255 && blue == 255) handler.addObject(new Block(xx*32, yy*32, 1, ObjectId.Block));
			
				//Wood
				if(red == 255 && green == 0 && blue == 0) handler.addObject(new Block(xx*32, yy*32, 3, ObjectId.Block));
				if(red == 255 && green == 255 && blue == 0) handler.addObject(new Block(xx*32, yy*32, 4, ObjectId.Block));
				
				//Stone
				if(red == 255 && green == 0 && blue == 255) handler.addObject(new Block(xx*32, yy*32, 2, ObjectId.Block));
				
				//Player
				if(red == 0 && green == 00 && blue == 255) 
					handler.addObject(new Player(xx*32, yy*32, handler, ObjectId.Player));
			}
		}
	}
	
	public static Texture getInstance(){
		return tex;
	}
	
	public static void main(String args[]){ 
		new Window(800, 600, "Feel Alive", new Game());
	}
}
