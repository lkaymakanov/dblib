package net.is_bg.ltf.db.common.client;

/***
 * Defines all the properties necessary for remote Connection to be established...
 * plus Connection operations on the remote connection....
 * @author lkaymakanov
 *
 */
public interface ICustomDSProperties {
	
	public String getKeyStoreFile();
	public String getKeyStorePass();
	public String getKeyStoreAlias();
	public String getKeyPass();
	public String getEndPoint();
	public long getMunicipalityId();
	public String getDsName();
	public String getUrl();
	public String getUserName();
	public IConnectionOperation getConop();
}
