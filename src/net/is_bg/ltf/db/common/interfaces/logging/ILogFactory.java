package net.is_bg.ltf.db.common.interfaces.logging;


// TODO: Auto-generated Javadoc
/**
 * A factory for creating ILog objects.
 */
public interface ILogFactory {

	/**
	 * Gets the log.
	 *
	 * @param c the c
	 * @return the log
	 */
	ILog getLog(Class<?> c);
}
