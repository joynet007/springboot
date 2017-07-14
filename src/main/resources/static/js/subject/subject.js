var subject = {};


subject.add = function(id){
    $("body").append($("<div id='subject_win_add'></div>"));
    var url = systemNamePath+"/subject/add/"+id;
    $("#subject_win_add").dialog({
        href:url,
        width:400,
        height:300,
        modal:true,
        title:'新科目',
        onClose : function() {
            $(this).dialog('destroy');
        }
    });


};

subject.edit = function (){

};

// $.ajax({url:systemNamePath+'/userinfo/del/'+usertel,async:false});

subject.del = function(subid,parentid){
    var url = systemNamePath+'/subject/delete/'+subid;
    jQuery.messager.confirm('提示:','确定删除此节点信息么?',function(event){
        if (event) {
            $.ajax({
                url: url,
                success: function (data) {
                    if (data.code == baseutil.mess_succ) {
                        $('#subject-tree').tree('reload');
                        var url = systemNamePath + '/subject/subject-view/' + parentid;
                        $('#subject-tree-panel').panel('refresh', url);
                    } else {
                        $.messager.alert('提示', obj.mdesc);
                    }
                }
            });
        } else {
            //nothing
        }
      });
};

// subject.del = function(subid,parentid){
//     var url = systemNamePath+'/subject/delete/'+subid;
//     jQuery.messager.confirm('提示:','确定删除此节点信息么?',function(event) {
//         if (event) {
//             $.ajax({
//                 url: url,
//                 success: function (data) {
//                     alert(data+"--data--");
//                     var obj = baseutil.toJSON(data);
//                     alert(obj.code+"---aaa---");
//                     if (obj.code == baseutil.mess_succ) {
//                         $('#subject-tree').datagrid('load');
//                         var url = systemNamePath + '/subject/subject-view/' + parentid;
//                         $('#subject-tree-panel').panel('refresh', url);
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
// subject.reload = function (){
//     $('#subjectlist').datagrid('reload');
// };




/**
 * 定义上面的工具条
 *
 */
subject.toolbar=[{
    text:'新增',
    iconCls:'icon-add',
    handler:subject.add
},{
    text:'编辑',
    iconCls:'icon-edit',
    handler:function(){
        subject.edit();
    }
},'-',{
    text:'删除',
    iconCls:'icon-cancel',
    handler:function(){
        subject.del();
    }
},'-',{
    text:'刷新',
    iconCls:'icon-reload',
    handler:function(){
        subject.reload();
    }
}];