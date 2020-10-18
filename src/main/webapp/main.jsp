    <%@ page language="java" import="java.util.*,com.baizhi.entity.*" pageEncoding="UTF-8" isELIgnored="false"%>
    <%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <!DOCTYPE html>
    <html lang="en">
    <head>
    <meta charset="UTF-8">
    <title>应学后台管理系统</title>
    <link rel="shortcut icon" href="${path}/static/img/yx-icon.png" type="images/x-icon">
    <!--引入样式文件-->
    <link rel="stylesheet" href="${path}/static/bs/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="${path}/static/jqgrid/css/trirand/ui.jqgrid-bootstrap.css" type="text/css">
    <link rel="stylesheet" href="${path}/static/yxue/yxue.css" type="text/css">

    <!--引入js功能文件-->
    <script src="${path}/static/bs/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="${path}/static/bs/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${path}/static/jqgrid/js/trirand/i18n/grid.locale-cn.js" type="text/javascript"></script>
    <script src="${path}/static/jqgrid/js/trirand/jquery.jqGrid.min.js" type="text/javascript"></script>
    <script src="${path}/static/yxue/yxue.js" type="text/javascript"></script>
    <script src="${path}/static/bs/js/ajaxfileupload.js" type="text/javascript"></script>


    <script>

    $(function () {

    })
    </script>

    <style>

    </style>
    </head>
    <body >
    <div class="navbar navbar-default" id="navbar-top">
    <div class="container-fluid">
    <div class="navbar-header">
    <a href="" class="navbar-left">
    <img src="${path}/static/img/yx-icon.png" alt="" width="48px" height="48px">
    </a>
    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#menu-1">
    <span class="caret"></span>
    </button>
    </div>
    <div class="navbar-collapse collapse" id="menu-1">
    <ul class="nav navbar-nav">
    <li><a href="javascript:void(0)">应学视频APP后台管理系统</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
    <li>
        <a href="#">
            欢迎：<span class="text text-primary">${sessionScope.username}</span>
            &nbsp;<img src="${path}/static/img/4.gif" class="img-circle" alt="" width="24px" height="24px">
        </a>
    </li>
    <li class="dropdown">
        <a href="${path}/admin/exit" >退出 <span class="glyphicon glyphicon-log-out"></span></a>
    </li>
    </ul>
    </div>
    </div>
    </div>
    <div class="container-fluid yx-nav-menu" >
    <div class="row ">
    <!--导航菜单-->
    <div class="col-xs-2 col-md-2" >
    <div class="panel panel-info ">
    <div class="panel-heading" >
        <a href="javascript:void(0)"> <span class="glyphicon glyphicon-menu-up"></span> 首页</a>
    </div>
    <div class="panel-body yx-nav-panel" style="height: 300px">

        <div class="panel-group" id="acc">
            <!--用户管理-->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <a href="#user-panel-body" class="panel-title" data-toggle="collapse"
                       data-parent="#acc"><span class="glyphicon glyphicon-user"></span> &nbsp;用户管理</a>
                </div>

                <div class="panel-collapse collapse" id="user-panel-body">
                    <div class="panel-body">
                        <ul class="list-group">
                            <li class="list-group-item">
                                <a id="user-btn" href="javascript:$('#centerLayout').load('${path}/user/user.jsp')" class="btn btn-default yx-nav-menu-btn">
                                     &nbsp;查看用户
                                </a>
                            </li><br>
                            <li class="list-group-item">
                                <a id="user-btn2" href="javascript:$('#centerLayout').load('${path}/user/userView.jsp')" class="btn btn-default yx-nav-menu-btn">
                                    &nbsp;用户统计
                                </a>
                            </li><br>
                            <li class="list-group-item">
                                <a id="user-btn3" href="javascript:$('#centerLayout').load('${path}/user/userMapJson.jsp')" class="btn btn-default yx-nav-menu-btn">
                                    &nbsp;用户分布
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <!--分类管理-->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <a href="#category-panel-body" class="panel-title" data-toggle="collapse"
                       data-parent="#acc"><span class="glyphicon glyphicon-list"></span> &nbsp;分类管理</a>
                </div>

                <div class="panel-collapse collapse" id="category-panel-body">
                    <div class="panel-body">
                        <ul class="list-group">
                            <li class="list-group-item">
                                <a id="category-btn" href="javascript:$('#centerLayout').load('${path}/category.jsp')" class="btn btn-default yx-nav-menu-btn">
                                    &nbsp;查看分类
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <!--视频管理-->

            <div class="panel panel-default">
                <div class="panel-heading">
                    <a href="#video-panel-body" class="panel-title" data-toggle="collapse"
                       data-parent="#acc"><span class="glyphicon glyphicon-film"></span>&nbsp;视频管理</a>
                </div>

                <div class="panel-collapse collapse" id="video-panel-body">
                    <div class="panel-body">
                        <ul class="list-group">
                            <li class="list-group-item">
                                <a id="video-btn" href="javascript:$('#centerLayout').load('${path}/videos/video.jsp')" class="btn btn-default yx-nav-menu-btn">
                                    &nbsp;视频管理
                                </a>
                            </li>
                            <li class="list-group-item">
                                <a id="video-btn2" href="javascript:$('#centerLayout').load('${path}/videos/videoSearch.jsp')" class="btn btn-default yx-nav-menu-btn">
                                    &nbsp;视频搜索
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <!--评论管理-->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <a href="#comment-panel-body" class="panel-title" data-toggle="collapse"
                       data-parent="#acc"><span class="glyphicon glyphicon-comment"></span> &nbsp;评论管理</a>
                </div>

                <div class="panel-collapse collapse" id="comment-panel-body">
                    <div class="panel-body">
                        <ul class="list-group">
                            <li class="list-group-item">
                                <a href="javascript:$('#centerLayout').load('${path}/comment.jsp')" class="btn btn-default yx-nav-menu-btn">  &nbsp;查看评论 </a>
                            </li>

                        </ul>
                    </div>
                </div>
            </div>
            <!--反馈信息管理-->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <a href="#feedback-panel-body" class="panel-title" data-toggle="collapse"
                       data-parent="#acc"><span class="glyphicon glyphicon-list-alt"></span> &nbsp;反馈信息管理</a>
                </div>

                <div class="panel-collapse collapse" id="feedback-panel-body">
                    <div class="panel-body">
                        <ul class="list-group">
                            <li class="list-group-item">
                                <a href="javascript:$('#centerLayout').load('${path}/feedback.jsp')" class="btn btn-default yx-nav-menu-btn">查看反馈信息</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <!--日志管理-->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <a href="#log-panel-body" class="panel-title" data-toggle="collapse"
                       data-parent="#acc"><span class="glyphicon glyphicon-oil"></span> &nbsp;日志管理</a>
                </div>

                <div class="panel-collapse collapse" id="log-panel-body">
                    <div class="panel-body">
                        <ul class="list-group">
                            <li class="list-group-item">
                                <a href="javascript:$('#centerLayout').load('${path}/log.jsp')" class="btn btn-default yx-nav-menu-btn">  &nbsp;查看日志 </a>
                            </li>

                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    </div>

    </div>
    <div class="col-xs-10 col-md-10" id="centerLayout">
    <div class="panel panel-info ">
    <div class="panel-heading">
        <a class="">应学视频APP后台管理系统</a>
    </div>

    <div class="panel-body yx-nav-panel" id="yx-content">
        <div id="page_end_html" align="center">
            <h1 >欢迎进入应学系统</h1>
        </div>

    </div>
    </div>

    </div>
    </div>
    </div>
    </body>
    </html>





































