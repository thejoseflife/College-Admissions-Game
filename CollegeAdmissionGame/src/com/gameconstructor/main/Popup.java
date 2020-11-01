package com.gameconstructor.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

public class Popup implements MouseListener {
	
	public int SATST, study, blog, essay, AP;
	
	public boolean observatory, tennis, service, coaching, SAT;
	
	public boolean checklistComplete = false;
	public boolean displayComplete = false;
	
	public int type = 3; //1 = Computer, 2 = Desk, 3 = Door, 4 = Checklist
	
	public RoundRectangle2D display = new RoundRectangle2D.Double(100, 100, Game.WIDTH - 200, 300, 50, 50);
	
	public Rectangle observatoryButton = new Rectangle(130, 200, 240, 80);
	public Rectangle tennisButton = new Rectangle(430, 200, 240, 80);
	public Rectangle serviceButton = new Rectangle(130, 300, 240, 80);
	public Rectangle coachingButton = new Rectangle(430, 300, 240, 80);
	
	public int timer = 0;
	
	public Popup(Game game) {
		game.addMouseListener(this);
	}
	
	public void tick() {
		if(observatory && tennis && service && coaching && SAT && SATST == 2 && study == 10 && essay == 3 && AP == 3) {
			checklistComplete = true;
		}
		
		if(Game.gameOver && timer < 116 * 5 - 2) {
			timer += 5;
		}
		if(timer >= 116 * 5 - 2) {
			timer = 116 * 5 - 2;
			displayComplete = true;
			type = 0;
		}
	}
	
