package net.is_bg.ltf.db.common.interfaces.timer;


// TODO: Auto-generated Javadoc
/**
 * The Interface IElaplsedTimer.
 */
public interface IElaplsedTimer {

	/**
	 * Start timer.
	 */
	public void start();
	
	/**
	 * Stop  timer.
	 */
	public void stop();
	
	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public long getStartTime();
	
	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	public long getEndTime();
	
	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public long getDuration();
}
