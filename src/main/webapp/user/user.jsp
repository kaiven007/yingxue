<%@ page language="java"  pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $(function () {
        // 初始化用户数据表格
        $('#user-tt').jqGrid({
            url: '${path}/user/showUser',
            datatype: 'json',

            colNames: ['编号', '账号名称','性别','手机号','签名','头像','状态','注册时间','学分','绑定的第三方账号','城市'],
            styleUI: 'Bootstrap',
            colModel: [
                {name : 'id',width : 55,editable: false,search: false,align : "center"},
                {name : 'username',editable:true,width : 90,align : "center"},
                {name : 'sex',editable:true,width : 90,align : "center"},
                {name : 'mobile',editable:true,width : 80,align : "center"},
                {name : 'sign',editable:true,width : 80,align : "center"},
                {
                    name: 'headShow',
                    edittype: "file",
                    editable: true,
                    search: false,
                    index: 'name asc, invdate',
                    width: 100,
                    align: "center",
                    //参数：各子的值,操作,行对象
                    formatter: function (cellvalue, options, rowObject) {
                        return "<img src='" + cellvalue + "' width='64px' height='64px'>"
                    }
                },
                {name : 'status',width : 100,
                    align : "center",
                    search: false,
                    formatter:function(cellvalue, options, rowObject){
                        //三个参数：列的值，操作，行对象  rowObject.id
                        if(cellvalue=="1"){
                            return "<a class='btn btn-success' onclick='updateStatus(\""+rowObject.id+"\")'>正常</a>";
                        }else{
                            return "<a class='btn btn-danger' onclick='updateStatus(\""+rowObject.id+"\")' >冻结</a>";
                        }
                    }},
                {
                    name: 'regTime', editable: true,align: "center",search: false,
                    label: "创建时间", editable: false,edittype: 'date', formatter: "date",
                    formatoptions: {srcformat: 'Y-m-d', newformat: 'Y-m-d'}
                },
                {name : 'score',editable:true,width : 80,align : "center"},
                {name : 'wechat',editable:true,width : 80,align : "center"},
                {name : 'fcity.name',editable: true,search: false,
                    align : "center",
                    edittype:"select",
                    editoptions:{
                        dataUrl:'${path}/video/cate?sty=city'
                    }
                }
            ],
            autowidth: true,
            mtype: 'get',
            pager: '#user-pager',
            rowNum : 5,
            rowList: [5,10, 20, 30],
            viewrecords: true,
            editurl: '${path}/user/edit',//编辑表单提交路径
            multiselect: true,
            height: '300px',
            width: '500px',
            rownumbers: true,
            page: 1,
            surl:'${path}/user/showUser',
            subgrid:true,
        }).navGrid( '#user-pager', {
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
                // 添加的同时提交额外参数
                closeAfterAdd: true,//关闭添加框
                afterSubmit: function (data) {  //添加成功之后执行的内容
                    //1.数据入库    文件数据不对   文件没有上传
                    //2.文件异步上传   添加成功之后  上传
                    //3.修改文件路径   （id,要的的字段内容）
                    //id=  data.responseText
                    // alert(data.responseJSON.message);
                    //alert(123);
                    let id = data.responseJSON.message;
                    $.ajaxFileUpload({
                        url: "${path}/user/headUpload",
                        type: "post",
                        /**
                         * 需要在上传文件的时候，提交一个新添加数据的id,
                         *  由于我们是在信息添加成功后处理文件上传 ，所以需要根据id
                         *  修改一些文件在服务器的保存路径
                         */
                        data: {"id": id},
                        fileElementId: "headShow", //文件选择框的id属性，即<input type="file">的id
                        success: function () {
                            //上传成功 所作的事情
                            //刷新页面
                            $("#user-tt").trigger("reloadGrid");
                        }
                    });
                    return ["ok"];
                }
            },  //添加之后的额外操作
            {}   //删除之后的额外操作
        );
    });
    //删除选中

    function updateStatus(a) {
        $.ajax({
            "url":"${path}/user/updateStatus",
            "type":"post",
            "data":"id="+a,
            "success":function () {
                $("#user-tt").trigger("reloadGrid");
            }
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
    //用户导出
    $('#excel').click(function () {
        alert('是否导出？');
        $.ajax({
            url:'/user/exportUser',
            type:"post",
            success:function (re) {
            }
        });
    })
</script>
<div class="panel panel-info">
    <div class="panel panel-heading">
        <h2>用户信息</h2>
    </div>
    <form action="${path}/user/importUser" method="post" enctype="multipart/form-data">
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
    <table id="user-tt"></table>
    <div id="user-pager"></div>

</div>


