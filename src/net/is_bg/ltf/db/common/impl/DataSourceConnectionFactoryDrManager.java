package net.is_bg.ltf.db.common.impl;

import java.sql.Connection;
import java.sql.DriverManager;

import net.is_bg.ltf.db.common.ConnectionProperties;
import net.is_bg.ltf.db.common.JDBCException;
import net.is_bg.ltf.db.common.interfaces.IConnectionFactory;

/**
 * Creates connection out of connection properties
 * 
 *<pre>
 *
 *Usage
 *
	//drivers 
	private final static String ORCL_DRIVER = "oracle.jdbc.OracleDriver"; 
	private final static String PGR_DRIVER  = "org.postgresql.Driver"; 
	private final static String PGR_DRIVER_DIGEST = "com.is.util.db.driver.digestdriver.DigestDriver";
	
	//data bases url's
	public final static String URL_PGR_BRC = "digest:jdbc:postgresql://10.240.110.120:5432/brc";
	public final static String URL_PGR_MDT = "digest:jdbc:postgresql://10.240.44.129:5432/mdt";
	public final static String URL_ORC_SF_129 = "jdbc:oracle:thin:@10.240.44.129:1521:ORCL";
	public final static String URL_ORC_SF_146 = "jdbc:oracle:thin:@10.240.44.146:1521:orcl";
	public final static String URL_LOCAL = "jdbc:oracle:thin:@localhost:1521:ltf";
	
	public final static String URL_PGR_149 = "jdbc:postgresql://10.240.44.149:5432/bnk11";
	public final static String URL_PGR_LOCLHOST = "digestdebug:jdbc:postgresql://localhost:5432/bnk";
	
	public final static String URL_PGR_PDV_7 = "digestdebug:jdbc:postgresql://10.240.110.7:5432/pdv";
	
	//SOME PREDEFINED CONNECTIONS
	//data base connections
	public  ConnectionProperties [] dBases = {
		new ConnectionProperties(ORCL_DRIVER, URL_ORC_SF_129, "brc", "brc", "orlc_brc_129"),  
		new ConnectionProperties(ORCL_DRIVER, URL_ORC_SF_129, "krp", "krp", "orlc_krp_129"),  
		new ConnectionProperties(ORCL_DRIVER, URL_ORC_SF_146, "brc", "brc", "orlc_brc_146"),  
		new ConnectionProperties(ORCL_DRIVER, URL_ORC_SF_146, "sdk", "sdk", "orlc_sdk_146"),  
		new ConnectionProperties(PGR_DRIVER, URL_PGR_BRC, "mdt", "mdt", "pgr_brc_129"),       
		new ConnectionProperties(ORCL_DRIVER, URL_LOCAL, "brc", "brc", "pgr_brc_129") ,       
		new ConnectionProperties(ORCL_DRIVER, URL_PGR_MDT, "mdt", "mdt", "pgr_mdt_129"),      
		
		new ConnectionProperties(PGR_DRIVER_DIGEST, URL_PGR_149, "bnk", "Bnk12345", "pgr_bnk_149") , 
		new ConnectionProperties(PGR_DRIVER_DIGEST, URL_PGR_LOCLHOST, "bnk", "Bnk12345", "localhost_pgr"),  
		new ConnectionProperties(PGR_DRIVER_DIGEST, URL_PGR_PDV_7, "pdv", "pdv", "pdv10.240.110.7")  
		
	};
	
	//creates datasource factory
	new  DataSourceConnectionFactoryDrManager(dBases[0]);
	
 *
 *</pre>
 * 
 * @author lubo
 *
 */
public class DataSourceConnectionFactoryDrManager implements IConnectionFactory {
	ConnectionProperties prop;
	
	public DataSourceConnectionFactoryDrManager(ConnectionProperties pr) {
		// TODO Auto-generated constructor stub
		prop = pr;
	}
	
	
	public Connection getConnection() {
		// TODO Auto-generated method stub
		try{
			Class.forName(prop.getDriver());
			return DriverManager.getConnection(prop.getUrl(), prop.getUser(), prop.getPass());
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new JDBCException(e);
		}
	}


}
