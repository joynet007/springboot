/**
 * 基础常用的方法
 * male : 男
 * female : 女
 */
baseutil = {
    mstatus_normal:'mstatus.normal',mstatus_stop:'mstatus.stop',
    msex_male:'msex.male',msex_female:'msex.female',
	mess_succ:'mess.succ',mess_failed:'mess.failed'
};



/**
 * 基础状态的判断
 * val 是值
 */

baseutil.formatter_status = function(val , rowObj){
	if(val == baseutil.mstatus_normal){
		return '启用';
	}else if(val == baseutil.mstatus_stop){
		return "停用";
	}else {
		return val;
	}
};

baseutil.formatter_time =function(val , rowObj){
	// if(val>10000){
	// 	return val+"_000";
	// }
	// return "000";
    var newTime = new Date(val);
    return newTime.toLocaleString();
}



/***
 * 去掉前后的空格
 */

baseutil.trim = function(str) {
	return str.replace(/^\s+|\s+$/g, "");
};

/****
 * 验证是数字和字母
 * @param str
 */
baseutil.checkzimu_shuzi = function (str) {
	
	 var pat = new RegExp("^[A-Za-z0-9]+$");
	 return pat.test(str);
};

baseutil.toJSON = function (data){
	 var obj = eval('(' + data + ')');
	 return obj;
}


function isNumber(oNum)
{
	if(!oNum) return false;
	var strP=/^\d+(\.\d+)?$/;
	if(!strP.test(oNum)) return false; 
	try{ 
		if(parseFloat(oNum)!=oNum) return false; 
	} 
	catch(ex) 
	{ 
		return false; 
	} 
		return true; 
};
