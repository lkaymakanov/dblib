package net.is_bg.ltf.db.common.impl.timer;

import net.is_bg.ltf.db.common.interfaces.timer.IElaplsedTimer;



// TODO: Auto-generated Javadoc
/**
 * <pre>
 * Wrap ElapsedTimeAttributes &
 * ElapsedTime Helper functions in separate class
 * to restrict access to public members in ElapsedTimeAttributes
 * and provide a higher level
 * API for measuring Activity time!
 * </pre>.
 *
 * @author lubo
 */
public class ElapsedTimer implements IElaplsedTimer{
	
	/** The attrib. */
	protected ElapsedTimeAttributes attrib = new ElapsedTimeAttributes();
	
	/**
	 * Start timer.
	 */
	public void start(){
		ElapsedTimeUtil.getStartTimeMillis(attrib);
	}
	
	/**
	 * Stop  timer.
	 */
	public void stop(){
		ElapsedTimeUtil.getEndTimeMillis(attrib);
	}
	
	
	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.interfaces.timer.IElaplsedTimer#getStartTime()
	 */
	public long getStartTime(){
		return attrib.startTime;
	}
	
	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.interfaces.timer.IElaplsedTimer#getEndTime()
	 */
	public long getEndTime(){
		return attrib.endTime;
	}
	
	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.interfaces.timer.IElaplsedTimer#getDuration()
	 */
	public long getDuration(){
		return attrib.duration;
	}

}
