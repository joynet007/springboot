var userinfo = {};




userinfo.formatter_msex = function(val , rowObj){
    if(val == baseutil.msex_male){
        return '男';
    }else if(val == baseutil.msex_female){
        return "女";
    }
};


userinfo.add = function(){
    $("body").append($("<div id='userinfo_win_add'></div>"));
    var url = systemNamePath+"/userinfo/add";
    $("#userinfo_win_add").dialog({
        href:url,
        width:400,
        height:300,
        modal:true,
        title:'新用户',
        onClose : function() {
            $(this).dialog('destroy');
        }
    });


};

userinfo.edit = function (){};

userinfo.del = function(){
    var row = $('#userinfolist').datagrid('getSelected');
    if(row == null){
        $.messager.alert('提示','请选取一行数据');
    }else{
        var usertel = row.usertel;
        /**
         * 下面的ajax的操作不是异步而是同步操作
         * 即等到删除完了之后 datagrid 页面才会reload
         */
        $.ajax({url:systemNamePath+'/userinfo/del/'+usertel,async:false});
        $('#userinfolist').datagrid('reload');
    }

};

userinfo.reload = function (){
    $('#userinfolist').datagrid('reload');
};




/**
 * 定义上面的工具条
 *
 */
userinfo.toolbar=[{
    text:'新增',
    iconCls:'icon-add',
    handler:userinfo.add
},{
    text:'编辑',
    iconCls:'icon-edit',
    handler:function(){
        userinfo.edit();
    }
},'-',{
    text:'删除',
    iconCls:'icon-cancel',
    handler:function(){
        userinfo.del();
    }
},'-',{
    text:'刷新',
    iconCls:'icon-reload',
    handler:function(){
        userinfo.reload();
    }
}];