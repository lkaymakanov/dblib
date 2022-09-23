package net.is_bg.ltf.db.common.client;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**Defines parameter to be passed as serialiazed string (JSON or any other form)

 */
public interface ICustomParam {

	public int getSqlType();
	public int getPosition();
	public Object getValue();
	public String getName();
	public ParamType getParamType();
	public int getParamTypeInt();

	public static class CustomParamBuilder{
		
		private int type;
		private int position;
		private Object value;
		private String paramName;
		private ParamType inOutParam = ParamType.IN;
		
		public CustomParamBuilder() {}
		
		public ICustomParam build() {
			return new CustomParam(position, type, value, paramName, inOutParam);
		}
		
		public CustomParamBuilder setType(int type) {
			this.type = type;
			return this;
		}
		
		public CustomParamBuilder setPosition(int position) {
			this.position = position;
			return this;
		}
		public CustomParamBuilder setValue(Object value) {
			this.value = value;
			return this;
		}
		public CustomParamBuilder setName(String paramName) {
			this.paramName = paramName;
			return this;
		}
		public CustomParamBuilder setInOutParam(ParamType inOutParam) {
			this.inOutParam = inOutParam;
			return this;
		}
		
	}
	
	public static Map<Integer, ICustomParam> toMap(List<ICustomParam> param){
		Map<Integer, ICustomParam> m = new Hashtable<Integer, ICustomParam>();
		for(ICustomParam p : param) {
			m.put(p.getPosition(), p);
		}
		return m;
	}

}

