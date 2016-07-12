package net.is_bg.ltf.db.common;

import net.is_bg.ltf.db.common.interfaces.IConnectionFactory;
import net.is_bg.ltf.db.common.interfaces.IConnectionFactoryX;
import net.is_bg.ltf.db.common.interfaces.ITransactionListener;
import net.is_bg.ltf.db.common.interfaces.logging.ILogFactory;
import net.is_bg.ltf.db.common.interfaces.timer.IElaplsedTimerFactory;
import net.is_bg.ltf.db.common.interfaces.visit.IVisitFactory;



// TODO: Auto-generated Javadoc
/**
 * Config database dependencies.
 * @author lubo
 *
 */
public class DBConfig {
	
	/** The log factory. */
	private static  ILogFactory logFactory;
	
	/** The visit factory. */
	private static  IVisitFactory  visitFactory; 
	
	/** The c factory. */
	private static  IConnectionFactoryX cFactory;
	
	/** The timer factory. */
	private static  IElaplsedTimerFactory timerFactory;
	
	/***
	 * A transaction listener!!!
	 */
	private static  ITransactionListener transactionListener;
	
	/**
	 * Initialize log factory, visit factory, connection factory & ElapsedTimerFactory. Call this method first before calling any other method in AppInitListener.
	 *
	 * @param logFactory the log factory
	 * @param visitFactory the visit factory
	 * @param cFactory the c factory
	 * @param elapsedTimerFactory the elapsed timer factory
	 */
	public static void initDBConfig(ILogFactory logFactory, IVisitFactory visitFactory, IConnectionFactoryX cFactory, IElaplsedTimerFactory elapsedTimerFactory,
			ITransactionListener transactionListener){
		DBConfig.logFactory = logFactory ;
		DBConfig.visitFactory = visitFactory;
		DBConfig.cFactory = cFactory;
		DBConfig.timerFactory = elapsedTimerFactory;
		DBConfig.transactionListener =transactionListener;
	}

	/**
	 * Obtains connection to DataBase.
	 *
	 * @return the connection factory
	 */
	public static IConnectionFactory getConnectionFactory() {
		return cFactory;
	}

	
	/**
	 * Obtains the log.
	 *
	 * @return the db log factory
	 */
	public static ILogFactory getDbLogFactory() {
		return logFactory;
	}

	/**
	 * *
	 * Obtains the Visit stuff needed for log.
	 *
	 * @return the visit factory
	 */
	public static IVisitFactory getVisitFactory() {
		return visitFactory;
	}

	/**
	 * Obtains the ElapsedTimer.
	 *
	 * @return the timer factory
	 */
	public static IElaplsedTimerFactory getTimerFactory() {
		return timerFactory;
	}

	public static ITransactionListener getTransactionListener() {
		return transactionListener;
	}
	
	
}
