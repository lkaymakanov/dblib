package net.is_bg.ltf.db.common.client;

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
