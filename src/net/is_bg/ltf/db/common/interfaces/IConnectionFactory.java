package net.is_bg.ltf.db.common.interfaces;

import java.sql.Connection;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating IConnection objects.
 */
public interface IConnectionFactory {

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public abstract Connection getConnection();

}