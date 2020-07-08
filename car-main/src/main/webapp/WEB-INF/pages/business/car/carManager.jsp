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
            <label class="layui-form-label">车辆号牌:</label>
            <div class="layui-input-inline">
                <input type="text" name="carnumber" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">车辆型号:</label>
            <div class="layui-input-inline">
                <input type="text" name="cartype" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">车辆颜色:</label>
            <div class="layui-input-inline">
                <input type="text" name="color" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">购买价格区间</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="lowPrice" placeholder="￥" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid">-</div>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="highPrice" placeholder="￥" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">出租价格区间</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="lowRentPrice" placeholder="￥" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid">-</div>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="highRentPrice" placeholder="￥" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">是否出租:</label>
            <div class="layui-input-inline">
                <input type="radio" name="isrenting" value="1" title="已出租">
                <input type="radio" name="isrenting" value="0" title="未出租" checked="checked">
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
<table class="layui-hide" id="carTable" lay-filter="carTable"></table>
<div style="display: none;" id="customerToolBar">
    <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
    <button type="button" class="layui-btn layui-btn-danger layui-btn-sm" lay-event="deleteBatch">批量删除</button>
</div>
<div id="carBar" style="display: none;">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-xs" lay-event="watchImg">查看大图</a>
</div>
<!-- 数据表格结束 -->

<!-- 添加和修改的弹出层开始 -->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv">
    <form class="layui-form layui-row layui-col-space10" lay-filter="dataFrm" id="dataFrm">
        <div class="layui-col-md12 layui-col-xs12">
            <div class="layui-row layui-col-space10">
                <div class="layui-col-md9 layui-col-xs7">
                    <div class="layui-form-item magt3">
                        <label class="layui-form-label">车牌号:</label>
                        <div class="layui-input-block">
                            <input type="text" name="carnumber" id="carnumber" class="layui-input" lay-verify="required"
                                   placeholder="请输入车牌号">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">车辆类型:</label>
                        <div class="layui-input-block">
                            <input type="text" name="cartype" class="layui-input" lay-verify="required"
                                   placeholder="请输入车辆类型">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">车辆颜色:</label>
                        <div class="layui-input-block">
                            <input type="text" name="color" class="layui-input" lay-verify="required"
                                   placeholder="请输入车辆颜色">
                        </div>
                    </div>
                </div>
                <div class="layui-col-md3 layui-col-xs5">
                    <div class="layui-upload-list thumbBox mag0 magt3" id="carimgDiv">
                        <!-- 显示上传的图片 -->
                        <img class="layui-upload-img thumbImg" id="showCarImg">
                        <!-- 保存当前显示图片的地址 -->
                        <input type="hidden" name="carimg" id="carimg">
                    </div>
                </div>
            </div>
            <div class="layui-form-item magb0">
                <div class="layui-inline">
                    <label class="layui-form-label">购买价格:</label>
                    <div class="layui-input-block">
                        <input type="text" name="price" class="layui-input" lay-verify="required|number"
                               placeholder="请输入购买价格">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">出租价格:</label>
                    <div class="layui-input-block">
                        <input type="text" name="rentprice" class="layui-input" lay-verify="required|number"
                               placeholder="请输入出租价格">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">出租押金:</label>
                    <div class="layui-input-block">
                        <input type="text" name="deposit" class="layui-input" lay-verify="required|number"
                               placeholder="请输入出租押金">
                    </div>
                </div>
            </div>
            <div class="layui-form-item magb0">
                <label class="layui-form-label">车辆描述:</label>
                <div class="layui-input-block">
                    <input type="text" name="description" class="layui-input" lay-verify="required"
                           placeholder="请输入车辆描述">
                </div>
            </div>
            <div class="layui-form-item magb0">
                <label class="layui-form-label">出租状态:</label>
                <div class="layui-input-block">
                    <input type="radio" name="isrenting" value="1" title="已出租">
                    <input type="radio" name="isrenting" value="0" title="未出租" checked="checked">
                </div>
            </div>
            <div class="layui-form-item magb0" style="text-align: center;">
                <div class="layui-input-block">
                    <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release"
                            lay-filter="doSubmit" lay-submit="">提交
                    </button>
                    <button type="reset" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh">
                        重置
                    </button>
                </div>
            </div>
        </div>
    </form>

</div>
<!-- 添加和修改的弹出层结束 -->
<%--查看大图弹出的开始--%>
<div id="viewBigImgDiv" style="display: none;text-align: center">
    <img alt="车辆图片" width="700px" height="700px" id="view_carimg">
