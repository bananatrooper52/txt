package dev.ce.txt.blocks.tiles;

import java.awt.image.BufferedImage;

import dev.ce.txt.assets.Assets;
import dev.ce.txt.blocks.Block;

public class Plank extends Block{

	public Plank(int id) {
		super(id);
	}

	@Override
	public BufferedImage texture() {
		return Assets.plank;
	}

}
