package net.is_bg.ltf.db.common.client;

import java.util.List;

/***
 * Interface combining params and prepstatement addional params in one...
 * @author lkaymakanov
 *
 */
public interface IParamsSqlAdditionalData {
	public List<ICustomParam> getParams();
	public IPrepStatementAdditionalData getAdditionalData();
}

