<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
<script src="https://eqcn.ajz.miesnfu.com/wp-content/plugins/wp-3d-pony/live2dw/lib/L2Dwidget.min.js"></script>
<script>
    L2Dwidget.init({
        // 更换模型地址前后都需要修改
        // 示例:"https://unpkg.com/live2d-widget-model-unitychan/assets/unitychan.model.json"
        "model": {
            jsonPath: "https://unpkg.com/live2d-widget-model-hijiki/assets/hijiki.model.json",
            "scale": 1
        },
        // 设置位置
        "display": {
            "position": "left",
            "width": 100,
            "height": 150,
            "hOffset": 30,
            "vOffset": 0
        },
        "mobile": {
            "show": true,
            "scale": 1.5
        },
        // 透明度设置
        "react": {
            "opacityDefault": .8,
            "opacityOnHover": 1
        }
    });
</script>
</body>

</html>
