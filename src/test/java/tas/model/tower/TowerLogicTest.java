package test.java.tas.model.tower;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import main.java.tas.controller.tower.TowerLogicImpl;
import main.java.tas.model.enemy.Enemy;
import main.java.tas.model.tower.TowerBuilder;
import main.java.tas.utils.Position;

class TowerLogicTest {

	@Test
	void NearbyTower() {
		List<Enemy> a = new ArrayList<>();
		
		TowerLogicImpl t = new TowerLogicImpl(a, x->{}, x->{return true;});
		
		t.placeTower(new TowerBuilder(new Position(50, 50), 100, 100, 100, "bla bla"));
		
		assertTrue(t.thereIsTowerNear(new Position(51, 51)));
	}
	
	@Test
	void NearbyTowerTooFar() {
		List<Enemy> a = new ArrayList<>();
		
		TowerLogicImpl t = new TowerLogicImpl(a, x->{}, x->{return true;});
		
		t.placeTower(new TowerBuilder(new Position(50, 50), 100, 100, 100, "bla bla"));
		
		assertFalse(t.thereIsTowerNear(new Position(51, 192)));
	}

}
