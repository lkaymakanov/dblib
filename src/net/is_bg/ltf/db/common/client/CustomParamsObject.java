package net.is_bg.ltf.db.common.client;

import java.util.ArrayList;
import java.util.List;

class CustomParamsObject {
	public CustomParamsObject() {
	}
	List<ICustomParam> params = new ArrayList<>();
	public List<ICustomParam> getParams() {
		return params;
	}
	public void setParams(List<ICustomParam> params) {
		this.params = params;
	}
}
