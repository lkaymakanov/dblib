package net.is_bg.ltf.db.common.client;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

class ConnReg  implements IConnectionRegister{
	
	private Map<Integer, ConData> conMap = new HashMap<Integer, ConData >();
    private static Map<String, IConnectionRegister> reg = new HashMap<String, IConnectionRegister>();
    private static final String defaultKey  = "b789b962-7ec2-4ffc-b37c-115be4e6f587";
    private String registerKey;
    
    private static void log(ConData d, String action ) {
    	System.out.println("Connection " + d.c + ", hash " + d.c.hashCode() + "  " +  action +   " at "  + System.currentTimeMillis() + "  registry " +  d.regKey + " by thread  " + Thread.currentThread());
    }
    
    ConnReg(){
    	if(!reg.containsKey(defaultKey)) {
    		this.registerKey = defaultKey;
    		reg.put(defaultKey, this);
    	}
    }
    
	@Override
	public void add(Connection c) {
		synchronized (conMap) {
			if(!conMap.containsKey(c.hashCode())) {
				ConData d = ConData.fromConnection(c);
				d.regKey = registerKey;
				conMap.put(c.hashCode(),d);
				log(d, " added ");
			}
		}
	}

	@Override
	public Connection get(int hashcode) {
		synchronized (conMap) {
			ConData d = conMap.get(hashcode);
			if(d!=null) {
				log(d, " retrieved  ");
				return d.c;
			}
			return null;
		}
	}

	@Override
	public void release(Connection c) {
		synchronized (conMap) {
			release(c.hashCode());
		}
	}

	@Override
	public void release(int hashCode) {
		synchronized (conMap) {
			ConData d = conMap.remove(hashCode);
			if(d!=null) {
				log(d, " released ");
			}
		}
	}
	
	
	@Override
	public void release() {
		synchronized (conMap) {
		    for(ConData d:conMap.values()) {
		    	if(d!=null)release(d.hashCode());
		    }
		}
	}
	
	@Override
	public IConnectionRegister getConnectionRegister(String dsName) {
		IConnectionRegister r;
		synchronized (this) {
			r = reg.get(dsName);
			if(r == null) r = reg.get(defaultKey); 
		}
		return r;
	}

	@Override
	public void addConnectionRegister(String dsName) {
		synchronized (this) {
			ConnReg regg = new ConnReg();
			regg.registerKey = dsName;
			reg.put(dsName,regg);
		}
	}
}


class ConData{
	int handle;
	Connection c;
	long createTime;
	String regKey;
	
	static ConData fromConnection(Connection c){
		ConData d = new ConData();
		d.c = c;
		d.handle = c.hashCode();
		d.createTime = System.currentTimeMillis();
		return d;
	}
}