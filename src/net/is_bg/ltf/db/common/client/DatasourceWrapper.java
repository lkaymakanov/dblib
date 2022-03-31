package net.is_bg.ltf.db.common.client;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.function.Function;
import java.util.logging.Logger;

import javax.sql.DataSource;

class DatasourceWrapper implements DataSource {
	private DataSource ds;
	private static volatile IConnectionRegister reg = new ConnReg();
	
	DatasourceWrapper(DataSource ds){
		this.ds = ds;
	}
	
	DatasourceWrapper(DataSource ds, IConnectionRegister reg){
		this.ds = ds;
		DatasourceWrapper.reg = reg;
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return ds.getLogWriter();
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return ds.getLoginTimeout();
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return ds.getParentLogger();
	}

	@Override
	public void setLogWriter(PrintWriter arg0) throws SQLException {
		ds.setLogWriter(arg0);
	}

	@Override
	public void setLoginTimeout(int arg0) throws SQLException {
		ds.setLoginTimeout(arg0);
	}

	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		return ds.isWrapperFor(arg0);
	}

	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		return ds.unwrap(arg0);
	}

	@Override
	public Connection getConnection() throws SQLException {
		ConnectionWrapperP c = ConnectionWrapperP.wrap(ds.getConnection());
		addCallBacks(c);
		reg.add(c);
		return c;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		ConnectionWrapperP c =  ConnectionWrapperP.wrap(ds.getConnection(username, password));
		addCallBacks(c);
		reg.add(c);
		return c;
	}
	
	private static void addCallBacks(final ConnectionWrapperP c ) {
		Function<Connection, Void> f = new Function<Connection, Void>() {
				@Override
				public Void apply(Connection t) {
					 reg.release(c.hashCode());
					 return null;
				}
			};
		c.beforeClose = f;
		c.beforeCommit = f;
		c.beforeRollBack = f;
	}

}
