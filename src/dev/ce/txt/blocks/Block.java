package dev.ce.txt.blocks;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.ce.txt.assets.Assets;

public abstract class Block {
	
	protected BufferedImage texture;
	protected final int id;
	
	public static Block[] blocks = new Block[256];
	public static Block ungenerated = new Ungenerated(0);
	public static Block grass = new Grass(1);
	public static Block dirt = new Dirt(2);
	public static Block stone = new Stone(3);

	public int x;
	public int y;
	
	public Block(int id) {
		
		this.id = id;
		
		blocks[id] = this;
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture(), x, y, Assets.DEFAULTRENDEREDSIZE, Assets.DEFAULTRENDEREDSIZE, null);
	}
	
	public void tick() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public abstract BufferedImage texture();
	
}
