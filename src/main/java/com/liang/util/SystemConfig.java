package com.liang.util;

/**
 * @author: joynet007@163.com
 * @date 2015-1-10 下午8:18:24
 * @Description: 初始系统约定的参数，例如jpa 固话对象名称等
 */
public class SystemConfig {

	public static String mstatus_normal="status.normal";
	public static String mstatus_stop="status.stop";

	public static String mess_succ="mess.succ";
	public static String mess_failed="mess.failed";

	public static final String root = "root";
	public static final String rootid = "rootid";

	public static final String sex_male = "sex.male"; //男人
	public static final String sex_female = "sex.female";//女人


	public static final String mistake_yes = "mistake.yes"; //错题
	public static final String mistake_no = "mistake.no";//学会的题目

	/**
     * 用户sessionkey
	 */
	public static String Session_key = "sessionKey";
	/**
	 * 采用单例模式，调用系统配置路径资源
	 */
	public  static SystemConfig instance = null;
	public static synchronized SystemConfig getInstance(){
		if(null == instance){
			return new SystemConfig();
		}
		return instance;
	}
	

	
	/**
	 * 从文件中读取
	 */
//	private String persistenceUnitName;
//	public  String getPersistenceUnitName() {
//		return persistenceUnitName;
//	}
//	public  void setPersistenceUnitName(String persistenceUnitName) {
//		this.persistenceUnitName = persistenceUnitName;
//	}
	
	
}
