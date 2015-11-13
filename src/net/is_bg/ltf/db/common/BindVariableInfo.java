package net.is_bg.ltf.db.common;

public class BindVariableInfo implements IBindVariableInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3239673191392175124L;
	private Object 	value;
	private int		type;
	private int 	position;
	private boolean outputparam = false;

	public BindVariableInfo(Object value,int type,int position){
		this.value=value;
		this.type=type;
		this.position=position;
		this.outputparam = false;
	}
	
	public BindVariableInfo(Object value,int type,int position, boolean output){
		this.value=value;
		this.type=type;
		this.position=position;
		this.outputparam = output;
	}

	public Object getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	public int getType() {
		// TODO Auto-generated method stub
		return type;
	}

	public int getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	public boolean IsOutputParam() {
		// TODO Auto-generated method stub
		return outputparam;
	}	
}