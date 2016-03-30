package net.is_bg.ltf.db.common.impl.logging;

import net.is_bg.ltf.db.common.interfaces.logging.ILog;
import net.is_bg.ltf.db.common.interfaces.logging.ILogFactory;



// TODO: Auto-generated Javadoc
/**
 * The Class LogFactorySystemOut.
 */
public class LogFactorySystemOut implements ILogFactory {
	
	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.interfaces.logging.ILogFactory#getLog(java.lang.Class)
	 */
	public ILog  getLog(Class<?> c){
		return new LogSystemOut();
	}
	
}
