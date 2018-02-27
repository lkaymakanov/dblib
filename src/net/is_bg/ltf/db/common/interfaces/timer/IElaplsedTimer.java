package net.is_bg.ltf.db.common.interfaces.timer;


public interface IElaplsedTimer {

	/**
	 * Start timer 
	 */
	public void start();
	/**
	 * Stop  timer
	 */
	public void stop();
	
	public long getStartTime();
	public long getEndTime();
	public long getDuration();
}
