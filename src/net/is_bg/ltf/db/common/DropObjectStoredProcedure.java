package net.is_bg.ltf.db.common;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class DropObjectStoredProcedure extends StoredProcedure{
	
	public enum OBJ_TYPE{
		
		TABLE("TABLE"),
		PROCEDURE("PROCEDURE"),
		FUNCTION("FUNCTION"),
		TRIGGER("TRIGGER"),
		VIEW("VIEW"),
		SEQUENCE("SEQUENCE");
	
		private String type = "";
		OBJ_TYPE(String type){
			this.type = type;
		}
		
		public String getType() {
			return type;
		}
	}
	
	private String objname = "";
	private OBJ_TYPE type = OBJ_TYPE.VIEW;

	public DropObjectStoredProcedure(String ojName, OBJ_TYPE type){
		this.type = type;
		this.objname = ojName;
	}
	
	@Override
	protected String getProcedureName() {
		// TODO Auto-generated method stub
		return "{call DELOBJECT(?,?)}";
	}

	@Override
	protected void setParameters(CallableStatement callableStatement)
			throws SQLException {
		// TODO Auto-generated method stub
		callableStatement.setString(1,  objname);
		callableStatement.setString(2, type.getType());
		
	}

}
