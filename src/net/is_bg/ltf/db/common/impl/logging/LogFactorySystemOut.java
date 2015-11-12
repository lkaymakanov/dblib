package net.is_bg.ltf.db.common.impl.logging;

import net.is_bg.ltf.db.common.interfaces.logging.ILog;
import net.is_bg.ltf.db.common.interfaces.logging.ILogFactory;



public class LogFactorySystemOut implements ILogFactory {
	
	public ILog  getLog(Class<?> c){
		return new LogSystemOut();
	}
	
}