</div>
<%--查看大图弹出的结束--%>
<script src="${PATH}/static/layui/layui.js"></script>
<script type="text/javascript">
    var tableIns;
    layui.use(['jquery', 'layer', 'form', 'table', 'upload'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var upload = layui.upload;
        //渲染数据表格
        tableIns = table.render({
            elem: '#carTable'   //渲染的目标对象
            , url: '${PATH}/car/loadAllCar' //数据接口
            , title: '用户数据表'//数据导出来的标题
            , toolbar: "#customerToolBar"   //表格的工具条
            , height: 'full-200'
            , cellMinWidth: 100 //设置列的最小默认宽度
            , page: true  //是否启用分页
            , cols: [[   //列表数据
                {type: 'checkbox', fixed: 'left', width: 80}
                , {field: 'carnumber', title: '车牌号', align: 'center', width: 120}
                , {field: 'cartype', title: '车辆型号', align: 'center', width: 100}
                , {field: 'color', title: '车辆颜色', align: 'center', width: 100}
                , {
                    field: 'isrenting', title: '出租状态', align: 'center', width: 100, templet: function (d) {
                        return d.isrenting == '0' ? '<font color=blue>未出租</font>' : '<font color=red>已出租</font>';
                    }
                }
                , {field: 'price', title: '购买价格', align: 'center', width: 100}
                , {field: 'rentprice', title: '出租价格', align: 'center', width: 100}
                , {field: 'deposit', title: '出租押金', align: 'center', width: 100}
                , {field: 'description', title: '车辆描述', align: 'center', width: 150}
                , {
                    field: 'carimg', title: '缩略图', align: 'center', width: 160, templet: function (d) {
                        return "<img width=40 height=40 src=${PATH}/file/downloadShowFile?path=" + d.carimg + " />";
                    }
                }
                , {field: 'createtime', title: '录入时间', align: 'center', width: 180}
                , {fixed: 'right', title: '操作', toolbar: '#carBar', align: 'center'}
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
                url: "${PATH}/car/loadAllCar?" + params
                ,page:{curr:1}
            })
        });

        //监听头部工具栏事件
        table.on("toolbar(carTable)", function (obj) {
            switch (obj.event) {
                case 'add':
                    openAddCar();
                    break;
                case 'deleteBatch':
                    deleteBatch();
                    break;
            }
            ;
        })
        //监听行工具事件
        table.on('tool(carTable)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if (layEvent === 'del') { //删除
                layer.confirm('真的删除【' + data.carnumber + '】这台车吗', function (index) {
                    //向服务端发送删除指令
                    $.post("${PATH}/car/delCar", {carnumber: data.carnumber}, function (res) {
                        layer.msg(res.msg);
                        //刷新数据 表格
                        tableIns.reload();
                    })
                });
            } else if (layEvent === 'edit') { //编辑
                openUpdateCar(data);
            } else if (layEvent === 'watchImg') {
                openViewBigImg(data);
            }
        });

        var url;
        var mainIndex;

        //打开添加页面
        function openAddCar() {
            mainIndex = layer.open({
                type: 1,
                title: '添加车辆信息',
                content: $("#saveOrUpdateDiv"),
                area: ['1000px', '450px'],
                success: function (index) {
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    $("#showCarImg").attr("src", "${PATH}/file/downloadShowFile?path=images/defaultImage.jpg");// 显示汽车图片
                    $("#carimg").val("images/defaultImage.jpg")
                    url = "${PATH}/car/addCar";
                    $("#carnumber").removeAttr("readonly");
                }
            });
        }

        //打开修改页面
        function openUpdateCar(data) {
            mainIndex = layer.open({
                type: 1,
                title: '修改车辆信息',
                content: $("#saveOrUpdateDiv"),
                area: ['1000px', '450px'],
                success: function (index) {
                    form.val("dataFrm", data);
                    $("#showCarImg").attr("src", "${PATH}/file/downloadShowFile?path=" + data.carimg);// 显示汽车图片
                    $("#carnumber").attr("readonly", "readonly");// 车牌号不能被修改
                    url = "${PATH}/car/updateCar";
                }
            });
        }

        //保存，可用于修改和保存管理员
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
            var checkStatus = table.checkStatus('carTable');
            var data = checkStatus.data;
            var params = "";
            $.each(data, function (i, item) {
                if (i == 0) {
                    params += "ids=" + item.carnumber;
                } else {
                    params += "&ids=" + item.carnumber;
                }
            });
            layer.confirm('真的删除选中的这些车辆信息吗', function (index) {
                //向服务端发送删除指令
                $.post("${PATH}/car/deleteBatchCar", params, function (res) {
                    layer.msg(res.msg);
                    //刷新数据 表格
                    tableIns.reload();
                })
            });
        }

        //上传图片
        //上传缩略图
        upload.render({
            elem: '#carimgDiv',
            url: '${PATH}/file/uploadFile',
            method: "post",  //此处是为了演示之用，实际使用中请将此删除，默认用post方式提交
            acceptMime: 'images/*',
            field: "mf",
            done: function (res, index, upload) {
                console.log(res);
                $('#showCarImg').attr('src', "${PATH}/file/downloadShowFile?path=" + res.data.src);
                $('#carimg').val(res.data.src);
                $('#carimgDiv').css("background", "#fff");
            }
        });

        // 查看大图
        function openViewBigImg(data) {
            mainIndex = layer.open({
                type: 1,
                title: '查看【' + data.carnumber + '】的图片',
                content: $("#viewBigImgDiv"),
                area: ['700px', '700px'],
                success: function (index) {
                    $("#view_carimg").attr("src", "${PATH}/file/downloadShowFile?path=" + data.carimg);
                }
            });
        }
    });
</script>
</body>
</html>




