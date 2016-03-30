package net.is_bg.ltf.db.common;

// TODO: Auto-generated Javadoc
/**
 * The Class BindVariableInfo.
 */
public class BindVariableInfo {
	
	/** The value. */
	private Object 	value;
	
	/** The type. */
	private int		type;
	
	/** The position. */
	private int 	position;
	
	/** The outputparam. */
	private boolean outputparam = false;

	/**
	 * Instantiates a new bind variable info.
	 *
	 * @param value the value
	 * @param type the type
	 * @param position the position
	 */
	public BindVariableInfo(Object value,int type,int position){
		this.value=value;
		this.type=type;
		this.position=position;
		this.outputparam = false;
	}
	
	/**
	 * Instantiates a new bind variable info.
	 *
	 * @param value the value
	 * @param type the type
	 * @param position the position
	 * @param output the output
	 */
	public BindVariableInfo(Object value,int type,int position, boolean output){
		this.value=value;
		this.type=type;
		this.position=position;
		this.outputparam = output;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public Object getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public int getType() {
		// TODO Auto-generated method stub
		return type;
	}

	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public int getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	/**
	 * Checks if is output param.
	 *
	 * @return true, if successful
	 */
	public boolean IsOutputParam() {
		// TODO Auto-generated method stub
		return outputparam;
	}	
}