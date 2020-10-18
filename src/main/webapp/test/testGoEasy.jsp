<%@page isELIgnored="false" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Document</title>
        <%--<script src="${path}/static/jqgrid/js/jquery.min.js"></script>--%>
        <script type="text/javascript" src="${path}/static/js/goeasy-1.0.17.js"></script>
        <script type="text/javascript">

            //初始化GoEasy对象
            var goEasy = new GoEasy({
                host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
                appkey: "BC-49c4bb3ed91945448c35358477615835", //替换为您的应用appkey
            });

            //接收消息
            goEasy.subscribe({
                channel: "2004Channel", //替换为您自己的channel
                onMessage: function (message) {
                    alert("Channel:" + message.channel + " content:" + message.content);
                }
            });


        </script>
    </head>
    <body>
        <h1>aaa</h1>

    </body>
</html>