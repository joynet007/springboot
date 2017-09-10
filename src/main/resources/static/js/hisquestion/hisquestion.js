/**
 * Created by liang on 2017/7/13.
 */

var hisquestion = {};

hisquestion.addquestion =function (subjectid , subjectName , moniname){

    $("body").append($("<div id='hisquestion_win_add'></div>"));
    var url = systemNamePath+"/hisquestion/add?subjectid="+subjectid+"&subjectName="+subjectName+"&moniname="+moniname;
    $("#hisquestion_win_add").dialog({
        href:url,
        width:1000,
        height:600,
        modal:true,
        title:'新增题目',
        onClose : function() {
            $(this).dialog('destroy');
        }
    });
}

hisquestion.edit = function (){

};

// $.ajax({url:systemNamePath+'/userinfo/del/'+usertel,async:false});

hisquestion.del = function(){

    var row = $('#hisquestionlist').datagrid('getSelected');
    if(row == null){
        $.messager.alert('提示','请选取一行数据');
    }else{
        var questionid = row.questionid;

        var url = systemNamePath+'/hisquestion/del/'+questionid;
        jQuery.messager.confirm('提示:','确定删除此节点信息么?',function(event){
            if (event) {
                $.ajax({
                    url: url,
                    success: function (data) {
                        if (data.code == baseutil.mess_succ) {
                            $('#hisquestionlist').datagrid('reload');
                            // $('#hisquestionlist').datagrid('load');
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
hisquestion.toolbar=[{
    text:'编辑',
    iconCls:'icon-edit',
    handler:function(){
        hisquestion.edit();
    }
},'-',{
    text:'删除',
    iconCls:'icon-cancel',
    handler:function(){
        hisquestion.del();
    }
},'-',{
    text:'刷新',
    iconCls:'icon-reload',
    handler:function(){
        hisquestion.reload();
    }
}];
