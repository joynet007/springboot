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
 * ===============================题目所属科目==================================================
 * 用户触发了科目选择框，如果选择了科目，则 章节 要跟着联动，如果选择为空则 章节、段落都初始为空。
 *
 */
choicequestion.cc1=function(rec){
    var url = systemNamePath+'/web/subject/findSubjectBypid/'+rec.subjectid;
    $('#cc2').combobox('reload', url);
};

// choicequestion.sublevel_two=function(rec){
//     var url = systemNamePath+'/subject/findSubjectBypid/'+rec.subjectid;
//     if(rec.subjectid == '' ){
//         $('#sublevel_three').combobox('clear');
//         $('#sublevel_three').combobox('setValues', ['','--请选择--']);
//     }else{
//         $('#sublevel_three').combobox('clear');
//         $('#sublevel_three').combobox('reload', url);
//     }
// };




// choicequestion.del = function(subid,parentid){
//     var url = systemNamePath+'/choicequestion/delete/'+subid;
//     jQuery.messager.confirm('提示:','确定删除此节点信息么?',function(event) {
//         if (event) {
//             $.ajax({
//                 url: url,
//                 success: function (data) {
//                     alert(data+"--data--");
//                     var obj = baseutil.toJSON(data);
//                     alert(obj.code+"---aaa---");
//                     if (obj.code == baseutil.mess_succ) {
//                         $('#choicequestion-tree').datagrid('load');
//                         var url = systemNamePath + '/choicequestion/choicequestion-view/' + parentid;
//                         $('#choicequestion-tree-panel').panel('refresh', url);
//                     } else {
//                         $.messager.alert('提示', obj.mdesc);
//                     }
//                 }
//             });
//         } else {
//             //nothing
//         }
//     });
// };

//
// choicequestion.reload = function (){
//     $('#choicequestionlist').datagrid('reload');
// };




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
