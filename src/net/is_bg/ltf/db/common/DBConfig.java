package net.is_bg.ltf.db.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

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
	private static volatile ILogFactory logFactory;
	
	/** The visit factory. */
	private static volatile IVisitFactory  visitFactory; 
	
	/** The c factory. */
	private static volatile IConnectionFactoryX cFactory;
	
	/** The timer factory. */
	private static volatile IElaplsedTimerFactory timerFactory;
	
	/***
	 * A transaction listener!!!
	 */
	private static volatile ITransactionListener transactionListener;
	
	
	static byte[] c(String data,  String encoding) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length());
		GZIPOutputStream gzip = new GZIPOutputStream(bos);
		gzip.write(data.getBytes(encoding));
		gzip.close();
		byte[] c = bos.toByteArray();
		bos.close();
		return c;
   }
	
	
	private static volatile ILogEncrypter encrypter; /*= new ILogEncrypter() {
		
		private String reverse(String s) {
			StringBuilder b = new StringBuilder();
			for(int i = s.length()-1; i  >=0; i--) {
				b.append(s.charAt(i));
			}
			return b.toString();
		}
		
		@Override
		public byte[] encrypt(byte[] b) {
			return b;
		}
		
		@Override
		public String encrypt(String encrypt) {
			if(true) return encrypt;
			try {
				return encrypt == null ? encrypt : Base64.getEncoder().encodeToString( c(encrypt, "UTF-8"));
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	};*/
	
	
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
		initDBConfig(logFactory, visitFactory, cFactory, elapsedTimerFactory, transactionListener, null);
	}
	
	public static void initDBConfig(ILogFactory logFactory, IVisitFactory visitFactory, IConnectionFactoryX cFactory, IElaplsedTimerFactory elapsedTimerFactory,
			ITransactionListener transactionListener, ILogEncrypter encrypter){
		DBConfig.logFactory = logFactory ;
		DBConfig.visitFactory = visitFactory;
		DBConfig.cFactory = cFactory;
		DBConfig.timerFactory = elapsedTimerFactory;
		DBConfig.transactionListener =transactionListener;
		if(encrypter!=null) DBConfig.encrypter = encrypter;
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
	
	public static ILogEncrypter getEncrypter() {
		return encrypter;
	}
	
}
