package com.gameconstructor.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {

	private int speed;
	private int frames;
	private int index = 0;
	private int count = 0;
	
	private BufferedImage[] images;
	private BufferedImage currentImg;
	
	public Animation(int speed, BufferedImage... args) {
		images = new BufferedImage[args.length];
		this.speed = speed;
		
		for(int i = 0; i < args.length; i++) {
			images[i] = args[i];
		}
		frames = args.length;
	}
	
	public void runAnimation() {
		index++;
		if(index > speed) {
			index = 0;
			nextFrame();
		}
	}
	
	private void nextFrame() {
		for(int i = 0; i < frames; i++) {
			if(count == i) currentImg = images[i];
		}
		
		count++;
		
		if(count > frames) {
			count = 0;
		}
	}
	
	public void drawAnimation(Graphics g, int x, int y, int width, int height) {
		g.drawImage(currentImg, x, y, width, height, null);
	}
	
}
