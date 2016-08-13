package dev.ce.txt.scenes;

import java.awt.Graphics;
import java.io.File;

import dev.ce.txt.Conveyor;
import dev.ce.txt.assets.Assets;
import dev.ce.txt.gfx.gui.ClickListener;
import dev.ce.txt.gfx.gui.GUIButton;
import dev.ce.txt.gfx.gui.GUIHandler;

public class _Options extends Scene {

	public GUIHandler guiHandler;

	private boolean perBlockMovement;
	
	public _TexturePack texturePackScene;

	public _Options(Conveyor conveyor) {
		super(conveyor);
		guiHandler = new GUIHandler(conveyor);
		texturePackScene = new _TexturePack(conveyor);

		guiHandler.add(new GUIButton(conveyor.getGameWidth() / 2 - Assets.DEFAULTRENDEREDSIZE * 4, 75,
				Assets.DEFAULTRENDEREDSIZE * 8, Assets.DEFAULTRENDEREDSIZE, Assets.guiButton, "Blocky Movement",
				"Smooth Movement", new ClickListener() {

					@Override
					public void onClick() {
						Assets.perBlockMovement = !Assets.perBlockMovement;
						perBlockMovement = !perBlockMovement;
					}

				}));

		guiHandler.add(new GUIButton(conveyor.getGameWidth() / 2 - Assets.DEFAULTRENDEREDSIZE * 4, 150,
				Assets.DEFAULTRENDEREDSIZE * 8, Assets.DEFAULTRENDEREDSIZE, Assets.guiButton, "texture packs", 
				new ClickListener() {

					@Override
					public void onClick() {
						Scene.setScene(texturePackScene);

					}

				}));

		guiHandler.add(new GUIButton(conveyor.getGameWidth() / 2 - Assets.DEFAULTRENDEREDSIZE * 4, 525,
				Assets.DEFAULTRENDEREDSIZE * 8, Assets.DEFAULTRENDEREDSIZE, Assets.guiButton, "back",
				new ClickListener() {

					@Override
					public void onClick() {
						Scene.setScene(conveyor.getMenuScene());

					}

				}));

	}

	@Override
	public void tick() {
		guiHandler.tick();
	}

	@Override
	public void render(Graphics g) {
		guiHandler.render(g);
	}

	public GUIHandler getGUIHandler() {
		return guiHandler;
	}

	class _TexturePack extends Scene {

		public GUIHandler guiHandler;
		private int texturePacksLoaded;
		private String[] files;
		private File dir;

		public _TexturePack(Conveyor conveyor) {
			super(conveyor);

			guiHandler = new GUIHandler(conveyor);

			guiHandler.add(new GUIButton(40 + Assets.DEFAULTRENDEREDSIZE * 8, 20 + Assets.DEFAULTRENDEREDSIZE,
					Assets.DEFAULTRENDEREDSIZE * 8, Assets.DEFAULTRENDEREDSIZE, Assets.guiButton, "back",
					new ClickListener() {

						@Override
						public void onClick() {
							Scene.setScene(conveyor.getGame().getOptionsScene());

						}

					}));

			initializeTexturePacks("resources/textures/");

		}

		public void initializeTexturePacks(String path) {
			dir = new File(path);
			files = dir.list();
			for (String file : files) {
				loadTexturePack(file);
				texturePacksLoaded++;
			}

		}

		public void loadTexturePack(String path) {

			guiHandler.add(
					new GUIButton(20, 20 + 20 * texturePacksLoaded + Assets.DEFAULTRENDEREDSIZE * texturePacksLoaded,
							Assets.DEFAULTRENDEREDSIZE * 8, Assets.DEFAULTRENDEREDSIZE, Assets.guiButton, path,
							new ClickListener() {

								@Override
								public void onClick() {
									Assets.setTexturePack(path);
								}

							}));
		}

		@Override
		public void tick() {
			guiHandler.tick();
		}

		@Override
		public void render(Graphics g) {
			guiHandler.render(g);
		}

		public GUIHandler getGUIHandler() {
			return guiHandler;
		}

	}

}