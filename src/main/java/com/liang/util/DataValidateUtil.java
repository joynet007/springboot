package com.liang.util;
import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

/**
 * chechSafe：校验字符串是否安全，+-_、a-z、A-Z、0-9、汉字其他不允许输入
 * chechHTML:匹配HTML标记
 * checkEmail：验证Email
 * checkIdCard:验证身份证号码(非严格验证)
 * checkMobile：验证手机号码
 * checkPhone:验证固定电话号码
 * checkDigit:验证整数（正整数和负整数）
 * checkDecimals:验证整数和浮点数（正负整数和正负浮点数）
 * checkDecimal:验证金额
 * checkBlankSpace:验证空白字符，空白字符，包括：空格、\t、\n、\r、\f、\x0B
 * checkChinese：验证中文
 * checkIncludeChinese：验证是否包含中文
 * checkBirthday：验证日期（年月日）格式：1992-09-03，或1992.09.03
 * checkURL：验证URL地址
 * checkPostcode：匹配中国邮政编码
 * checkIpAddress:匹配IP地址(简单匹配，格式，如：192.168.1.1，127.0.0.1，没有匹配IP段的大小)
 * @author 
 *
 */
public class DataValidateUtil {
	public static void main(String[] args) {
		System.out.println(checkDigit("--1"));
//		System.out.println(dataValidateUtil.chechHTML("df"));
	
	}
	/**
	 * 校验字符串是否安全，
	 * +-_、a-z、A-Z、0-9、汉字其他不允许输入
	 * @param str
	 * @return
	 */
	public static boolean chechSafe(String str){
		 String regex = "[a-zA-Z0-9\u4E00-\u9FA5\\-_+]*"; 
	     return Pattern.matches(regex, str); 
	}
	/**
	 * 匹配HTML标记
	 * @param str
	 * @return
	 */
	public static boolean chechHTML(String str){
		 String regex = "<(S*?)[^>]*>.*?|<.*? />"; 
	     return Pattern.matches(regex, str); 
	}
	/**
     * 验证Email
     * @param email email地址，格式：zhangsan@sina.com，zhangsan@xxx.com.cn，xxx代表邮件服务商
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkEmail(String email) { 
        String regex = "\\w+@[\\w-]+(\\.[\\w-]+)+"; 
        return Pattern.matches(regex, email); 
    } 
     
    /**
     * 验证身份证号码
     * @param idCard 居民身份证号码15位或18位，最后一位可能是数字或字母
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkIdCard(String idCard) { 
        String regex = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}"; 
        return Pattern.matches(regex,idCard); 
    } 
     
    /**
     * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
     * @param mobile 移动、联通、电信运营商的号码段
     *<p>移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
     *、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）</p>
     *<p>联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）</p>
     *<p>电信的号段：133、153、180（未启用）、189</p>
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkMobile(String mobile) { 
        String regex = "(\\+\\d+)?1[3458]\\d{9}$"; 
        return Pattern.matches(regex,mobile); 
    } 
     
    /**
     * 验证固定电话号码
     * @param phone 电话号码，格式：国家（地区）电话代码 + 区号（城市代码） + 电话号码，如：+8602085588447
     * <p><b>国家（地区） 代码 ：</b>标识电话号码的国家（地区）的标准国家（地区）代码。它包含从 0 到 9 的一位或多位数字，
     *  数字之后是空格分隔的国家（地区）代码。</p>
     * <p><b>区号（城市代码）：</b>这可能包含一个或多个从 0 到 9 的数字，地区或城市代码放在圆括号——
     * 对不使用地区或城市代码的国家（地区），则省略该组件。</p>
     * <p><b>电话号码：</b>这包含从 0 到 9 的一个或多个数字 </p>
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkPhone(String phone) { 
        String regex = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$"; 
        return Pattern.matches(regex, phone); 
    } 
    /**
     * 验证金额
     */
    public static boolean checkDecimal(String decimals) { 
		  String regex = "(([1-9]\\d*)|0)(\\.\\d{1,2})?";
	      return Pattern.matches(regex,decimals); 
	    } 
    /**
     * 验证整数（正整数和负整数）  	
     * @param digit 一位或多位0-9之间的整数
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkDigit(String digit) { 
    	String regex = "^[-\\+]?\\d+$"; 
        return Pattern.matches(regex,digit); 
    } 
    
    /**
     * 验证正整数
     * @param digit 一位或多位0-9之间的整数
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkDigitInt(String digit) { 
    	 String patrn ="^[+]?\\d+$"; 
        return Pattern.matches(patrn,digit); 
    } 
   
    /**
     * 验证整数和浮点数（正负整数和正负浮点数）
     * @param decimals 一位或多位0-9之间的浮点数，如：1.23，233.30
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkDecimals(String decimals) { 
        String regex = "\\-?[1-9]\\d+(\\.\\d+)?"; 
        return Pattern.matches(regex,decimals); 
    }  
     
    /**
     * 验证空白字符
     * @param blankSpace 空白字符，包括：空格、\t、\n、\r、\f、\x0B
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkBlankSpace(String blankSpace) { 
        String regex = "\\s+"; 
        return Pattern.matches(regex,blankSpace); 
    } 
     
    /**
     * 验证中文
     * @param chinese 中文字符
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkChinese(String chinese) { 
    	System.out.println("原字符串："+chinese);
    	try {
			System.out.println("GBK："+new String(chinese.getBytes("UTF-8"),"GBK"));
			System.out.println("UTF-8："+new String(chinese.getBytes("ISO8859-1"),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String regex = "^[\u4E00-\u9FA5]+$"; 
        return Pattern.matches(regex,chinese); 
    } 
    /**
     * 验证是否包含中文--贾俊
     * @param chinese 中文字符
     * @return 如果包含汉字返回true，不包含汉字返回false
     */ 
    public static boolean checkIncludeChinese(String chinese) { 
    	Pattern regex =Pattern.compile("[\u4e00-\u9fa5]"); 
        return regex.matcher(chinese).find();
    } 
     
    /**
     * 验证日期（年月日）
     * @param birthday 日期，格式：1992-09-03，或1992.09.03
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkBirthday(String birthday) { 
        String regex = "\\d{4}([-./])\\d{1,2}([-./])\\d{1,2}"; 
        return Pattern.matches(regex,birthday); 
    } 
     
    /**
     * 验证URL地址
     * @param url 格式：http://blog.csdn.net:80/xyang81/article/details/7705960? 或 http://www.csdn.net:80
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkURL(String url) { 
        String regex = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?"; 
        return Pattern.matches(regex, url); 
    } 
     
    /**
     * 匹配中国邮政编码
     * @param postcode 邮政编码
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkPostcode(String postcode) { 
        String regex = "[1-9]\\d{5}"; 
        return Pattern.matches(regex, postcode); 
    } 
     
    /**
     * 匹配IP地址(简单匹配，格式，如：192.168.1.1，127.0.0.1，没有匹配IP段的大小)
     * @param ipAddress IPv4标准地址
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkIpAddress(String ipAddress) { 
        String regex = "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))"; 
        return Pattern.matches(regex, ipAddress); 
    } 
    
    /**
     * 判断是否是money格式
     * 输入金额的要求：整数位最多十位，小数为最多为两位，可以无小数位 
    * @Title: isMoney 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param money
    * @param @return    设定文件 
    * @return boolean    返回类型    
    * @throws
     */
    public static boolean isMoney(String money){
    	String regex = "^(([0-9]|([1-9][0-9]{0,9}))((\\.[0-9]{1,2})?))$";
    	return Pattern.matches(regex, money);
    	
    }
}
