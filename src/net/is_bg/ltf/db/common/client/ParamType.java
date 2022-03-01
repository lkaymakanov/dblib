package net.is_bg.ltf.db.common.client;

public enum ParamType {
	
	IN(1),
	OUT(2),
	INOUT(3);
	
	private int value;
	ParamType(int v){
		this.value = v;
	}
	
	public int getValue() {
		return value;
	}
}
