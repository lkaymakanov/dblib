package net.is_bg.ltf.db.common;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;

// TODO: Auto-generated Javadoc
/**
 * The Class DbUtils.
 */
public class DbUtils {

	/**
	 * Int to bool.
	 *
	 * @param in the in
	 * @return true, if successful
	 */
	public static boolean IntToBool(Integer in) {
		return (in != 0) ? true : false;
	}

	/**
	 * Bool to int.
	 *
	 * @param boo the boo
	 * @return the int
	 */
	public static int BoolToInt(boolean boo) {
		return (boo) ? 1 : 0;
	}

	
	
	/**
	 * Round float.
	 *
	 * @param f the f
	 * @return the string
	 */
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
	
	/**
	 * Round double.
	 *
	 * @param f the f
	 * @return the string
	 */
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
	
	/**
	 * Round big decimal.
	 *
	 * @param bd the bd
	 * @return the string
	 */
	public static String RoundBigDecimal(BigDecimal bd) {
		if (bd == null)
			return "";
		DecimalFormat df = new DecimalFormat("#.##");
		double doublePayment = bd.doubleValue();
		String s = df.format(doublePayment).replace(",", ".");
		return s;
	}
	
	
	
	/**
	 * Round big decimal2.
	 *
	 * @param bd the bd
	 * @return the big decimal
	 */
	public static BigDecimal RoundBigDecimal2(BigDecimal bd) {
		return bd.setScale(2,BigDecimal.ROUND_CEILING);
	}
	
	public static long getCommittedTransactionsCnt(){
		return DBTransaction.commitedTransactions.get();
	}
	
	public static long getRollBackedTransactionsCnt(){
		return DBTransaction.rollbackedTranasactions.get();
	}

	
	public static String exceptionToString(Exception e){
		if(e== null) return "";
		return  ( e.toString() + e.getMessage()) == null ? "" : " " +  e.getCause() + getStackTrace(e);
	}
	
	public static String   getStackTrace(Exception ex){
		if(ex == null) return "";
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		return sw.toString(); 
	}
	
}
