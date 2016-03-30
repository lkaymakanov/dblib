package net.is_bg.ltf.db.common;
import java.util.List;
// TODO: Auto-generated Javadoc
/**
 * Database connection properties URL, driver, user & pass.
 * @author lubo
 *
 */
public class ConnectionProperties {

	/** The driver. */
	String driver;
	
	/** The url. */
	String url;
	
	/** The user. */
	String user;
	
	/** The pass. */
	String pass;
	
	/** The name_to_display. */
	String name_to_display = "";
	
	/**
	 * Instantiates a new connection properties.
	 */
	public ConnectionProperties() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Instantiates a new connection properties.
	 *
	 * @param args the args
	 */
	public ConnectionProperties(List<String> args){
		if(args == null || args.size() < 5)
			return;
		driver = args.get(0);
		url = args.get(1);
		user = args.get(2);
		pass = args.get(3);
		name_to_display = args.get(4);
	}
	
	/**
	 * Instantiates a new connection properties.
	 *
	 * @param dr the dr
	 * @param url the url
	 * @param usr the usr
	 * @param ps the ps
	 * @param name the name
	 */
	public ConnectionProperties(String dr, String url, String usr, String ps, String name) {
		// TODO Auto-generated constructor stub
		driver = dr;
		this.url = url;
		this.user = usr;
		this.pass = ps;
		name_to_display = name;
	}

	/**
	 * Gets the driver.
	 *
	 * @return the driver
	 */
	public String getDriver() {
		return driver;
	}

	/**
	 * Sets the driver.
	 *
	 * @param driver the new driver
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Gets the pass.
	 *
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * Sets the pass.
	 *
	 * @param pass the new pass
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * Gets the name_to_display.
	 *
	 * @return the name_to_display
	 */
	public String getName_to_display() {
		return name_to_display;
	}

	/**
	 * Sets the name_to_display.
	 *
	 * @param name_to_display the new name_to_display
	 */
	public void setName_to_display(String name_to_display) {
		this.name_to_display = name_to_display;
	}	
}
