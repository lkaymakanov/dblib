package net.is_bg.ltf.db.common;
import java.util.List;
/**
 * Database connection properties URL, driver, user & pass.
 * @author lubo
 *
 */
public class ConnectionProperties {

	String driver;
	String url;
	String user;
	String pass;
	String name_to_display = "";
	
	/**
	 * 
	 */
	public ConnectionProperties() {
		// TODO Auto-generated constructor stub
	}
	
	public ConnectionProperties(List<String> args){
		if(args == null || args.size() < 5)
			return;
		driver = args.get(0);
		url = args.get(1);
		user = args.get(2);
		pass = args.get(3);
		name_to_display = args.get(4);
	}
	
	public ConnectionProperties(String dr, String url, String usr, String ps, String name) {
		// TODO Auto-generated constructor stub
		driver = dr;
		this.url = url;
		this.user = usr;
		this.pass = ps;
		name_to_display = name;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName_to_display() {
		return name_to_display;
	}

	public void setName_to_display(String name_to_display) {
		this.name_to_display = name_to_display;
	}	
}
