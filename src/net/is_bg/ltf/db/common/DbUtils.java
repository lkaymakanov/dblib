package net.is_bg.ltf.db.common;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class DbUtils {

	public static boolean IntToBool(Integer in) {
		return (in != 0) ? true : false;
	}

	public static int BoolToInt(boolean boo) {
		return (boo) ? 1 : 0;
	}

	
	
	public static String RoundFloat(Float f) {
		// За закръгляне на float до два символа след десетичната запетая
		DecimalFormat df = new DecimalFormat("0.00");
		try {
			return df.format(f).replace(",", ".");
		} catch (Exception e) {
			//LOG.warn(e.getMessage());
			return "";
		}
	}
	
	public static  String RoundDouble(Double f) {
		// За закръгляне на float до два символа след десетичната запетая
		DecimalFormat df = new DecimalFormat("#.##");
		try {
			return df.format(f).replace(",", ".");
		} catch (Exception e) {
			//LOG.warn(e.getMessage());
			return "";
		}
	}
	
	public static String RoundBigDecimal(BigDecimal bd) {
		// За закръгляне на float до два символа след десетичната запетая
		if (bd == null)
			return "";
		DecimalFormat df = new DecimalFormat("#.##");
		double doublePayment = bd.doubleValue();
		String s = df.format(doublePayment).replace(",", ".");
		return s;
	}
	
	
	
	public static BigDecimal RoundBigDecimal2(BigDecimal bd) {
		return bd.setScale(2);
	}
}
