package com.neomentis.feelalive.framework;

import java.awt.image.BufferedImage;

import com.neomentis.feelalive.window.BufferedImageLoader;

public class Texture {

	SpriteSheet bs, ps;
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	
	public BufferedImage[] block = new BufferedImage[40];
	public BufferedImage[] player = new BufferedImage[19];
	
	public Texture(){
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			block_sheet = loader.loadImage("/block_sheet.png");
			player_sheet = loader.loadImage("/player_sheet.jpg");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);
		
		getTextures();
	}
	
	private void getTextures()
	{
		block[0] = bs.grabImage(1, 2, 32, 32); //stone
		block[1] = bs.grabImage(2, 2, 32, 32); //cracked stone
		block[2] = bs.grabImage(3, 2, 32, 32); //really cracked stone
		
		//player needs to use ps not bs (ps.grabImage)
		//temp using blocks
		player[0] = bs.grabImage(1, 1, 32, 64); //idle frame for player
	}
}
