package net.is_bg.ltf.db.common.impl.logging;

import net.is_bg.ltf.db.common.interfaces.logging.ILog;


public class LogSystemOut implements ILog{

	public boolean isDebugEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isErrorEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isFatalEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isInfoEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isTraceEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isWarnEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public void trace(Object message) {
		// TODO Auto-generated method stub
		
	}

	public void trace(Object message, Throwable t) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}

	public void debug(Object message) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}

	public void debug(Object message, Throwable t) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}

	public void info(Object message) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}

	public void info(Object message, Throwable t) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}

	public void warn(Object message) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}

	public void warn(Object message, Throwable t) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}

	public void error(Object message) {
		// TODO Auto-generated method stub
		System.err.println(message);
	}

	public void error(Object message, Throwable t) {
		// TODO Auto-generated method stub
		System.err.println(message);
	}

	public void fatal(Object message) {
		// TODO Auto-generated method stub
		System.err.println(message);
	}

	public void fatal(Object message, Throwable t) {
		// TODO Auto-generated method stub
		System.err.println(message);
	}

	
}
