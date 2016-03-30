package net.is_bg.ltf.db.common;

// TODO: Auto-generated Javadoc
/**
 * The Class JDBCException.
 */
public class JDBCException extends RuntimeException {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6347859037514925869L;

	/**
	 * Instantiates a new jDBC exception.
	 */
	public JDBCException() {
		super();
	}

	/**
	 * Instantiates a new jDBC exception.
	 *
	 * @param message the message
	 */
	public JDBCException(String message) {
		super(message);
	}
	
	/**
	 * Instantiates a new jDBC exception.
	 *
	 * @param cause the cause
	 */
	public JDBCException(Throwable cause) {
		super(cause != null ? cause.toString() : null);
	}

	/**
	 * Instantiates a new jDBC exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public JDBCException(String message, Throwable cause) {
		super(message, cause);
	}


}