	public void render(Graphics2D g) {
		//Door activities
		if(type == 3) {
			g.setColor(Color.WHITE);
			g.fill(display);
			g.setFont(new Font("arial", Font.BOLD, 35));
			g.setColor(Color.BLACK);
			g.drawString("Extracurricular Activities", 190, 150);
			if(observatory) g.setColor(new Color(210, 210, 210)); 
			else g.setColor(Color.WHITE);
			g.fill(observatoryButton);
			if(tennis) g.setColor(new Color(210, 210, 210)); 
			else g.setColor(Color.WHITE);
			g.fill(tennisButton);
			if(service) g.setColor(new Color(210, 210, 210)); 
			else g.setColor(Color.WHITE);
			g.fill(serviceButton);
			if(coaching) g.setColor(new Color(210, 210, 210)); 
			else g.setColor(Color.WHITE);
			g.fill(coachingButton);
			g.setColor(Color.BLACK);
			g.draw(observatoryButton);
			g.draw(tennisButton);
			g.draw(serviceButton);
			g.draw(coachingButton);
			g.setFont(new Font("arial", Font.BOLD, 25));
			g.drawString("Do nothing useful", 142, 247);
			g.drawString("Play tennis", 484, 247);
			g.drawString("Community service", 135, 347);
			g.drawString("Coach tennis", 469, 347);
		}
		//Computer
		if(type == 1) {
			g.setColor(Color.WHITE);
			g.fill(display);
			g.setFont(new Font("arial", Font.BOLD, 35));
			g.setColor(Color.BLACK);
			g.drawString("Computer", 320, 150);
			if(essay == 3) g.setColor(new Color(210, 210, 210)); 
			else g.setColor(Color.WHITE);
			g.fill(observatoryButton);
			g.setColor(Color.WHITE);
			g.fill(tennisButton);
			g.setColor(Color.BLACK);
			g.draw(observatoryButton);
			g.draw(tennisButton);
			g.setFont(new Font("arial", Font.BOLD, 25));
			g.drawString("Do CommonApp", 151, 247);
			g.drawString("TheJosefLife post", 443, 247);
			if(checklistComplete) {
				g.draw(serviceButton);
				g.drawString("Apply to colleges", 146, 347);
			}
		
		}
		//Desk
		if(type == 2) {
			g.setColor(Color.WHITE);
			g.fill(display);
			g.setFont(new Font("arial", Font.BOLD, 35));
			g.setColor(Color.BLACK);
			g.drawString("Desk", 358, 150);
			if(study == 10) g.setColor(new Color(210, 210, 210)); 
			else g.setColor(Color.WHITE);
			g.fill(observatoryButton);
			if(SAT) g.setColor(new Color(210, 210, 210)); 
			else g.setColor(Color.WHITE);
			g.fill(tennisButton);
			if(SATST == 2) g.setColor(new Color(210, 210, 210)); 
			else g.setColor(Color.WHITE);
			g.fill(serviceButton);
			if(AP == 3) g.setColor(new Color(210, 210, 210)); 
			else g.setColor(Color.WHITE);
			g.fill(coachingButton);
			g.setColor(Color.BLACK);
			g.draw(observatoryButton);
			g.draw(tennisButton);
			g.draw(serviceButton);
			g.draw(coachingButton);
			g.setFont(new Font("arial", Font.BOLD, 25));
			g.drawString("Do schoolwork", 160, 247);
			g.drawString("Take SAT w/ Essay", 437, 247);
			g.drawString("SAT Subject Tests", 140, 347);
			g.drawString("AP Tests", 500, 347);
			
		
		}
		//Checklist
		if(type == 4) {
			g.setColor(Color.WHITE);
			g.fill(display);
			g.setFont(new Font("arial", Font.BOLD, 35));
			g.setColor(Color.BLACK);
			g.drawString("Checklist", 320, 150);
			if(essay == 3) g.setColor(new Color(210, 210, 210)); 
			g.setFont(new Font("arial", Font.PLAIN, 25));
			if(SAT) g.setColor(Color.BLUE);
			else g.setColor(Color.BLACK);
			g.drawString("SAT with Essay", 151, 227);
			if(SATST > 0) g.setColor(Color.BLUE);
			else g.setColor(Color.BLACK);
			g.drawString("Subject Test Math", 443, 227);
			if(SATST > 1) g.setColor(Color.BLUE);
			else g.setColor(Color.BLACK);
			g.drawString("Subject Test Science", 151, 257);
			if(service) g.setColor(Color.BLUE);
			else g.setColor(Color.BLACK);
			g.drawString("Community Service", 443, 257);
			if(coaching) g.setColor(Color.BLUE);
			else g.setColor(Color.BLACK);
			g.drawString("Tennis Coaching", 151, 287);
			if(tennis) g.setColor(Color.BLUE);
			else g.setColor(Color.BLACK);
			g.drawString("Play Tennis", 443, 287);
			if(AP == 3) g.setColor(Color.BLUE);
			else g.setColor(Color.BLACK);
			g.drawString("AP Tests", 151, 317);
			if(study == 10) g.setColor(Color.BLUE);
			else g.setColor(Color.BLACK);
			g.drawString("Finish School", 443, 317);
			if(essay == 3) g.setColor(Color.BLUE);
			else g.setColor(Color.BLACK);
			g.drawString("Complete Commonapp", 151, 347);
			if(observatory) g.setColor(Color.BLUE);
			else g.setColor(Color.BLACK);
			g.drawString("Be a lazy bum", 443, 347);
			if(checklistComplete) g.setColor(Color.BLACK);
			else g.setColor(Color.WHITE);
			g.setFont(new Font("arial", Font.BOLD, 25));
			g.drawString("Ready to apply to colleges!", 240, 387);
		
		}
		
		if(Game.gameOver) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, Game.WIDTH, timer);
			g.setColor(Color.BLACK);
			g.drawRect(0, 0, Game.WIDTH, timer);
			
