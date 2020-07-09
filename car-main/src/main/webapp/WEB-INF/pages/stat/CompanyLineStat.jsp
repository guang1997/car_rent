<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<!DOCTYPE html>
<html style="height: 100%">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${PATH}/static/layui/css/layui.css" media="all"/>
</head>
<body style="height: 100%; margin: 0">
<!-- 搜索条件开始 -->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>查询条件</legend>
</fieldset>
<form class="layui-form" method="post" id="searchFrm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">请选择年份</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" id="year" placeholder="yyyy">
            </div>
        </div>
        <div class="layui-inline">
            <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询
            </button>
        </div>
    </div>
</form>

<!-- 搜索条件结束 -->
<div id="container" style="height: 80%"></div>
<script type="text/javascript" src="${PATH}/static/echarts/js/echarts.min.js"></script>
<script type="text/javascript" src="${PATH}/static/jquery/jquery-2.1.1.min.js"></script>
<script src="${PATH}/static/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['jquery', 'laydate'], function () {
        var $ = layui.jquery;
        var laydate = layui.laydate;
        //年选择器
        laydate.render({
            elem: '#year'
            , type: 'year'
            , value: new Date()
        });
        $("#doSearch").click(function () {
            initLineTable();
        });

        function initLineTable() {
            var year = $("#year").val();
            // 初始化线形图
            if (year == "") {
                year = new Date().getFullYear();
            }
            $.post("${PATH}/stat/getComSaleLineJson", {year: year}, function (data) {
                var dom = document.getElementById("container");
                var myChart = echarts.init(dom);
                var app = {};
                option = null;
                option = {
                    title: {
                        text: '公司年度月销售额统计表',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    xAxis: {
                        type: 'category',
                        data: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: data,
                        type: 'line'
                    }]
                };
                if (option && typeof option === "object") {
                    myChart.setOption(option, true);
                }
            });

        }

        initLineTable();
    });


</script>
</body>
</html>
</html>
