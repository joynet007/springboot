
$(document).ready(function (){
    $('#subject-tree').tree({onClick:function(node){
        var id = node.id;
        var url = systemNamePath+'/subject/subject-view/'+id;
        $('#subject-tree-panel').panel('refresh', url);
    }});


});

var subjecttree = {};/**
 * Created by liang on 2017/7/12.
 */
