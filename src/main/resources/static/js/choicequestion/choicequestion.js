/**
 * Created by liang on 2017/7/13.
 */

var choicequestion = {};


choicequestion.add = function(id){
    $("body").append($("<div id='choicequestion_win_add'></div>"));
    var url = systemNamePath+"/choicequestion/add";
    $("#choicequestion_win_add").dialog({
        href:url,
        width:1000,
        height:600,
        modal:true,
        title:'新增题目',
        onClose : function() {
            $(this).dialog('destroy');
        }
    });


};

choicequestion.edit = function (){

};

// $.ajax({url:systemNamePath+'/userinfo/del/'+usertel,async:false});

choicequestion.del = function(){

    var row = $('#choicequestionlist').datagrid('getSelected');
    if(row == null){
        $.messager.alert('提示','请选取一行数据');
    }else{
        var questionid = row.questionid;

        var url = systemNamePath+'/choicequestion/del/'+questionid;
        jQuery.messager.confirm('提示:','确定删除此节点信息么?',function(event){
            if (event) {
                $.ajax({
                    url: url,
                    success: function (data) {
                        if (data.code == baseutil.mess_succ) {
                            $('#choicequestionlist').datagrid('reload');
                            // $('#choicequestionlist').datagrid('load');
                        } else {
                            $.messager.alert('提示', obj.mdesc);
                        }
                    }
                });
            } else {
                //nothing
            }
        });
    }

};





/**
 * 定义上面的工具条
 *
 */
choicequestion.toolbar=[{
    text:'新增',
    iconCls:'icon-add',
    handler:choicequestion.add
},{
    text:'编辑',
    iconCls:'icon-edit',
    handler:function(){
        choicequestion.edit();
    }
},'-',{
    text:'删除',
    iconCls:'icon-cancel',
    handler:function(){
        choicequestion.del();
    }
},'-',{
    text:'刷新',
    iconCls:'icon-reload',
    handler:function(){
        choicequestion.reload();
    }
}];
