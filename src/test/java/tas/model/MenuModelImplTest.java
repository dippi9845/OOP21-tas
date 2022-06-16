package test.java.tas.model;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import main.java.tas.model.menu.MenuModel;
import main.java.tas.model.menu.MenuModelImpl;

public class MenuModelImplTest {
	private final Random rand = new Random();
	private final int nTests = 4;
	private final MenuModel model = new MenuModelImpl();

	@Test
	public void testMainScene() {

		for (int i = 0; i < this.nTests - 1; i++) {
			final int newMainScene = this.rand.nextInt(6);
			this.model.setMainScene(newMainScene);
			assertEquals(newMainScene, this.model.getMainScene());
		}
	}

	@Test
	public void testCurrentLevel() {

		for (int i = 0; i < this.nTests - 1; i++) {
			final int newCurrentLevel = this.rand.nextInt(6);
			this.model.setCurrentLevel(newCurrentLevel);
			assertEquals(newCurrentLevel, this.model.getCurrentLevel());
		}
	}

	@Test
	public void testMenuMode() {

		for (int i = 0; i < this.nTests - 1; i++) {
			final int newMenuMode = this.rand.nextInt(6);
			this.model.setMenuMode(newMenuMode);
			assertEquals(newMenuMode, this.model.getMenuMode());
		}
	}

}