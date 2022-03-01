package net.is_bg.ltf.db.common.client;

import java.io.Serializable;

class CustomParam implements Serializable, ICustomParam {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8102771290195792193L;
	private int sqlType;
	private int position;
	private Object value;
	private String paramName;
	private ParamType inOutParam = ParamType.IN;  //1 in param, 2 - out param 3 - inout param
	
	public CustomParam() {
		
	}
	
	CustomParam(int postion, int sqlType, Object value, String name) {
		this.position = postion;
		this.sqlType = sqlType;
		this.value = value;
		this.paramName = name;
	}
	
	CustomParam(int postion, int type, Object value, String name, ParamType inOutparam) {
		this(postion, type, value, name);
		paramName =  paramName == null ? "param" + postion : paramName;
		this.inOutParam = inOutparam;
	}
	
	
	public final boolean isOutParam() {
		return (inOutParam.getValue() & 2)!=0;
	}
	
	@Override
	public int getParamTypeInt() {
		return inOutParam.getValue();
	}
	
	public final boolean isInOutParam() {
		return ((inOutParam.getValue() & 1)!=0) && isOutParam();
	}
	
	@Override
	public String getName() {
		return paramName;
	}

	@Override
	public ParamType getParamType() {
		return inOutParam;
	}

	@Override
	public int getSqlType() {
		return sqlType;
	}

	@Override
	public int getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public Object getValue() {
		return value;
	}

	
	/*public static void main(String [] test) {
		CustomParam p;
		p = getInOutParam(1, Types.BIT, 1, "bit param");
		p = getInParam(1, Types.BIT, 1, "bit param");
		p = getOutParam(1, Types.BIT, 1, "bit param");
		System.out.println(p);
	}*/
	
}