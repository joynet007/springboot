/**
 * 专门处理日期类型的类
 */
var date_util ={  
		
};


//2014-03-05
date_util.getYear=function(s_date){return s_date.substring(0,4);};
date_util.getMonth=function(s_date){return s_date.substring(5,7);};
date_util.getDay=function(s_date){return s_date.substring(8);};


/**
如果生日不为空的时候进行格式校验，校验的规则是，
年份：年份必须是数字，并且不能不能低于1900
月粉：月份必须是数字，并且不能小于1,不能大于2
日期：日期必须是数字，并且不能小于1，不能大于31
 **/
date_util.isYear=function(s_year){
	/**
	 *1、长度只能是4位
	 *2、如果是整数并且大于1900 
	 * **/
	if(s_year.length != 4){return false;}
	if(!date_util.isNum(s_year)){ return false;}
	if(s_year < 1900 ){return false;}
	return  true;
};


date_util.isMonth = function (s_month){
	
	/**
	 *1、长度不能是大于2位
	 *2、必须是正整数
	 *3、必须在1到12之间
	 **/
	if(s_month.length > 2){	return false;}
	if(!date_util.isNum(s_month)){	return false;}
	if(s_month<=0||s_month>12){	return false;}
	return true;
};

date_util.isDay = function (s_day){
	/**
	 *1、长度不能是大于2位
	 *2、必须是正整数
	 *3、必须在1到31之间
	 **/
	if(s_day.length > 2){return false;}
	if(!date_util.isNum(s_day)){return false;}
	if(s_day<=0||s_day>31){return false;}
	return true;
};


/**
 * 校验是否是正整数
 */
date_util.isNum=function(pvalue){
	var strRef = "1234567890";
	 for (var i=0;i<pvalue.length;i++)
	 {
	  		tempChar= pvalue.substring(i,i+1);
	  		if (strRef.indexOf(tempChar,0)==-1) 
	  		{
	   				return false; 
	  		};
	 }
	 return true;
};



date_util.init=function(){
	$.messager.alert("提示","init");
};