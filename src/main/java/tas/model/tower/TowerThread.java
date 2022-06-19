package main.java.tas.model.tower;

/**
 * An interface that model a tower, with his own thread dedicated
 *
 */
public interface TowerThread extends Tower, Runnable{
	
	/**
	 * Return true if is time to stop for the thread, false if the current thread can go on
	 * 
	 * @return true if is time to stop for the thread, false if the current thread can go on
	 */
	public boolean isStop();

	/**
	 * This function is called when is necessary to stop the run method {@link TowerThread#run()},
	 * after a call to this method {@link TowerThread#isStop()} must return true
	 */
	public void stop();

	/** {@inheritDoc} */
	default void run() {
		while (!this.isStop()) {
			this.compute();
		}
	}
}
