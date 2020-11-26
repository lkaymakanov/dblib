package net.is_bg.ltf.db.common;

import java.sql.CallableStatement;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * The Class DropObjectStoredProcedure.
 */
public class DropObjectStoredProcedure extends StoredProcedure{
	
	/**
	 * The Enum OBJ_TYPE.
	 */
	public enum OBJ_TYPE{
		
		/** The table. */
		TABLE("TABLE"),
		
		/** The procedure. */
		PROCEDURE("PROCEDURE"),
		
		/** The function. */
		FUNCTION("FUNCTION"),
		
		/** The trigger. */
		TRIGGER("TRIGGER"),
		
		/** The view. */
		VIEW("VIEW"),
		
		/** The sequence. */
		SEQUENCE("SEQUENCE");
	
		/** The type. */
		private String type = "";
		
		/**
		 * Instantiates a new obj type.
		 *
		 * @param type the type
		 */
		OBJ_TYPE(String type){
			this.type = type;
		}
		
		/**
		 * Gets the type.
		 *
		 * @return the type
		 */
		public String getType() {
			return type;
		}
	}
	
	/** The objname. */
	private String objname = "";
	
	/** The type. */
	private OBJ_TYPE type = OBJ_TYPE.VIEW;

	/**
	 * Instantiates a new drop object stored procedure.
	 *
	 * @param ojName the oj name
	 * @param type the type
	 */
	public DropObjectStoredProcedure(String ojName, OBJ_TYPE type){
		this.type = type;
		this.objname = ojName;
	}
	
	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.StoredProcedure#getProcedureName()
	 */
	@Override
	protected String getProcedureName() {
		// TODO Auto-generated method stub
		return "{call DELOBJECT(?,?)}";
	}

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.StoredProcedure#setParameters(java.sql.CallableStatement)
	 */
	@Override
	protected void setParameters(CallableStatement callableStatement)
			throws SQLException {
		// TODO Auto-generated method stub
		callableStatement.setString(1,  objname);
		callableStatement.setString(2, type.getType());
		
	}
	
	@Override
	public boolean isStoredProcedure() {
		return false;
	}
	
	@Override
	public boolean isUpdate() {
		return true;
	}
	
	

}
