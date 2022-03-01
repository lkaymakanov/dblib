package net.is_bg.ltf.db.common.client;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;


class CustomDataSource implements DataSource {
	private ICustomDSProperties prop;

	CustomDataSource(ICustomDSProperties prop) {
	   this.prop = prop;
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	@Override
	public Connection getConnection() throws SQLException {
		return new CustomConnection(prop);
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		return new CustomConnection(prop);
	}
	
	
}
