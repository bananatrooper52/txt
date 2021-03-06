package dev.ce.txt.scenes;

import java.awt.Color;
import java.awt.Graphics;

import dev.ce.txt.Conveyor;
import dev.ce.txt.assets.Assets;
import dev.ce.txt.gfx.gui.ClickListener;
import dev.ce.txt.gfx.gui.GUIButton;
import dev.ce.txt.gfx.gui.GameString;
import dev.ce.txt.world.World;

public class _Game extends Scene {

	public World world;
	private boolean showGUI;
	private boolean paused;

	public _Game(Conveyor conveyor) {
		super(conveyor);

		world = new World(conveyor);

		/*
		 * COPY AND PASTE BUTTON guiHandler.add(new
		 * GUIButton(conveyor.getGameWidth() / 2 - Assets.DEFAULTRENDEREDSIZE *
		 * 4, yCoord, Assets.DEFAULTRENDEREDSIZE * 8,
		 * Assets.DEFAULTRENDEREDSIZE, Assets.guiButton, "text1", "text2", new
		 * ClickListener() {
		 * 
		 * @Override public void onClick() { whathappenswhenclickedhere
		 * conveyor.getGameScene().getWorld().saveWorld();
		 * Scene.setScene(conveyor.getMenuScene()); } }));
		 */

		guiHandler.add(new GameString("PAUSED", 60, conveyor.getGameWidth() / 2 - 170, 10));
		guiHandler.add(new GUIButton(conveyor.getGameWidth() / 2 - Assets.DEFAULTRENDEREDSIZE * 4, 150,
				Assets.DEFAULTRENDEREDSIZE * 8, Assets.DEFAULTRENDEREDSIZE, Assets.guiButton, "OPTIONS",
				new ClickListener() {

					@Override
					public void onClick() {
						Scene.setSubScene(conveyor.getGame().getOptionsScene());
					}

				}));
		guiHandler.add(new GUIButton(conveyor.getGameWidth() / 2 - Assets.DEFAULTRENDEREDSIZE * 4, 225,
				Assets.DEFAULTRENDEREDSIZE * 8, Assets.DEFAULTRENDEREDSIZE, Assets.guiButton, "BACK TO MENU",
				new ClickListener() {

					@Override
					public void onClick() {
						Scene.setScene(conveyor.getGame().getMenuScene());
						setPaused(false);
					}

				}));

	}

	@Override
	public void tick() {

		paused = conveyor.getKeyHandler().paused;
		
		if (!paused) {
			conveyor.getWorld().tick();
		}

		if (showGUI && !guiHandler.getEnabled()) {
			guiHandler.enableGUIHandler();
		} else if (!showGUI && guiHandler.getEnabled()) {
			guiHandler.disableGUIHandler();
		}

		if (paused) {
			showGUI = true;
		} else {
			showGUI = false;
		}

	}

	@Override
	public void render(Graphics g) {

		conveyor.getWorld().render(g);

		if (paused) {
			g.setColor(new Color(0, 0, 0, 126));
			g.fillRect(0, 0, conveyor.getGameWidth(), conveyor.getGameHeight());
		}

		if (showGUI) {
			guiHandler.render(g);
		}

	}

	public World getWorld() {
		return world;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

}
