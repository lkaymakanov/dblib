package net.is_bg.ltf.db.common.impl.timer;


public class ElapsedTimerNano extends ElapsedTimer{

	@Override
	public void start() {
		// TODO Auto-generated method stub
		ElapsedTimeUtil.getStartTimeNano(attrib);
	}
	
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		ElapsedTimeUtil.getEndTimeNano(attrib);
	}
}
