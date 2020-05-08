<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- HTML5文档-->
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>添加学生</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script>
        $(function () {
           $("#sno").blur(function () {
               alert(1);
           });
        });
    </script>
</head>
<body>
<div class="container">
    <center><h3>添加联系人页面</h3></center>
    <form action="${pageContext.request.contextPath}/addServlet" method="post">
        <div class="form-group">
            <label for="sno">学号：</label><span id="span_username">${duplicate}</span>
            <input type="text" class="form-control" id="sno" name="sno" placeholder="请输入姓名">
        </div>

        <div class="form-group">
            <label for="sname">姓名：</label>
            <input type="text" class="form-control" id="sname" name="sname" placeholder="请输入姓名">
        </div>

        <div class="form-group">
            <label>性别：</label>
            <input type="radio" name="ssex" value="男" checked="checked"/>男
            <input type="radio" name="ssex" value="女"/>女
        </div>

        <div class="form-group">
            <label for="sage">年龄：</label>
            <input type="text" class="form-control" id="sage" name="sage" placeholder="请输入年龄">
        </div>

        <div class="form-group">
            <label for="saddress">籍贯：</label>
            <select name="saddress" class="form-control" id="saddress">
                <option value="杭州">杭州</option>
                <option value="北京">北京</option>
                <option value="中国台湾">中国台湾</option>
            </select>
        </div>

        <div class="form-group">
            <label for="sqq">QQ：</label>
            <input type="text" class="form-control" name="sqq" id="sqq" placeholder="请输入QQ号码"/>
        </div>

        <div class="form-group">
            <label for="semail">Email：</label>
            <input type="text" class="form-control" name="semail" id="semail" placeholder="请输入邮箱地址"/>
        </div>

        <div class="form-inline">
            <label for="verifycode">验证码：</label>
            <input type="text" name="verifycode" class="form-control" id="verifycode" placeholder="请输入验证码"
                   style="width: 120px;"/>
            <a href="javascript:refreshCode()"><img id="check" src="${pageContext.request.contextPath}/checkCodeServlet" title="看不清点击刷新"/></a>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" id="reset"/>
            <a class="btn btn-default" href="${pageContext.request.contextPath}/findByPageServlet">返回</a>
        </div>
        <div class="alert alert-warning alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert">
                <span>&times;</span></button>
            <strong>${checkError}</strong>
        </div>
    </form>
</div>
<script>
    document.getElementById("check").onclick = function () {
        this.src = "${pageContext.request.contextPath}/checkCodeServlet?time=" + new Date().getTime();
    }

    document.getElementById("reset").onclick = function() {
        location.reload();
    }
</script>
</body>
</html>