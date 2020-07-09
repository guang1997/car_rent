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
                <input type="text" name="rentid" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">身份证号:</label>
            <div class="layui-input-inline">
                <input type="text" name="identity" id="identity" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">车牌号:</label>
            <div class="layui-input-inline">
                <input type="text" name="carnumber" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">开始时间:</label>
            <div class="layui-input-inline">
                <input type="text" name="startTime" id="startTime"  autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">结束时间:</label>
            <div class="layui-input-inline">
                <input type="text" name="endTime" id="endTime"  autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">出租单状态:</label>
            <div class="layui-input-inline">
                <input type="radio" name="rentflag" value="0" title="未归还">
                <input type="radio" name="rentflag" value="1" title="已归还" checked="checked">
            </div>
        </div>
    </div>
    <div class="layui-form-item" style="text-align: center;">
        <div class="layui-input-block">
            <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search"
                    id="doSearch">查询
            </button>
            <button type="reset" class="layui-btn layui-btn-warm  layui-icon layui-icon-refresh">重置</button>
        </div>
    </div>
</form>


<!-- 搜索条件结束 -->

<!-- 数据表格开始 -->
<table class="layui-hide" id="rentListTable" lay-filter="rentListTable"></table>
<div style="display: none;" id="rentListToolBar">
    <button type="button" class="layui-btn layui-btn-danger layui-btn-sm" lay-event="deleteBatch">批量删除</button>
</div>
<div id="rentListBar" style="display: none;">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="exportRent">导出出租单</a>
</div>
<!-- 数据表格结束 -->

<!-- 修改的弹出层开始 -->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv" >
    <form class="layui-form"  lay-filter="dataFrm" id="dataFrm">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">起租时间:</label>
                <div class="layui-input-inline">
                    <input type="text" name="begindate"  id="begindate" readonly="readonly" placeholder="请输入起租时间"  autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">还车时间:</label>
                <div class="layui-input-inline">
                    <input type="text" name="returndate"  id="returndate" readonly="readonly" placeholder="请输入还车时间"  autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">出租单号:</label>
            <div class="layui-input-block">
                <input type="text" name="rentid" lay-verify="required"  readonly="readonly"  placeholder="请输入出租单号" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">身份证号:</label>
            <div class="layui-input-block">
                <input type="text" name="identity" lay-verify="required" readonly="readonly"  placeholder="请输入身份证号" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">车牌号:</label>
                <div class="layui-input-inline">
                    <input type="text" name="carnumber" lay-verify="required"  readonly="readonly"  placeholder="请输入车牌号" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">出租价格:</label>
                <div class="layui-input-inline">
                    <input type="text" name="price" lay-verify="required"   placeholder="请输入出租价格" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">操作员:</label>
            <div class="layui-input-block">
                <input type="text" name="opername" lay-verify="required"  readonly="readonly" placeholder="请输入操作员" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" style="text-align: center;">
            <div class="layui-input-block">
                <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release" lay-filter="doSubmit" lay-submit="">提交</button>
                <button type="reset" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh" >重置</button>
            </div>
        </div>
    </form>

</div>
<!-- 修改的弹出层结束 --><%--查看大图弹出的开始--%>
<div id="viewBigImgDiv" style="display: none;text-align: center">
    <img alt="车辆图片" width="700px" height="700px" id="view_carimg">
