package com.gameconstructor.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Box {

	public boolean movable = false;
	
	int x, y, width, height;
	Color color;
	int ID;
	
	public Box(int x, int y, int width, int height, Color color, int ID) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.ID = ID;
		
		if(ID == 10 || ID == 15 || ID == 14 || ID == 19 || ID == 24 || ID == 29) movable = true;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
		
		if(ID == 4) {
			g.setColor(new Color(113, 64, 0));
			g.fillRect(x + 20, y + 10, width - 20, height - 20);
			g.setColor(Color.BLACK);
			g.fillRect(x + 30, y + 20, width - 50, height - 50);
			g.setColor(Color.CYAN);
			g.fillRect(x + 32, y + 20, width - 54, 2);
		}
		if(ID == 9) {
			g.setColor(new Color(113, 64, 0));
			g.fillRect(x, y + 10, width - 20, height - 20); 
			g.setColor(Color.WHITE);
			g.fillRect(x + 15, y + 30, 40, 55);
		}
		if(ID == 10 || ID == 15) {
			g.setColor(new Color(113, 64, 0));
			g.fillRect(x, y, width, 20);
		}
		if(ID == 0 || ID == 5 || ID == 20 || ID == 25) {
			g.setColor(Color.BLACK);
			g.fillRect(x, y, width, height / 2);
		}
		if(ID == 27) {
			g.setColor(Color.WHITE);
			g.fillRect(x + width - 20, y, 20, height);
		}
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);
	}
	
}
