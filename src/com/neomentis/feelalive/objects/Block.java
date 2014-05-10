package com.neomentis.feelalive.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.neomentis.feelalive.framework.GameObject;
import com.neomentis.feelalive.framework.ObjectId;
import com.neomentis.feelalive.framework.Texture;
import com.neomentis.feelalive.window.Game;

public class Block extends GameObject {
	
	Texture tex = Game.getInstance();
	private int type;

	public Block(float x, float y, int type, ObjectId id) {
		super(x, y, id);
		this.type = type;
	}

	public void tick(LinkedList<GameObject> object) 
	{
		
	}
	public void render(Graphics g) 
	{
		g.drawImage(tex.block[type], (int)x, (int)y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

}
