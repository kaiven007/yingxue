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
        <script src="${path}/static/js/echarts.js"></script>
        <script src="${path}/static/jqgrid/js/jquery.min.js"></script>
        <script type="text/javascript">

            $(function(){

                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('main'));

                $.get("${path}/echarts/queryUser",function(data){

                    // 指定图表的配置项和数据
                    var option = {
                        title: {
                            text: '用户注册统计图',  //标题
                            link:"${path}/main.jsp",
                            subtext:"纯属虚构",
                        },
                        tooltip: {}, //鼠标提示
                        legend: {
                            data:['小男生',"小姑娘"]  //选项卡
                        },
                        xAxis: {
                            data: data.month //横轴
                        },
                        yAxis: {},  //纵轴
                        series: [{
                            name: '小男生',
                            type: 'line',
                            data: data.boys
                        },{
                            name: '小姑娘',
                            type: 'bar',
                            data:data.girls
                        }]
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);

                },"JSON");

            });

        </script>
    </head>
    <body>
        <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
        <div id="main" style="width: 600px;height:400px;"></div>

    </body>
</html>