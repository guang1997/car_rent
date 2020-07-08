<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="favicon.ico">
    <link rel="stylesheet" href="${PATH}/static/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${PATH}/static/css/public.css" media="all"/>
</head>
<body class="childrenBody">
<!-- 搜索条件开始 -->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>查询条件</legend>
</fieldset>
<form class="layui-form" method="post" id="searchFrm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">出租单号:</label>
            <div class="layui-input-inline">
                <input type="text" name="search_rentid" id="search_rentid" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询
            </button>
        </div>
    </div>
</form>


<div id="content" style="display: none;">
    <!-- 检查单位的表单 -->
    <blockquote class="layui-elem-quote" style="margin-top: 10px;">
        <h2>检查单表单</h2>
        <hr>
        <form class="layui-form" method="post" id="checkFrm" lay-filter="checkFrm">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">检查单号:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="checkid" readonly="readonly"
                               placeholder="请输入检查单号" id="checkid" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">出租单号:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="rentid" readonly="readonly" id="rentid"
                               placeholder="请输入出租单号" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">检查时间:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="checkdate" readonly="readonly"
                               placeholder="请选择检查时间" id="checkdate" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">存在问题:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="problem" placeholder="请输入出现的问题" id="problem" lay-verify="required"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">赔付金额:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="paymoney" placeholder="请输入赔付金额" id="paymoney"
                               lay-verify="required|number" value="0" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">操作员:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="opername" placeholder="请输入操作员" id="opername"
                               readonly="readonly" autocomplete="off" class="layui-input">
                    </div>
                </div>

            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">问题描述:</label>
                <div class="layui-input-block">
						<textarea placeholder="请输入问题描述" name="checkdesc" lay-verify="required"
                                  class="layui-textarea"></textarea>
                </div>
            </div>
            <div class="layui-form-item" style="text-align: center;">
                <button type="button" lay-submit=""
                        class="layui-btn layui-btn-normal  layui-icon layui-icon-release"
                        id="doSubmit" lay-filter="doSubmit">保存
                </button>
            </div>
        </form>
    </blockquote>

    <!-- 出租单  车辆  客户的信息展开 -->
    <div style="padding: 10px; background-color: #F2F2F2;">
        <div class="layui-row layui-col-space8">
            <div class="layui-col-md4">
                <div class="layui-card">
                    <div class="layui-card-header">车辆信息</div>
                    <div class="layui-card-body" id="car_carnumber">
                    </div>
                    <div class="layui-card-body" id="car_cartype">
                    </div>
                    <div class="layui-card-body" id="car_color">
                    </div>
                    <div class="layui-card-body" id="car_price">
                    </div>
                    <div class="layui-card-body" id="car_rentprice">
                    </div>
                    <div class="layui-card-body" id="car_deposit">
                    </div>
                    <div class="layui-card-body" id="car_description">
                    </div>
                    <div class="layui-card-body">
                        车辆图片:<img alt="" width="200" height="150" id="car_carimg" src="">
                    </div>
                </div>
            </div>
            <div class="layui-col-md4">
                <div class="layui-card">
                    <div class="layui-card-header">出租单信息</div>
                    <div class="layui-card-body" id="rent_rentid">
                    </div>
                    <div class="layui-card-body" id="rent_price">
                    </div>
                    <div class="layui-card-body" id="rent_begindate">
                    </div>
                    <div class="layui-card-body" id="rent_returndate">
                    </div>
                    <div class="layui-card-body" id="rent_opername">
                    </div>
                </div>
            </div>
            <div class="layui-col-md4">
                <div class="layui-card">
                    <div class="layui-card-header">客户信息</div>
                    <div class="layui-card-body" id="customer_identity">
                    </div>
                    <div class="layui-card-body" id="customer_custname">
                    </div>
                    <div class="layui-card-body" id="customer_sex">
                    </div>
                    <div class="layui-card-body" id="customer_address">
                    </div>
                    <div class="layui-card-body" id="customer_phone">
                    </div>
                    <div class="layui-card-body" id="customer_career">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${PATH}/static/layui/layui.js"></script>
<script type="text/javascript">
    var tableIns;
    layui.use(['jquery', 'layer', 'form', 'table', 'upload'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var upload = layui.upload;

        //查询出租单号
        $("#doSearch").click(function () {
            var rentid = $("#search_rentid").val();
            $.post("${PATH}/rent/checkRentExist", {rentid: rentid}, function (obj) {
                if (obj == "") {
                    layer.msg("您输入的出租单号不存在，请重新输入")
                } else if (obj.rentflag == 1) {
                    layer.msg("车辆已归还，请输入其他单号")
                    $("#content").hide();
                } else if (obj.rentflag == 0) {
                    initCheckFormData(rentid);
                    $("#content").show();
                }
            });
        });

        // 加载检查单数据
        function initCheckTable(rentid) {
            $.post("${PATH}/check/getCheckByRentId", {rentid: rentid}, function (check) {
                $("#checkid").val(check.checkid);
                $("#rentid").val(check.rentid);
                $("#checkdate").val(check.checkdate);
                $("#problem").val(check.problem);
                $("#paymoney").val(check.paymoney);
                $("#opername").val(check.opername);
                $("#checkdesc").val(check.checkdesc);
            });
        }

        // 加载车辆信息
        function initCarTable(rentid) {
            $.post("${PATH}/check/getCarByRentId", {rentid: rentid}, function (car) {
                console.log(car)
                $("#car_carnumber").html("车牌号：" + car.carnumber);
                $("#car_cartype").html("车辆型号：" + car.cartype);
                $("#car_color").html("车辆颜色：" + car.color);
                $("#car_price").html("购买价格：" + car.price);
                $("#car_rentprice").html("出租价格：" + car.rentprice);
                $("#car_deposit").html("出租押金：" + car.deposit);
                $("#car_description").html("车辆描述：" + car.description);
                $("#car_carimg").attr("src", "${PATH}/file/downloadShowFile?path=" + car.carimg);
            });

        }

        // 加载出租单信息
        function initRentListTable(rentid) {
            $.post("${PATH}/check/getRentById", {rentid: rentid}, function (rent) {
                console.log(rent)
                $("#rent_rentid").html("出租单号：" + rent.rentid);
                $("#rent_price").html("出租价格：" + rent.price);
                $("#rent_begindate").html("起租时间：" + rent.begindate);
                $("#rent_returndate").html("还车时间：" + rent.returndate);
                $("#rent_opername").html("操作员：" + rent.opername);
            });
        }

        // 加载客户信息
        function initCusTable(rentid) {
            $.post("${PATH}/check/getCustomerByRentId", {rentid: rentid}, function (customer) {
                console.log(customer);
                $("#customer_identity").html("身份证号：" + customer.identity);
                $("#customer_custname").html("顾客姓名：" + customer.custname);
                $("#customer_sex").html("顾客性别：" + customer.sex);
                $("#customer_address").html("地址：" + customer.address);
                $("#customer_phone").html("手机号：" + customer.phone);
                $("#customer_career").html("职业；" + customer.career);
            });
        }

        // 加载表单数据和 卡片面板的数据
        function initCheckFormData(rentid) {
            initCheckTable(rentid);
            initCarTable(rentid);
            initRentListTable(rentid);
            initCusTable(rentid);
        }

        form.on('submit(doSubmit)', function(data){
            // 保存数据
            $("#doSave").click(function () {
                var params=$("#checkFrm").serialize();
                $.post("${PATH}/check/saveCheck", params, function (obj) {
                    layer.msg(obj.msg);
                    $("#content").hide();
                });
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });
        });

    });
</script>
</body>
</html>





