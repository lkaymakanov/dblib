package net.is_bg.ltf.db.common.impl.timer;


// TODO: Auto-generated Javadoc
/**
 * The Class ElapsedTimerNano.
 */
public class ElapsedTimerNano extends ElapsedTimer{

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.impl.timer.ElapsedTimer#start()
	 */
	@Override
	public void start() {
		// TODO Auto-generated method stub
		ElapsedTimeUtil.getStartTimeNano(attrib);
	}
	
	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.impl.timer.ElapsedTimer#stop()
	 */
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		ElapsedTimeUtil.getEndTimeNano(attrib);
	}
}
