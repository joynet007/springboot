/**
 * 初始化树的 onclick 事件
 */
$(document).ready(function (){
	$('#sys-tree').tree({onClick:function(node){
		
		var isleaf = $('#sys-tree').tree('isLeaf', node.target);
		if(isleaf){
			sys.addTabs(node.text,node.id);
		}
	}});
	
	//easyUI datebox 的默认格式 日/月/年 通过此方法修改为年-月-日
	sys.do_datebox();
	
	
});
//end ready


/**
 * 创建一个mian 对象用于操作 main 文件夹下的所有页面的事件
 */
var sys =new Object();

/**
 * easyUI datebox 的默认格式 日/月/年 通过此方法修改为年-月-日
 */
sys.do_datebox = function(){
	
	$.fn.datebox.defaults.formatter = function(date){ 
        var y = date.getFullYear(); 
        var m = date.getMonth()+1; 
        var d = date.getDate(); 
        return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d); 
    }; 

    $.fn.datebox.defaults.parser = function(s){ 
        if (!s) return new Date(); 
        var ss = s.split('-'); 
        var y = parseInt(ss[0],10); 
        var m = parseInt(ss[1],10); 
        var d = parseInt(ss[2],10); 
        if (!isNaN(y) && !isNaN(m) && !isNaN(d)){ 
            return new Date(y,m-1,d); 
        } else { 
            return new Date(); 
        } 
    };
};


/**
 * 
 */
sys.addTabs=function(title,id){
		/**
		 * 权限模块
		 */
		if("sysuserinfo" == id){
			var url = systemNamePath+"/userinfo/startpage";
			sys.createTab(title,url);
		}else if("subject" == id){
            var url = systemNamePath+"/subject/startpage";
			sys.createTab(title,url);
		}else if("choicequestion" == id){
            var url = systemNamePath+"/choicequestion/startpage";
            sys.createTab(title,url);
        }else if("hisquestion" == id){
            var url = systemNamePath+"/hisquestion/startpage";
            sys.createTab(title,url);
        }




		else if("daywork" == id){
            var url = systemNamePath+"/user/userlist.html";
			sys.createTab(title,url);
		}
		//系统安全退出
		else if("dologout" == id){
			
			var url = systemNamePath+"/rest/sys/dologin";
			window.location.href=url;
		}
		
};
/**
 * 当前的tab 是否存在如果存在就 自动选择 否则 创建新的tab
 */
sys.createTab =function(title,url){
	/**当前的tab 是否存在如果存在就 自动选择 否则 创建新的tab**/
	if ($('#sys-main-tab').tabs('exists', title)){  
        $('#sys-main-tab').tabs('select', title);  
    } else {
    	/**添加一个tab标签**/
		$("#sys-main-tab").tabs('add',{
			title: title,
			selected: true,
			closable:true
		});
    }
	sys.loadContext(title,url);
};



sys.loadContext = function(title,url){
	/**
	 * 这里有两种获得tabpanel的方式
	 * 一种是通过获得当前选择的tablpanle
	 * 另一种是 通过title 或者 index 方式获得tabpanel
	 */
//	var tab = $('#sys-main-tab').tabs('getSelected');  // get selected panel
	var tab = $('#sys-main-tab').tabs('getTab',title); //pass title or index get panel
	tab.panel('refresh', url);
};


sys.test=function(){
	alert("test");
};
