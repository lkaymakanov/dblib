package net.is_bg.ltf.db.common.client;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;

/***
 * Used to convert sql paramater to a String
 * Used in converting prepared statement paramaters to their String representation....
 * @author lkaymakanov
 *
 */
public class SqlTypeConverter {

	public static String toString(Object o) {
		if(o == null) return null;
		if(o instanceof Timestamp) return  ((Timestamp)o).getTime() + "";
		if(o instanceof Time) return  ((Time)o).getTime() + "";
		if(o instanceof Date) return ((Date)o).getTime() +"";
		return o.toString();
	}
	
	static Timestamp longToTimeStamp(long l){
		return new java.sql.Timestamp(l);
	}
	
	static int getSqlType(Object o) {
		if(o==null) return Types.JAVA_OBJECT;
		if( o instanceof Long ) return Types.BIGINT;
		if( o instanceof String ) return Types.VARCHAR;
		if( o instanceof Integer ) return Types.INTEGER;
		if( o instanceof BigDecimal ) return Types.NUMERIC;
		if( o instanceof Short ) return Types.SMALLINT;
		if( o instanceof Timestamp ) return Types.TIMESTAMP;
		if( o instanceof Time ) return Types.TIME;
		if( o instanceof Date ) return Types.DATE;
		if( o instanceof Boolean ) return Types.BOOLEAN;
		return Types.JAVA_OBJECT;
	}
	
	static Time toTime(long l){
		return new java.sql.Time(l);
	}
	
	static Date toDate(long l){
		return new java.sql.Date(l);
	}
	
	/*Object fromString(String s, int sqlype) {
		switch (sqlype) {
		case Types.TIME:
			
			break;

		default:
			break;
		}
		return null;
	}*/
}
