package com.neomentis.feelalive.window;

import com.neomentis.feelalive.framework.GameObject;

public class Camera {

	private float x, y;
	
	public Camera(float x, float y){
		this.x = x;
		this.y = y;
	}
	public void tick(GameObject player)
	{
		x = -player.getX() + Game.WIDTH/2;
		if(player.isFalling() == true)
			y = -player.getY() + (Game.HEIGHT/2) + 180;
	}
	
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	
	public void setX(float x){
		this.x = x;
	}
	public void setY(float y){
		this.y = y;
	}
	
}
