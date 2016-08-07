package dev.ce.txt.entities;

import java.awt.Graphics;
import java.util.Random;

import dev.ce.txt.Game;
import dev.ce.txt.assets.Assets;

public class Frownie extends Entity {

	private int speed = 3;
	private boolean direction[];
	
	public Frownie(int x, int y, int width, int height) {
		
		super(x, y, width, height);
		
		direction = new boolean[4];
		texture = Assets.frownie;
		
	}

	@Override
	public void tick() {
		
		Random rand = new Random();
		int randInt = rand.nextInt(100);
		
		if(randInt >= 97) {
			
			changeCourse();
			
		}
		
		if(direction[0] == true) {
			x -= speed;
		}
		
		if(direction[1] == true) {
			x += speed;
		}
		
		if(direction[2] == true) {
			y += speed;
		}
		
		if(direction[3] == true) {
			y -= speed;
		}
		
		if(x + Assets.DEFAULTRENDEREDSIZE > Game.WIDTH) {
			x = Game.WIDTH - Assets.DEFAULTRENDEREDSIZE;
		}
		
		if(x < 0) {
			x = 0;
		}
		
		if(y + Assets.DEFAULTRENDEREDSIZE > Game.HEIGHT) {
			y = Game.HEIGHT - Assets.DEFAULTRENDEREDSIZE;
		}
		
		if(y < 0) {
			y = 0;
		}
		
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(texture, x, y, Assets.DEFAULTRENDEREDSIZE, Assets.DEFAULTRENDEREDSIZE, null);
		
	}
	
	public void changeCourse() {
		
		Random rand = new Random();
		
		for(int i = 0; i < 4; i++) {
			
			int randInt = rand.nextInt(2);
			
			if(randInt == 0) {
				direction[i] = true;
			} else {
				direction[i] = false;
			}
			
		}
		
	}
	
}