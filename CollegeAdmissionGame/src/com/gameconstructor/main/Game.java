package com.gameconstructor.main;
//Notes:
//


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, MouseListener, MouseMotionListener, KeyListener {

	private static final long serialVersionUID = -2421751466973514721L;
	private BufferedImage spritesheet = null;
	public static final int WIDTH = 800, HEIGHT = 600;
	private Thread thread;
	private boolean running = false;
	private Player player;
	
	public static BufferedImage[] images = new BufferedImage[12];
	
	public static Box[] boxes = new Box[30];
	
	public boolean popup = false;
	public Popup pop;
	
	public static boolean gameOver = false;
	
	public static String update = "";
	public static int timer = 0;

	
	public void init() {
		int counter = 0;
		for(int j = 0; j < 6; j++) {
			for(int i = 0; i < 5; i++) {
				Color tempColor = Color.ORANGE;
				if(counter % 2 == 0) tempColor = Color.YELLOW; 
				boxes[counter] = new Box(j * (WIDTH/6), i * 116, WIDTH/6, 116, tempColor, counter);
				
				for(int a = 0; a < 6; a++) {
					for(int b = 1; b < 4; b++) {
						Point p = new Point(WIDTH/6 * a, (116 * b) + 10);
						if(boxes[counter].getRect().contains(p)) {
							boxes[counter].movable = true;
						}
					}
					
				}
				
				counter++;
			}
		}
		
		requestFocus();
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
			try {

				spritesheet = loader.loadImage("/sprite_sheet.png");

			} catch (IOException e) {
				e.printStackTrace();
			}
			
		SpriteSheet ss = new SpriteSheet(spritesheet);
		images[0] = ss.grabImage(1, 1, 64, 64);
		images[1] = ss.grabImage(2, 1, 64, 64);
		images[2] = ss.grabImage(3, 1, 64, 64);
		images[3] = ss.grabImage(4, 1, 64, 64);
		images[4] = ss.grabImage(1, 2, 64, 64);
		images[5] = ss.grabImage(2, 2, 64, 64);
		images[6] = ss.grabImage(3, 2, 64, 64);
		images[7] = ss.grabImage(4, 2, 64, 64);
		images[8] = ss.grabImage(1, 3, 64, 64);
		images[9] = ss.grabImage(2, 3, 64, 64);
		images[10] = ss.grabImage(3, 3, 64, 64);
		images[11] = ss.grabImage(4, 3, 64, 64);
		
		player = new Player(WIDTH / 2 - 132, HEIGHT/2 - 70, ID.Player, images);
		pop = new Popup(this);
		
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		init();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		int ticks = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				ticks++;
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				System.out.println("Ticks: " + ticks);
				frames = 0;
				ticks = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		player.tick();
		
		if(popup) {
			pop.tick();
		}
		
		for(Box b: boxes) {
			b.tick();
		}
		
		if(!update.equals("")) {
			timer++;
		}
		
		if(timer >= 300) {
			update = "";
			timer = 0;
		}

	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D)g.create();
		
		//Graphics here v
		
			g.setColor(Color.white);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			
		if(!pop.displayComplete) {	
			
			for(Box b: boxes) {
				b.render(g);
			}
			
			player.render(g);
			
			g.setColor(Color.WHITE);
			g.fillRect(WIDTH - 305, HEIGHT - 55, 300, 30);
			g.setColor(Color.BLACK);
			g.drawRect(WIDTH - 305, HEIGHT - 55, 300, 30);
			g.setFont(new Font("arial", Font.PLAIN, 20));
			g.drawString(update, WIDTH - 300, HEIGHT - 33);
		}
		
		if(popup) {
			pop.render(g2d);
		}
		//Graphics here ^
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]) {
		JFrame frame = new JFrame("Josef's College Admission Process");
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		Game game = new Game();
		frame.add(game);
		frame.setVisible(true);
		game.start();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		//System.out.println("In");
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		//System.out.println("Out");
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		//System.out.println("Off");
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		//System.out.println("Drag");
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		//System.out.println("Move");
		
	}
	
	public static int clamp(int var, int min, int max) {
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(!player.moving && !popup && !gameOver) {
			if(key == KeyEvent.VK_LEFT) {
				player.moveLeft();
				player.image = images[9];
			}
			if(key == KeyEvent.VK_RIGHT) {
				player.moveRight();
				player.image = images[11];
				if(player.getBox().ID == 27) {
					pop.type = 4;
					popup = true;
				}
			}
			if(key == KeyEvent.VK_UP) {
				player.moveUp();
				player.image = images[10];
				if(player.getBox().ID == 10 || player.getBox().ID == 15) {
					pop.type = 3;
					popup = true;
				}
			}
			if(key == KeyEvent.VK_DOWN) {
				player.moveDown();
				player.image = images[0];
				if(player.getBox().ID == 3) {
					pop.type = 1;
					popup = true;
				}
				if(player.getBox().ID == 8) {
					pop.type = 2;
					popup = true;
				}
			}
		}
		if(popup && !gameOver) {
			if(pop.type == 3 && key != KeyEvent.VK_UP) popup = false;
			if((pop.type == 1 || pop.type == 2) && key != KeyEvent.VK_DOWN) popup = false;
			if(pop.type == 4 && key != KeyEvent.VK_RIGHT) popup = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
}
