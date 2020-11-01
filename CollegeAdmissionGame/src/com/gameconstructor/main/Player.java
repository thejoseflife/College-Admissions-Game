package com.gameconstructor.main;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;	

public class Player extends GameObject {

	public boolean moving = false;
	
	boolean movingLeft = false, movingRight = false, movingUp = false, movingDown = false;
	
	int height = 100;
	int width = 130;
	
	double prevX;
	double prevY;
	
	int speed = 10;
	
	BufferedImage[] images = new BufferedImage[12];
	
	BufferedImage image;
	
	Animation walkLeft;
	Animation walkRight;
	Animation walkUp;
	Animation walkDown;
	
	private boolean loaded = false;
	
	public Player(int x, int y, ID id, BufferedImage[] images) {
		super(x, y, id);
		
		this.images = images;
		
		image = images[0];
		
		walkLeft = new Animation(5, images[1], images[2]);
		walkRight = new Animation(5, images[3], images[7]);
		walkUp = new Animation(5, images[4], images[8]);
		walkDown = new Animation(5, images[5], images[6]);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		//System.out.println(x + " " + y);
		
		if(movingLeft && x <= prevX - Game.WIDTH/6) {
			moving = false;
			movingLeft = false;
			velX = 0;
			x = prevX - Game.WIDTH/6;
		}
		if(movingRight && x >= prevX + Game.WIDTH/6) {
			moving = false;
			movingRight = false;
			velX = 0;
			x = prevX + Game.WIDTH/6;
		}
		if(movingUp && y <= prevY - 116) {
			moving = false;
			movingUp = false;
			velY = 0;
			y = prevY - 116;
		}
		if(movingDown && y >= prevY + 116) {
			moving = false;
			movingDown = false;
			velY = 0;
			y = prevY + 116;
		}
		
		if(movingLeft) {
			walkLeft.runAnimation();
		} if(movingRight) {
			walkRight.runAnimation();
		} if(movingUp) {
			walkUp.runAnimation();
		} if(movingDown) {
			walkDown.runAnimation();
		}
		
	}
	
	public Box getBox() {
		for(Box b: Game.boxes) {
			if(b.getRect().contains(new Point((int)x + 30, (int)y + 30))) return b;
		}
		return null;
	}
	
	public void moveLeft() {
		for(Box b: Game.boxes) {
			if(b.getRect().contains(new Point((int)x - Game.WIDTH/6 + 30, (int)y + 20)) && b.movable) {
				moving = true;
				movingLeft = true;
				velX = -speed;
				prevX = x;
			}
		}
	}
	
	public void moveRight() {
		for(Box b: Game.boxes) {
			if(b.getRect().contains(new Point((int)x + Game.WIDTH/6 + 30, (int)y + 20)) && b.movable) {
				moving = true;
				movingRight = true;
				velX = speed;
				prevX = x;
			}
		}
	}
	
	public void moveUp() {
		for(Box b: Game.boxes) {
			if(b.getRect().contains(new Point((int)x + 30, (int)y - 116 + 30)) && b.movable) {
				moving = true;
				movingUp = true;
				velY = -speed;
				prevY = y;
			}
		}
	}
	
	public void moveDown() {
		for(Box b: Game.boxes) {
			if(b.getRect().contains(new Point((int)x + 30, (int)y + 116 + 30)) && b.movable) {
				moving = true;
				movingDown = true;
				velY = speed;
				prevY = y;
			}
		}
	}


	@Override
	public void render(Graphics g) {
		
		if(!moving) g.drawImage(image, (int)x, (int)y, width, height, null);
		else {
			if(movingLeft) {
				walkLeft.drawAnimation(g, (int)x, (int)y, width, height);
			} if(movingRight) {
				walkRight.drawAnimation(g, (int)x, (int)y, width, height);
			} if(movingUp) {
				walkUp.drawAnimation(g, (int)x, (int)y, width, height);
			} if(movingDown) {
				walkDown.drawAnimation(g, (int)x, (int)y, width, height);
			}
		}
		
	}

	@Override
	public Rectangle getBounds() {
		return null;
		
	}

}
