package main.java.tas.tower;
import main.java.tas.model.enemies.Enemy;
import
public abstract class Tower {
	private Enemy target;
	private final int damage;
	private final Position pos;
	private final int raggio;
	private final int wait;
	
	abstract protected void Attacca();
	
	
}
