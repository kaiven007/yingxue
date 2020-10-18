<%@ page language="java"  pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $(function () {
        //点击搜索按钮执行
        $("#searchBtn").click(function(){

            //清楚表单内容
            $("#video-tt").empty();

            //1.获取输入框的内容
            var content = $('#content').val();
            //清楚输入框内容
            $("#content").val("");

        // 初始化视频数据表格
        $('#video-tt').jqGrid({
            url: '${path}/video/videoSearch?content='+content,
            datatype: 'json',
            colNames: ['id', '标题','简介','封面','视频','发布时间'],
            styleUI: 'Bootstrap',
            colModel: [
                {'name': 'id', editable: false,search: false,align : "center"},
                {'name': 'title', editable: true,align : "center"},
                {'name': 'intro', editable: true,search: false,align : "center"},
                {'name': 'coverUrl',edittype: "file",
                    editable: false,
                    index: 'name asc, invdate',
                    width: 100,
                    align: "center",
                    //参数：各子的值,操作,行对象
                    formatter: function (cellvalue, options, rowObject) {
                        return "<img src='" + cellvalue + "' width='64px' height='64px'>"
                    }
                },
                {'name': 'videoUrl',edittype: "file",
                    editable: true,
                    index: 'name asc, invdate',
                    width: 100,
                    align: "center",
                    //参数：各子的值,操作,行对象
                    formatter: function (cellvalue, options, rowObject) {
                        return "<video controls='controls' <source src='" + cellvalue + "' width='100px' height='80px'>"
                    }
                },
                {'name': 'createAt', align: "center",
                    label: "创建时间", editable: false,edittype: 'date', formatter: "date",
                    formatoptions: {srcformat: 'Y-m-d', newformat: 'Y-m-d'}
                },
            ],
            autowidth: true,
            mtype: 'get',
            pager: '#video-pager',
           // rowNum:2,
            //rowList: [2, 5, 10,15],
            viewrecords: true,
           // editurl: '${path}/video/edit',
            cellurl: '',
            //multiselect: true,
            height: '300px',
            width: '500px',
            rownumbers: true,
        }).navGrid (


        );
            {

            }
    })
    });
    //更新索引
    $('#es').click(function () {
        alert('是否更新？');
        $.ajax({
            url:'${path}/video/addVideoEs',
            type:"post",
            success:function (re) {
            }
        });
    })
</script>
<div class="panel panel-info">

    <div class="panel panel-heading">
        <h2>视频搜索</h2>
    </div>
    <div class="panel-body" align="center">
            <div class="input-group" style="width: 300px">
                <input id="content" type="text" class="form-control" placeholder="请输入标题或描述" aria-describedby="basic-addon2">
            <span class="input-group-btn" id="basic-addon1">
               <button type="submit" id="searchBtn" class="btn btn-success">搜索</button>
            </span>
            <span class="input-group-btn" id="basic-addon2">
               <button   class="btn btn-success">
                <a id="es" href="javascript:void(0)"  data-toggle="modal">更新索引</a>
               </button>
            </span>
            </div>
    </div>
    <table id="video-tt"></table>


</div>
