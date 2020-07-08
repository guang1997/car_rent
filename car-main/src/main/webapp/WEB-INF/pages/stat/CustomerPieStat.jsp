<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body style="height: 100%; margin: 0">
<div id="container" style="height: 100%"></div>
<script type="text/javascript" src="${PATH}/static/echarts/js/echarts.min.js"></script>
<script type="text/javascript" src="${PATH}/static/jquery/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
    $.post("${PATH}/stat/getStatJson",{}, function (data) {
        var dom = document.getElementById("container");
        var myChart = echarts.init(dom);
        var app = {};
        option = null;
        option = {
            title: {
                text: '汽车出租客户来源统计表',
                left: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: '{a} <br/>{b} : {c} ({d}%)'
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: data
            },
            series: [
                {
                    name: '地区',
                    type: 'pie',
                    radius: '55%',
                    center: ['50%', '60%'],
                    data: data,
                    emphasis: {
                        itemStyle: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }
    } );
</script>
</body>
</html>
