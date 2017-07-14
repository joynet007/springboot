package com.liang.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Pattern;

public class MoneyUtil {
	public static final BigDecimal MAX_VALUE = BigDecimal.valueOf(Long.MAX_VALUE);
	public static final BigDecimal MIN_VALUE = BigDecimal.valueOf(Long.MIN_VALUE);
	public static final long	NumEmpty	= Long.MIN_VALUE + 988;

	
	/**
	 * 带千分位的字符串<br/>
	 * 例子:12,300,014.55<br/>
	 * @param amount
	 * @param floatSize
	 * @return
	 */
	public static String getBalanceDisplay(long amount, int floatSize) {
		NumberFormat format = NumberFormat.getInstance();
		if (NumEmpty == amount) return "";
		if (floatSize < 0) { // error input
			return Long.toString(amount);
		}
		if (floatSize == 2) {
			boolean zs = true;
			if (amount < 0) {
				zs = false;
				amount = -amount;
			}
			long m = amount / 100;
			long f = amount - m * 100;
			return (zs ? "" : "-") + format.format(m) + "."
					+ (f > 9 ? Long.toString(f) : "0" + f);
		} else if (floatSize == 0) {
			return format.format(amount);
		} else { // error input
			return format.format(amount);
		}
	}
	
	
	
	
	/**
	 * 字符串金额乘以100转换为long类型
	 * 金额：可为负数，允许2位小数
	 * @param input
	 * @return
	 */
	public static long str2long(String input){
		//金额：可为负数，允许2位小数
		String regex = "(-)?(([1-9]\\d*)|0)(\\.\\d{1,2})?";
		if(!Pattern.matches(regex, input)){
			throw new RuntimeException("金额格式错误");
		}
		BigDecimal amount = new BigDecimal(input).multiply(BigDecimal.valueOf(100L));
		if(MAX_VALUE.compareTo(amount) < 0){
			throw new RuntimeException("金额值太大");
		}
		if(MIN_VALUE.compareTo(amount) > 0){
			throw new RuntimeException("金额值太小");
		}
		return amount.longValue();
	}
	
	/**
	 * 将long类型金额除以100转换为字符串
	 * @param amount
	 * @return
	 */
	public static String long2str(long amount){
		return BigDecimal.valueOf(amount).divide(BigDecimal.valueOf(100L)).toString();
	}
	
	public static String getMoneyDisplay(BigDecimal amount) {
		if (amount == null) {
			return "0.00";
		} else {
			DecimalFormat df = new DecimalFormat("##.00");
			return df.format(amount.setScale(2, BigDecimal.ROUND_DOWN));
		}
	}
	
	/**
	 * 将long类型金额除以100转换为字符串，并保留2位小数位
	 * @param amount
	 * @return
	 */
	public static String long2strWith2Dec(long amount){
		return MoneyUtil.getBalanceDisplay(amount, 2);
//		String str = MoneyUtil.long2str(amount);
//		int index = str.indexOf(".");
//		if(index == -1){
//			return str + ".00";
//		}else if(index == str.length()-2){
//			return str + "0";
//		}else{
//			return str;
//		}
	}
	
	public static void main(String[] args){
		try {
			System.out.println(MoneyUtil.str2long("123.358"));
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(MoneyUtil.long2strWith2Dec(1234000));
	}
}
