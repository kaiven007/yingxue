<%@ page language="java"  pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $(function () {
        // 初始化用户数据表格
        $('#feedback-tt').jqGrid({
            url: '${path}/feedback/showFeedback',
            datatype: 'json',

            colNames: ['编号', '反馈标题','反馈内容','反馈时间','反馈的用户'],
            styleUI: 'Bootstrap',
            colModel: [
                {name : 'id',width : 55,editable: false,align : "center"},
                {name : 'title',editable:true,width : 80,align : "center"},
                {name : 'content',editable:true,width : 80,align : "center"},
                {
                    name: 'createAt', editable: false,align: "center",
                    label: "创建时间", edittype: 'date', formatter: "date",
                    formatoptions: {srcformat: 'Y-m-d', newformat: 'Y-m-d'}
                },
                {'name' : 'fuser.username',editable: true,search: false,
                    align : "center",
                    edittype:"select",
                    editoptions:{
                        dataUrl:'${path}/video/cate?sty=user'
                    }
                },
            ],
            autowidth: true,
            mtype: 'get',
            pager: '#feedback-pager',
            rowNum : 5,
            rowList: [5,10, 20, 30],
            viewrecords: true,
            editurl: '${path}/feedback/edit',//编辑表单提交路径
            multiselect: true,
            height: '300px',
            width: '500px',
            rownumbers: true,
            page: 1,
            surl:'',
            subgrid:true,
        }).navGrid( '#feedback-pager', {
                edit: true,
                add: true,
                del: true,
                edittext: "修改",
                addtext: "添加",
                deltext: "删除"
            },
            {
                closeAfterEdit:true
            },  //修改之后的额外操作
            {//添加之后的额外操作

            },  //添加之后的额外操作
            {}   //删除之后的额外操作
        );
    });
    //模板导出
    $('#excel1').click(function () {
        alert('是否导出模板？');
        $.ajax({
            url:'/feedback/',
            type:"post",
            success:function (re) {
            }
        });
    });
    //反馈导出
    $('#excel2').click(function () {
        alert('是否导出？');
        $.ajax({
            url:'/feedback/exportFeedback',
            type:"post",
            success:function (re) {
            }
        });
    })
</script>
<div class="panel panel-info">

    <div class="panel panel-heading">
        <h2>反馈信息</h2>
    </div>
    <form action="${path}/feedback/importFeedback" method="post" enctype="multipart/form-data">
        <div align="center">
            <input type="file" name="multipartFile"><br>
            <a id="excel1"  href="javascript:void(0)" class="btn btn-success">
                &nbsp;导出模板
            </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input class='btn btn-success' type="submit" value="导入">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a id="excel2"  href="javascript:void(0)" class="btn btn-success">
                &nbsp;导出
            </a>
        </div>
        <br>
    </form>
    <table id="feedback-tt"></table>
    <div id="feedback-pager"></div>

</div>


