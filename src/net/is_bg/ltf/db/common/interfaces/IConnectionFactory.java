package net.is_bg.ltf.db.common.interfaces;

import java.sql.Connection;

public interface IConnectionFactory {

	public abstract Connection getConnection();

}