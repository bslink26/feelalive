package com.neomentis.feelalive.framework;

import java.awt.image.BufferedImage;

import com.neomentis.feelalive.window.BufferedImageLoader;

public class Texture {

	SpriteSheet bs, ps;
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	
	public BufferedImage[] block = new BufferedImage[40];
	public BufferedImage[] player = new BufferedImage[8];
	
	public Texture(){
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			block_sheet = loader.loadImage("/block_sheet.png");
			player_sheet = loader.loadImage("/player_sheet.png");
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
		block[1] = bs.grabImage(1, 15, 32, 32); //dirt
		block[2] = bs.grabImage(3, 2, 32, 32); //really cracked stone
		
		//Walking right
		player[0] = ps.grabImage(1, 1, 32, 64); //idle frame for player
		player[1] = ps.grabImage(2, 1, 32, 64); //walking 1 frame for player
		player[2] = ps.grabImage(3, 1, 32, 64); //walking 2 frame for player
		player[3] = ps.grabImage(4, 1, 32, 64); //walking 3 frame for player
		player[4] = ps.grabImage(5, 1, 32, 64); //walking 4 frame for player
		player[5] = ps.grabImage(6, 1, 32, 64); //walking 5 frame for player
		player[6] = ps.grabImage(7, 1, 32, 64); //walking 6 frame for player
		player[7] = ps.grabImage(8, 1, 32, 64); //walking 7 frame for player

		//TODO: Walking Left
		//TODO: Jumping Animation
		//TODO: Fighting Animations
		
	}
}
