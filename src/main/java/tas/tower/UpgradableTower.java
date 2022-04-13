package main.java.tas.tower;

public interface UpgradableTower extends Tower {
	
	public int getCost();
	
	public boolean upgradable(final int money);
	
	public boolean upgrade();
	
	
}
