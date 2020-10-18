<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html lang="zh">
<head>
    <meta charset="UTF-8"/>
    <!--在手机网站，都需要加上视口约束！！！-->
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no"/>
    <!--以最新的内核渲染页面-->
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>登录</title>
    <!--引入css文件-->
    <link rel="stylesheet" type="text/css" href="${path}/static/bs/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/static/bs/css/bootstrap-theme.css"/>
    <!--引入jQuery-->
    <script src="${path}/static/bs/js/jquery-3.3.1.min.js" type="text/javascript"></script>
    <!--引入js文件-->
    <script src="${path}/static/bs/js/bootstrap.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${path}/static/yxue/yxue.js"></script>
    <style type="text/css">


    </style>
</head>
<body class="container" >
<div class="col-md-4 col-md-offset-4" style="align:center;"><br/><br/><br/><br/>
    <div class="panel" style="align:center;width: 300px;">
        <div class="middle-box text-center loginscreen  animated fadeInDown">
            <div >
                <div>

                    <h1 class="logo-name"><img src="${path}/static/img/yx-icon.png"></h1>

                </div>
                <h3>应学后台管理系统</h3>

                <form  role="form" action="${path}/admin/adminLogin" method="post">
                    <input type="hidden" value="1" name="id">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="用户名" name="username">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="密码" name="password">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="phone" name="phone" placeholder="请输入手机号" required="">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="code" name="code" placeholder="请输入验证码" required="" >
                        <br>
                        <a id="sendPhoneCode" href="javascript:void(0)" class="btn btn-default">发送验证码</a>
                    </div>
                    <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>

                </form>
            </div>
        </div>
    </div>

</div>
</body>
</html>