</div>
<%--查看大图弹出的结束--%>
<script src="${PATH}/static/layui/layui.js"></script>
<script type="text/javascript">
    var tableIns;
    layui.use(['jquery', 'layer', 'form', 'table', 'upload','laydate'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var upload = layui.upload;
        var laydate = layui.laydate;
        //渲染数据表格
        tableIns = table.render({
            elem: '#rentListTable'   //渲染的目标对象
            , url: '${PATH}/rent/loadAllRentList' //数据接口
            , title: '用户数据表'//数据导出来的标题
            , toolbar: "#rentListToolBar"   //表格的工具条
            , height: 'full-200'
            , cellMinWidth: 100 //设置列的最小默认宽度
            , page: true  //是否启用分页
            , cols: [[   //列表数据
                {type: 'checkbox', fixed: 'left', width: 80}
                , {field: 'rentid', title: '出租单号', align: 'center', width: 260}
                , {field: 'identity', title: '身份证号', align: 'center', width: 200}
                , {field: 'carnumber', title: '车牌号', align: 'center', width: 100}
                , {
                    field: 'rentflag', title: '是否归还', align: 'center', width: 100, templet: function (d) {
                        return d.rentflag == '1' ? '<font color=blue>已归还</font>' : '<font color=red>未归还</font>';
                    }
                }
                , {field: 'begindate', title: '起租时间', align: 'center', width: 100}
                , {field: 'returndate', title: '还车时间', align: 'center', width: 100}
                , {field: 'price', title: '出租价格', align: 'center', width: 80}
                , {field: 'opername', title: '操作员', align: 'center', width: 80}
                , {field: 'createtime', title: '录入时间', align: 'center', width: 180}
                , {fixed: 'right', title: '操作', toolbar: '#rentListBar', align: 'center'}
            ]]
            , done: function (data, curr, count) {
                // 如果当前页不是第一页，且删除之后这页没有数据了，那么跳到前一页
                if (count == 0 && curr != 1) {
                    tableIns.reload({
                        page: {
                            curr: curr - 1
                        }
                    })
                }
            }
        })
     
        //模糊查询
        $("#doSearch").click(function () {
            var params = $("#searchFrm").serialize();
            tableIns.reload({
                url: "${PATH}/rent/loadAllRentList?" + params
                ,page:{curr:1}
            })
        });
        //初始化日期时间选择器
        laydate.render({
            elem: '#begindate'
            , type: 'datetime'
        });
        //初始化日期时间选择器
        laydate.render({
            elem: '#returndate'
            , type: 'datetime'
        });
        //监听头部工具栏事件
        table.on("toolbar(rentListTable)", function (obj) {
            if (obj.event === 'deleteBatch') {
                deleteBatch();
            }
        });
        //监听行工具事件
        table.on('tool(rentListTable)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if (layEvent === 'del') { //删除
                layer.confirm('真的删除【' + data.rentid + '】这个出租单吗', function (index) {
                    if (data.rentflag===1) {
                        //向服务端发送删除指令
                        $.post("${PATH}/rent/delRentList", {rentid: data.rentid}, function (res) {
                            layer.msg(res.msg);
                            //刷新数据 表格
                            tableIns.reload();
                        })
                    } else {
                        layer.msg("车辆尚未归还，不能删除出租单");
                        return false;
                    }

                });
            } else if (layEvent === 'edit') { //编辑
                openUpdateCar(data);
            } else if (layEvent === 'exportRent') {
                window.location.href ="${PATH}/stat/exportRent?rentid=" + data.rentid;
            }
        });

        var url;
        var mainIndex;


        //打开修改页面
        function openUpdateCar(data) {
            mainIndex = layer.open({
                type: 1,
                title: '修改出租单信息',
                content: $("#saveOrUpdateDiv"),
                area: ['1000px', '450px'],
                success: function (index) {
                    form.val("dataFrm", data);
                    url = "${PATH}/rent/updateRentList";
                }
            });
        }

        //保存，可用于修改出租单
        form.on("submit(doSubmit)", function (obj) {
            //序列化表单数据
            var params = $("#dataFrm").serialize();
            console.log(params);
            $.post(url, params, function (obj) {
                layer.msg(obj.msg);
                //关闭弹出层
                layer.close(mainIndex)
                //刷新数据 表格
                tableIns.reload();
            })
        });

        //批量删除
        function deleteBatch() {
            //得到选中的数据行
            var checkStatus = table.checkStatus('rentListTable');
            var data = checkStatus.data;
            var params = "";
            $.each(data, function (i, item) {
                if (i == 0) {
                    params += "ids=" + item.rentid;
                } else {
                    params += "&ids=" + item.rentid;
                }
            });
            layer.confirm('真的删除选中的这些出租单吗', function (index) {
                //向服务端发送删除指令
                $.post("${PATH}/rent/deleteBatchRent", params, function (res) {
                    layer.msg(res.msg);
                    //刷新数据 表格
                    tableIns.reload();
                })
            });
        }
    });
</script>
</body>
</html>




