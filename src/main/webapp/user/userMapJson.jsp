<%@page isELIgnored="false" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
        <script src="${path}/static/js/echarts.js"></script>

        <script src="${path}/static/js/china.js"></script>

<script type="text/javascript">

            $(function(){

                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('main'));

                $.get("${path}/user/userMap",function(datas){

                    var series=[];

                    for(var i=0;i<datas.length;i++){
                        var data=datas[i];

                        series.push(
                            {
                                name: data.sex,
                                type: 'map',
                                mapType: 'china',
                                label: {
                                    normal: {
                                        show: false
                                    },
                                    emphasis: {
                                        show: true
                                    }
                                },
                                data:data.city
                            }
                        )
                    }

                    // 指定图表的配置项和数据
                    var option = {
                        title : {
                            text: '每月用户注册量',
                            subtext: '纯属虚构',
                            left: 'center'
                        },
                        tooltip : {
                            trigger: 'item'
                        },
                        legend: {
                            orient: 'vertical',
                            left: 'left',
                            data:['男生','女生']
                        },
                        visualMap: {
                            min: 0,
                            max: 1000,
                            left: 'left',
                            top: 'bottom',
                            text:['高','低'],           // 文本，默认为数值文本
                            calculable : true
                        },
                        toolbox: {
                            show: true,
                            orient : 'vertical',
                            left: 'right',
                            top: 'center',
                            feature : {
                                mark : {show: true},
                                dataView : {show: true, readOnly: false},
                                restore : {show: true},
                                saveAsImage : {show: true}
                            }
                        },
                        series : series
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);

                },"JSON")



            });

</script>
<div class="panel panel-info">
    <div class="panel panel-heading">
        <h2>用户分布信息</h2>
    </div>
        <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
        <div id="main" style="width: 600px;height:400px;"></div>
</div>