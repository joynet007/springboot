/**
 * Created by liang on 2017/7/13.
 */

var hisquestion = {};


hisquestion.submitForm = function(){
    var choicequestionname=$("input[name=choicequestionname]");
    var answera=$("input[name=answera]");
    var answerb=$("input[name=answerb]");
    var answerc=$("input[name=answerc]");
    var answerd=$("input[name=answerd]");
    var realanswer=$("input[name=realanswer]");

    $('#hisquestionAddForm').form('submit',{
        url:systemNamePath+"/hisquestion/save",
        onSubmit:function(){



            if(choicequestionname.val() == null || choicequestionname.val() == '')
            {
                $.messager.alert('提示','题目描述不能为空！');
                choicequestionname.focus();
                return false;
            }

            if(answera.val() == null || answera.val() == '')
            {
                $.messager.alert('提示','请填写，答案A！');
                answera.focus();
                return false;
            }

            if(answerb.val() == null || answerb.val() == '')
            {
                $.messager.alert('提示','请填写，答案B！');
                answerb.focus();
                return false;
            }

            if(answerc.val() == null || answerc.val() == '')
            {
                $.messager.alert('提示','请填写，答案C！');
                answerc.focus();
                return false;
            }

            if(answerd.val() == null || answerd.val() == '')
            {
                $.messager.alert('提示','请填写，答案D！');
                answerd.focus();
                return false;
            }

            if($('input:radio:checked').val() == null || $('input:radio:checked').val() == '')
            {
                $.messager.alert('提示','请填写正确答案！');
                return false;
            }

//                return false;


        },
        success:function(data){
            var obj = baseutil.toJSON(data);
            if(obj.code == baseutil.mess_succ ){
                $("#hisquestion_win_add").dialog('destroy');
                $('#hisquestionlist').datagrid('reload');
            }else{
                $.messager.alert('提示',obj.mdesc);
                $("#hisquestion_win_add").dialog('destroy');
            }

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


hisquestion.reload = function(){
    $('#hisquestionlist').datagrid('reload');
};

hisquestion.add =function(){
        var subjectid = $('#subjectid').combobox('getValue');
        var subjectName = $('#subjectid').combobox('getText');
        var moniname = $('#moniname').combobox('getValue');

        if(subjectid == null || subjectid == ''){
            $.messager.alert('提示','请选择详细的科目');
            return false;
        }
        if(moniname == null || moniname == ''){
            $.messager.alert('提示','请选择模拟年份');
            return false;
        }
        hisquestion.addquestion(subjectid,subjectName,moniname);
}

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


hisquestion.edit =function (){

    var row = $('#hisquestionlist').datagrid('getSelected');
    if(row == null){
        $.messager.alert('提示','请选取一行数据');
    }else{
        var questionid = row.questionid;
        var subjectName = $('#subjectid').combobox('getText');
        var url = systemNamePath+'/hisquestion/edit/'+questionid+"/"+subjectName;

        $("body").append($("<div id='hisquestion_win_edit'></div>"));
        $("#hisquestion_win_edit").dialog({
            href:url,
            width:1000,
            height:600,
            modal:true,
            title:'编辑题目',
            onClose : function() {
                $(this).dialog('destroy');
            }
        });
    }

}




/**
 * 定义上面的工具条
 *
 */
hisquestion.toolbar=[{
    text:'新增',
    iconCls:'icon-add',
    handler:function(){
        hisquestion.add();
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
},'-',{
    text:'编辑',
    iconCls:'icon-edit',
    handler:function(){
        hisquestion.edit();
    }
}];
