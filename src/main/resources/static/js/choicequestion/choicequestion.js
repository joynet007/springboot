/**
 * Created by liang on 2017/7/13.
 */

var choicequestion = {};


choicequestion.add = function(id){
    $("body").append($("<div id='choicequestion_win_add'></div>"));
    var url = systemNamePath+"/choicequestion/add";
    $("#choicequestion_win_add").dialog({
        href:url,
        width:600,
        height:500,
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

choicequestion.del = function(subid,parentid){
    var url = systemNamePath+'/choicequestion/delete/'+subid;
    jQuery.messager.confirm('提示:','确定删除此节点信息么?',function(event){
        if (event) {
            $.ajax({
                url: url,
                success: function (data) {
                    if (data.code == baseutil.mess_succ) {
                        $('#choicequestion-tree').tree('reload');
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
