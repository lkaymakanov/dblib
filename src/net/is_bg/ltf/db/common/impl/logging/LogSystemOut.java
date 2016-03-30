package net.is_bg.ltf.db.common.impl.logging;

import net.is_bg.ltf.db.common.interfaces.logging.ILog;


// TODO: Auto-generated Javadoc
/**
 * The Class LogSystemOut.
 */
public class LogSystemOut implements ILog{

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.interfaces.logging.ILog#isDebugEnabled()
	 */
	public boolean isDebugEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.interfaces.logging.ILog#isErrorEnabled()
	 */
	public boolean isErrorEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.interfaces.logging.ILog#isFatalEnabled()
	 */
	public boolean isFatalEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.interfaces.logging.ILog#isInfoEnabled()
	 */
	public boolean isInfoEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.interfaces.logging.ILog#isTraceEnabled()
	 */
	public boolean isTraceEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.interfaces.logging.ILog#isWarnEnabled()
	 */
	public boolean isWarnEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.interfaces.logging.ILog#trace(java.lang.Object)
	 */
	public void trace(Object message) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.interfaces.logging.ILog#trace(java.lang.Object, java.lang.Throwable)
	 */
	public void trace(Object message, Throwable t) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.interfaces.logging.ILog#debug(java.lang.Object)
	 */
	public void debug(Object message) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.interfaces.logging.ILog#debug(java.lang.Object, java.lang.Throwable)
	 */
	public void debug(Object message, Throwable t) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.interfaces.logging.ILog#info(java.lang.Object)
	 */
	public void info(Object message) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.interfaces.logging.ILog#info(java.lang.Object, java.lang.Throwable)
	 */
	public void info(Object message, Throwable t) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.interfaces.logging.ILog#warn(java.lang.Object)
	 */
	public void warn(Object message) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.interfaces.logging.ILog#warn(java.lang.Object, java.lang.Throwable)
	 */
	public void warn(Object message, Throwable t) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.interfaces.logging.ILog#error(java.lang.Object)
	 */
	public void error(Object message) {
		// TODO Auto-generated method stub
		System.err.println(message);
	}

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.interfaces.logging.ILog#error(java.lang.Object, java.lang.Throwable)
	 */
	public void error(Object message, Throwable t) {
		// TODO Auto-generated method stub
		System.err.println(message);
	}

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.interfaces.logging.ILog#fatal(java.lang.Object)
	 */
	public void fatal(Object message) {
		// TODO Auto-generated method stub
		System.err.println(message);
	}

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.interfaces.logging.ILog#fatal(java.lang.Object, java.lang.Throwable)
	 */
	public void fatal(Object message, Throwable t) {
		// TODO Auto-generated method stub
		System.err.println(message);
	}

	
}
