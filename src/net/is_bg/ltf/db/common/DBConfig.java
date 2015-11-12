package net.is_bg.ltf.db.common;

import net.is_bg.ltf.db.common.interfaces.IConnectionFactory;
import net.is_bg.ltf.db.common.interfaces.logging.ILogFactory;
import net.is_bg.ltf.db.common.interfaces.timer.IElaplsedTimerFactory;
import net.is_bg.ltf.db.common.interfaces.visit.IVisitFactory;



/**
 * Config database dependencies.
 * @author lubo
 *
 */
public class DBConfig {
	
	private static  ILogFactory logFactory;
	private static  IVisitFactory  visitFactory; 
	private static  IConnectionFactory cFactory;
	private static  IElaplsedTimerFactory timerFactory;
	
	/**
	 * Initialize log factory, visit factory, connection factory & ElapsedTimerFactory. Call this method first before calling any other method in AppInitListener.
	 * @param logFactory
	 * @param visitFactory
	 * @param cFactory
	 */
	public static void initDBConfig(ILogFactory logFactory, IVisitFactory visitFactory, IConnectionFactory cFactory, IElaplsedTimerFactory elapsedTimerFactory){
		DBConfig.logFactory = logFactory ;
		DBConfig.visitFactory = visitFactory;
		DBConfig.cFactory = cFactory;
		DBConfig.timerFactory = elapsedTimerFactory;
	}

	/**
	 * Obtains connection to DataBase.
	 * @return
	 */
	public static IConnectionFactory getConnectionFactory() {
		return cFactory;
	}

	
	/**
	 * Obtains the log.
	 */
	public static ILogFactory getDbLogFactory() {
		return logFactory;
	}

	/***
	 * Obtains the Visit stuff needed for log.
	 */
	public static IVisitFactory getVisitFactory() {
		return visitFactory;
	}

	/**
	 * Obtains the ElapsedTimer.
	 * @return
	 */
	public static IElaplsedTimerFactory getTimerFactory() {
		return timerFactory;
	}
	
	
	
}
