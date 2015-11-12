package net.is_bg.ltf.db.common;

public class JDBCException extends RuntimeException {
	private static final long serialVersionUID = -6347859037514925869L;

	public JDBCException() {
		super();
	}

	public JDBCException(String message) {
		super(message);
	}
	
	public JDBCException(Throwable cause) {
		super(cause != null ? cause.toString() : null);
	}

	public JDBCException(String message, Throwable cause) {
		super(message, cause);
	}


}