			if(timer == 116 * 5 - 2) {
				int offset = -40;
				int offset1 = 0;
				g.setFont(new Font("arial", Font.BOLD, 35));
				g.setColor(Color.BLACK);
				g.drawString("A few months later...", 225, 50);
				
				g.setColor(Color.BLACK);
				g.setFont(new Font("arial", Font.PLAIN, 20));
				g.drawString("Massachusetts Institute of Technology - ", 115 + offset, 100);
				g.setColor(new Color(219, 0, 0));
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("REJECTED", 476 + offset, 100);
				
				g.setColor(new Color(90, 90, 90));
				g.setFont(new Font("arial", Font.PLAIN, 20));
				g.drawString("Cornell University - ", 115 + offset, 123);
				g.setColor(new Color(0, 130, 255));
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("WAITLISTED (Computer Science)", 290 + offset, 123);
				
				g.setColor(Color.BLACK);
				g.setFont(new Font("arial", Font.PLAIN, 20));
				g.drawString("Dartmouth College - ", 115 + offset, 146);
				g.setColor(new Color(219, 0, 0));
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("REJECTED", 300 + offset, 146);
				
				g.setColor(new Color(90, 90, 90));
				g.setFont(new Font("arial", Font.PLAIN, 20));
				g.drawString("Brown University - ", 115 + offset, 169);
				g.setColor(new Color(219, 0, 0));
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("REJECTED", 284 + offset, 169);
				
				g.setColor(Color.BLACK);
				g.setFont(new Font("arial", Font.PLAIN, 20));
				g.drawString("University of Pennsylvania - ", 115 + offset, 192);
				g.setColor(new Color(219, 0, 0));
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("REJECTED", 367 + offset, 192);
				
				g.setColor(new Color(90, 90, 90));
				g.setFont(new Font("arial", Font.PLAIN, 20));
				g.drawString("University of Chicago - ", 115 + offset, 215);
				g.setColor(new Color(219, 0, 0));
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("REJECTED", 322 + offset, 215);
				
				g.setColor(Color.BLACK);
				g.setFont(new Font("arial", Font.PLAIN, 20));
				g.drawString("Vanderbilt University - ", 115 + offset, 261 - 23);
				g.setColor(new Color(0, 0, 120));
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("WAITLISTED (WITHDRAWN)", 316 + offset, 261 - 23);
				
				g.setColor(new Color(90, 90, 90));
				g.setFont(new Font("arial", Font.PLAIN, 20));
				g.drawString("Johns Hopkins University - ", 115 + offset, 284 - 23);
				g.setColor(new Color(219, 0, 0));
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("REJECTED", 358 + offset, 284 - 23);
				
				g.setColor(Color.BLACK);
				g.setFont(new Font("arial", Font.PLAIN, 20));
				g.drawString("Georgia Institute of Technology - ", 115 + offset, 307 - 23);
				g.setColor(new Color(0, 130, 255));
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("WAITLISTED (Mechanical Engineering)", 413 + offset, 307 - 23);
				
				g.setColor(new Color(90, 90, 90));
				g.setFont(new Font("arial", Font.PLAIN, 20));
				g.drawString("Carnegie Mellon University - ", 115 + offset, 330 - 23);
				g.setColor(new Color(0, 0, 120));
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("WAITLISTED (WITHDRAWN)", 371 + offset, 330 - 23);
				
				g.setColor(Color.BLACK);
				g.setFont(new Font("arial", Font.PLAIN, 20));
				g.drawString("Emory University - ", 115 + offset, 399 - 69);
				g.setColor(new Color(41, 200, 0));
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("ACCEPTED (Mathematics)", 285 + offset, 399 - 69);
				
				g.setColor(new Color(90, 90, 90));
				g.setFont(new Font("arial", Font.PLAIN, 20));
				g.drawString("University of Michigan - ", 115 + offset, 422 - 69);
				g.setColor(new Color(41, 200, 0));
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("ACCEPTED (Computer Science)", 330 + offset, 422 - 69);
				
				g.setColor(Color.BLACK);
				g.setFont(new Font("arial", Font.PLAIN, 20));
				g.drawString("University of Virginia - ", 115 + offset, 445 - 69);
				g.setColor(new Color(41, 200, 0));
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("ACCEPTED (Computer Science)", 315 + offset, 445 - 69);
				
				g.setColor(new Color(90, 90, 90));
				g.setFont(new Font("arial", Font.PLAIN, 20));
				g.drawString("University of Rochester - ", 115 + offset, 468 - 69);
				g.setColor(new Color(41, 200, 0));
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("ACCEPTED (Computer Science)", 342 + offset, 468 - 69);
				
				g.setColor(Color.BLACK);
				g.setFont(new Font("arial", Font.PLAIN, 20));
				g.drawString("Purdue University - ", 115 + offset, 491 - 69);
				g.setColor(new Color(41, 200, 0));
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("ACCEPTED (Computer Science)", 291 + offset, 491 - 69);
				
				g.setColor(new Color(90, 90, 90));
				g.setFont(new Font("arial", Font.PLAIN, 20));
				g.drawString("Boston University - ", 115 + offset, 514 - 69);
				g.setColor(new Color(41, 200, 0));
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("ACCEPTED (Computer Engineering)", 290 + offset, 514 - 69);
				
				g.setColor(Color.BLACK);
				g.setFont(new Font("arial", Font.PLAIN, 20));
				g.drawString("Rutgers University at NB - ", 115 + offset, 537 - 69);
				g.setColor(new Color(41, 200, 0));
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("ACCEPTED (Computer Science + Business)", 353 + offset, 537 - 69);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point p = new Point(e.getX(), e.getY());
		if(type == 3) {
			if(observatoryButton.contains(p) && !observatory) {
				observatory = true;
				Game.timer = 0;
				Game.update = "Did absolutely nothing.";
			}
			if(tennisButton.contains(p) && !tennis) {
				tennis = true;
				Game.timer = 0;
				Game.update = "Went to play tennis.";
			}
			if(serviceButton.contains(p) && !service) {
				service = true;
				Game.timer = 0;
				Game.update = "Did community service.";
			}
			if(coachingButton.contains(p) && !coaching) {
				coaching = true;
				Game.timer = 0;
				Game.update = "Coached tennis.";
			}
		}
		if(type == 1) {
			if(observatoryButton.contains(p) && essay < 3) {
				Game.timer = 0;
				if(essay == 0) {
					Game.update = "Drafted personal statement.";
				} else if(essay == 1) {
					Game.update = "Drafted school supplements.";
				} else if(essay == 2) {
					Game.update = "Finished all essays.";
				}
				essay++;
			}
			if(tennisButton.contains(p)) {
				Game.timer = 0;
				Game.update = "Posted on @TheJosefLife.";
			}
			if(checklistComplete && serviceButton.contains(p)) {
				finishGame();
			}
		}
		if(type == 2) {
			if(observatoryButton.contains(p) && study < 10) {
				Game.timer = 0;
				if(study < 9) Game.update = "Studied " + (study + 1) + "/10";
				if(study == 9) Game.update = "School complete: 3.8 UW, 7 APs";
				study++;
			}
			if(tennisButton.contains(p) && !SAT) {
				SAT = true;
				Game.timer = 0;
				Game.update = "Scored 1510 and 6/4/6 Essay";
			}
			if(serviceButton.contains(p) && SATST < 2) {
				Game.timer = 0;
				if(SATST == 0) Game.update = "Scored 800 Math 2";
				if(SATST == 1) Game.update = "Scored 650 Bio-E(Won't send)";
				
				SATST++;
			}
			if(coachingButton.contains(p) && AP < 3) {
				Game.timer = 0;
				if(AP == 0) Game.update = "Scored 4/5 CompSci A";
				if(AP == 1) Game.update = "Scored 4/5 English Lang";
				if(AP == 2) Game.update = "Scored 2 Euro Hist(Won't send)";
				
				AP++;
			}
		}
		
	}
	
	public void finishGame() {
		Game.gameOver = true;
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
