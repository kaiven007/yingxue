<%@ page language="java"  pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script>
    $(function(){
        pageInit();
    });
    function pageInit(){
        jQuery('#comment-tt').jqGrid(
            {
                url : '${path}/comment/showAllComment',
                styleUI:"Bootstrap",
                datatype : "json",
                colNames : [ '评论ID', '用户名称', '视频','评论内容','评论时间' ],
                colModel : [
                    {'name' : 'id',  width : 55,search: false,align : "center"},
                    {'name' : 'fuser.username',editable: true,search: false,
                        align : "center",
                        edittype:"select",
                        editoptions:{
                            dataUrl:'${path}/video/cate?sty=user'
                        }
                    },
                    {'name': 'fvideo.videoUrl',
                        editable: true,
                        search: false,
                        index: 'name asc, invdate',
                        align: "center",
                        edittype:"select",
                        editoptions:{
                            dataUrl:'${path}/video/cate?sty=video'
                        },
                        //参数：各子的值,操作,行对象
                        formatter: function (cellvalue, options, rowObject) {
                            return "<video controls='controls' <source src='" + cellvalue + "' width='100px' height='80px'>"
                        }
                    },
                    {'name' : 'content',editable: true,width : 100,align : "center"},
                    {'name': 'createAt', align: "center",search: false,
                        label: "创建时间", editable: false,edittype: 'date', formatter: "date",
                        formatoptions: {srcformat: 'Y-m-d', newformat: 'Y-m-d'}
                    },
                ],
                rowNum : 5,
                rowList : [5, 10, 15, 20 ],
                pager : '#comment-page',
                viewrecords : true,
                multiselect :true, //选择框
                subGrid : true,
                page: 1,
                height: '400px',
                width: '1000px',
                autowidth: true,
                editurl: '${path}/comment/edit',//编辑表单提交路径
                //rownumbers: true,//序列
                surl:'${path}/comment/showAllComment',
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
                            url : '${path}/comment/showByIdComment?id='+row_id,
                            styleUI:"Bootstrap",
                            datatype : "json",
                            colNames : [ '评论ID', '用户名称', '视频','评论内容','评论时间'  ],
                            colModel : [
                                {'name' : 'id', search: false, width : 55,align : "center"},
                                {'name' : 'fuser.username',editable: true,search: false,
                                    align : "center",
                                    edittype:"select",
                                    editoptions:{
                                        dataUrl:'${path}/video/cate?sty=user'
                                    }
                                },
                                {'name': 'fvideo.videoUrl',
                                    editable: true,
                                    search: false,
                                    index: 'name asc, invdate',
                                    align: "center",
                                    edittype:"select",
                                    editoptions:{
                                        dataUrl:'${path}/video/cate?sty=video'
                                    },
                                    //参数：各子的值,操作,行对象
                                    formatter: function (cellvalue, options, rowObject) {
                                        return "<video controls='controls' <source src='" + cellvalue + "' width='100px' height='80px'>"
                                    }
                                },
                                {'name' : 'content',editable: true,width : 100,align : "center"},
                                {'name': 'createAt', align: "center",search: false,
                                    label: "创建时间", edittype: 'date', formatter: "date",
                                    formatoptions: {srcformat: 'Y-m-d', newformat: 'Y-m-d'}
                                },
                            ],
                            pager : pager_id,
                            height: '200px',
                            width: '1000px',
                            rowNum : 4,
                            rowList : [ 4, 10, 20, 30 ],
                            viewrecords : true,
                            autowidth: true,
                            multiselect :true, //选择框
                            surl:'${path}/comment/showAllComment?id='+row_id,
                            editurl: '${path}/comment/edit?id='+row_id,//编辑表单提交路径

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
            });
        jQuery("#comment-tt").navGrid( '#comment-page', {
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
    //评论导出
    $('#excel').click(function () {
        alert('是否导出？');
        $.ajax({
            url:'/comment/exportComment',
            type:"post",
            success:function (re) {
            }
        });
    })
</script>

<div class="panel panel-info">
    <div class="panel panel-heading">
        <h2>评论信息</h2>
    </div>
    <form action="${path}/comment/importComment" method="post" enctype="multipart/form-data">
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
    <table id="comment-tt"></table>
    <div id="comment-page"></div>
</div>


