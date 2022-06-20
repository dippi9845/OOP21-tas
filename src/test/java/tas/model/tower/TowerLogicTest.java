package test.java.tas.model.tower;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import main.java.tas.controller.tower.TowerController;
import main.java.tas.controller.tower.TowerControllermpl;
import main.java.tas.model.enemy.Enemy;
import main.java.tas.model.tower.TowerBuilder;
import main.java.tas.utils.Position;

class TowerLogicTest {

	@Test
	void NearbyTower() {
		List<Enemy> a = new ArrayList<>();
		
		TowerController t = new TowerControllermpl(a, x->{}, x->{return true;});
		
		t.placeTower(new TowerBuilder(new Position(50, 50), 100, 100, 100, "bla bla"));
		
		assertTrue(t.thereIsTowerNear(new Position(51, 51)));
	}
	
	@Test
	void NearbyTowerTooFar() {
		List<Enemy> a = new ArrayList<>();
		
		TowerController t = new TowerControllermpl(a, x->{}, x->{return true;});
		
		t.placeTower(new TowerBuilder(new Position(50, 50), 100, 100, 100, "bla bla"));
		
		assertFalse(t.thereIsTowerNear(new Position(51, 192)));
	}
	
	@Test
	void AddTower() {
		List<Enemy> a = new ArrayList<>();
		
		TowerController t = new TowerControllermpl(a, x->{}, x->{return true;});
		
		t.placeTower(new TowerBuilder(new Position(50, 50), 100, 100, 100, "bla bla"));
		
		assertTrue(t.getBuildTowers().size() == 1);
	}
	
	@Test
	void AddTowerThread() {
		List<Enemy> a = new ArrayList<>();
		
		TowerController t = new TowerControllermpl(a, x->{}, x->{return true;});
		
		t.placeTower(new TowerBuilder(new Position(50, 50), 100, 100, 100, "bla bla"));
		
		assertTrue(t.getBuildThread().size() == 1);
	}
	
	@Test
	void IsThreadRunning() {
		List<Enemy> a = new ArrayList<>();
		
		TowerController t = new TowerControllermpl(a, x->{}, x->{return true;});
		
		t.placeTower(new TowerBuilder(new Position(50, 50), 100, 100, 100, "bla bla"));
		
		assertTrue(t.getBuildThread().get(0).isAlive());
	}
	
	@Test
	void AreThreadRunning() {
		List<Enemy> a = new ArrayList<>();
		
		TowerController t = new TowerControllermpl(a, x->{}, x->{return true;});
		
		t.placeTower(new TowerBuilder(new Position(50, 50), 100, 100, 100, "bla bla"));
		t.placeTower(new TowerBuilder(new Position(50, 50), 100, 100, 100, "bla bla"));
		
		assertTrue(t.getBuildThread().get(0).isAlive() && t.getBuildThread().get(1).isAlive());
	}
	
	@Test
	void ClearFromTowers() {
		List<Enemy> a = new ArrayList<>();
		
		TowerController t = new TowerControllermpl(a, x->{}, x->{return true;});
		
		t.placeTower(new TowerBuilder(new Position(50, 50), 100, 100, 100, "bla bla"));
		t.placeTower(new TowerBuilder(new Position(50, 50), 100, 100, 100, "bla bla"));
		
		t.closeAll();
		
		assertTrue(t.getBuildTowers().size() == 0);
	}
	
	void ClearFromThreads() {
		List<Enemy> a = new ArrayList<>();
		
		TowerController t = new TowerControllermpl(a, x->{}, x->{return true;});
		
		t.placeTower(new TowerBuilder(new Position(50, 50), 100, 100, 100, "bla bla"));
		t.placeTower(new TowerBuilder(new Position(50, 50), 100, 100, 100, "bla bla"));
		
		t.closeAll();
		
		assertTrue(t.getBuildThread().size() == 0);
	}
}
