<%@page isELIgnored="false" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

        <script src="${path}/static/js/echarts.js"></script>

        <script type="text/javascript">

                $(function(){

                        // 基于准备好的dom，初始化echarts实例
                        var myChart = echarts.init(document.getElementById('main'));

                        $.get("${path}/user/userView",function(data){

                                // 指定图表的配置项和数据
                                var option = {
                                        title: {
                                                text: '用户注册统计图',  //标题
                                                subtext:"纯属虚构",
                                        },
                                        tooltip: {}, //鼠标提示
                                        legend: {
                                                data:['男生',"女生"]  //选项卡
                                        },
                                        xAxis: {
                                                data: data.month //横轴
                                        },
                                        yAxis: {},  //纵轴
                                        series: [{
                                                name: '男生',
                                                type: 'line',
                                                data: data.boys
                                        },{
                                                name: '女生',
                                                type: 'bar',
                                                data:data.girls
                                        }]
                                };

                                // 使用刚指定的配置项和数据显示图表。
                                myChart.setOption(option);

                        },"JSON");
                });
        </script>
        <script type="text/javascript">
                //初始化GoEasy对象
                var goEasy = new GoEasy({
                        host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
                        appkey: "BC-49c4bb3ed91945448c35358477615835", //替换为您的应用appkey
                });
                $(function(){
                        // 基于准备好的dom，初始化echarts实例
                        var myChart = echarts.init(document.getElementById('main'));
                        //接收消息
                        goEasy.subscribe({
                                channel: "2004Channel", //替换为您自己的channel
                                onMessage: function (message) {
                                        //alert("Channel:" + message.channel + " content:" + message.content);
                                        //将json字符串转为javascript对象
                                        var data=JSON.parse(message.content);

                                        // 指定图表的配置项和数据
                                        var option = {
                                                title: {
                                                        text: '用户注册统计图',  //标题
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
                                }
                        });
                });
        </script>

<div class="panel panel-info">
        <div class="panel panel-heading">
                <h2>用户统计信息</h2>
        </div>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>
</div>
