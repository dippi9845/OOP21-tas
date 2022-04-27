package main.java.tas.tower;

public interface UpgradableTower extends Tower {
	
	public int getLevel();
	
	public boolean upgradeable(final int money);
	
	public void upgrade();
}
