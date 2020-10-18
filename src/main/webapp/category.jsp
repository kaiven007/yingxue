<%@ page language="java"  pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script>
    $(function(){
        pageInit();
    });
    function pageInit(){
        jQuery('#category-tt').jqGrid(
            {
                url : '${path}/category/showCategory',
                styleUI:"Bootstrap",
                datatype : "json",
                colNames : [ '分类ID', '分类名称', '级别' ],
                colModel : [
                    {name : 'id',  width : 55,search: false,align : "center"},
                    {name : 'name',editable:true,width : 90,align : "center"},
                    {name : 'level',width : 100,align : "center"},
                ],
                rowNum : 8,
                rowList : [5, 8, 10, 20, 30 ],
                pager : '#category-page',
                viewrecords : true,
                multiselect :true, //选择框
                subGrid : true,
                page: 1,
                height: '300px',
                width: '1000px',
                autowidth: true,
                editurl: '${path}/category/edit',//编辑表单提交路径
                //rownumbers: true,//序列
                surl:'${path}/category/showCategory',
                //caption : "Grid as Subgrid",
                subGridRowExpanded : function(subgrid_id, row_id) {
                    var subgrid_table_id, pager_id;
                    subgrid_table_id = subgrid_id + "_t";
                    pager_id = "p_" + subgrid_table_id;
                    $("#" + subgrid_id).html(
                        "<table id='" + subgrid_table_id
                        + "' class='scroll'></table><div id='"
                        + pager_id + "' class='scroll'></div>");
                    jQuery("#" + subgrid_table_id).jqGrid(
                        {
                            url : '${path}/category/showByIdCategory?id='+row_id,
                            styleUI:"Bootstrap",
                            datatype : "json",
                            colNames : [ '分类ID', '分类名称', '级别'  ],
                            colModel : [
                                {name : 'id',  width : 55,search: false,align : "center"},
                                {name : 'name',editable:true,width : 90,align : "center"},
                                {name : 'level',width : 100,align : "center"},
                            ],
                            pager : pager_id,
                            height: '200px',
                            width: '1000px',
                            rowNum : 4,
                            rowList : [ 4, 10, 20, 30 ],
                            viewrecords : true,
                            autowidth: true,
                            surl:'${path}/category/showByIdCategory?id='+row_id,
                            multiselect :true, //选择框
                            editurl: '${path}/category/edit?pId='+row_id,//编辑表单提交路径

                        });
                    jQuery("#" + subgrid_table_id).jqGrid('navGrid',
                        "#" + pager_id, {
                            edit : true,
                            add : true,
                            del : true,
                            edittext: "修改",
                            addtext: "添加",
                            deltext: "删除"
                        });
                },
                subGridRowColapsed : function(subgrid_id, row_id) {
                    // this function is called before removing the data
                   var subgrid_table_id;
                    subgrid_table_id = subgrid_id+"_t";
                    jQuery("#"+subgrid_table_id).remove();
                }
            });
        jQuery("#category-tt").navGrid( '#category-page', {
            add : true,
            edit : true,
            del : true,
            edittext: "修改",
            addtext: "添加",
            deltext: "删除"
        });
    }
    //模板导出
    $('#excel1').click(function () {
        alert('是否导出模板？');
        $.ajax({
            url:'//',
            type:"post",
            success:function (re) {
            }
        });
    });
    //分类导出
    $('#excel').click(function () {
        alert('是否导出？');
        $.ajax({
            url:'/category/exportCategory',
            type:"post",
            success:function (re) {
            }
        });
    })
</script>

<div class="panel panel-info">
    <div class="panel panel-heading">
        <h2>分类信息</h2>
    </div>
    <form action="${path}/category/importCategory" method="post" enctype="multipart/form-data">
        <div align="center">
            <input type="file" name="multipartFile"><br>
            <a id="excel1"  href="javascript:void(0)" class="btn btn-success">
                &nbsp;导出模板
            </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input class='btn btn-success' type="submit" value="导入">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a id="excel"  href="javascript:void(0)" class="btn btn-success">
                &nbsp;导出
            </a>
        </div>
        <br>
    </form>
    <table id="category-tt"></table>
    <div id="category-page"></div>
</div>


