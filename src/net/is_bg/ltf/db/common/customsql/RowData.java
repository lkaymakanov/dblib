package net.is_bg.ltf.db.common.customsql;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class RowData implements Serializable, IRowData {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3687394994538790166L;
	private Object []  data;
	
	RowData(Object [] data) {
		this.data = data;
	}
	
	public List<Object> getData() {
		return  data == null ? new ArrayList<Object>() : Arrays.asList(data);
	}
}
