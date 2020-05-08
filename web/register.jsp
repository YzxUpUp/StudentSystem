<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>注册</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
    </script>
    <script>
        window.onload = function () {
            var i = 0;
            document.getElementById("username").onblur = function () {
                if(this.value.length >= 3 && this.value.length <= 10){
                    document.getElementById("one").innerText = "✔";
                    i=0;
                }else{
                    document.getElementById("one").innerText = "✖";
                    i=1;
                }
            }

            var password = document.getElementById("password");
            var repassword = document.getElementById("repassword");
            repassword.onblur = function () {
                if(password.value == repassword.value){
                    document.getElementById("two").innerText = "✔";
                    document.getElementById("three").innerText = "✔";
                    i=0;
                }else{
                    document.getElementById("two").innerText = "✖";
                    document.getElementById("three").innerText = "✖";
                    i=1;
                }
            }

            document.getElementById("regis").onclick = function () {
                if(i==0){
                    document.getElementById("register_info").submit();
                }else{
                    alert("请正确输入用户名和密码！");
                }
            }

            document.getElementById("check").onclick = function () {
                this.src = "${pageContext.request.contextPath}/checkCodeServlet?time=" + new Date().getTime();
            }
        }
    </script>
</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">注册</h3>
    <form action="${pageContext.request.contextPath}/registerServlet" method="post" id="register_info">
        <div class="form-group">
            <label for="user">用户名：</label><span id="one"></span>
            <input type="text" name="username" class="form-control" id="username" placeholder="请输入用户名"/>
        </div>

        <div class="form-group">
            <label for="password">密码：</label><span id="two"></span>
            <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码"/>
        </div>

        <div class="form-group">
            <label for="password">确认密码：</label><span id="three"></span>
            <input type="password" class="form-control" id="repassword" placeholder="请再次输入密码"/>
        </div>

        <div class="form-inline">
            <label for="vcode">验证码：</label>
            <input type="text" name="verifycode" class="form-control" id="verifycode" placeholder="请输入验证码"
                   style="width: 120px;"/>
            <a href="javascript:void(0)"><img src="${pageContext.request.contextPath}/checkCodeServlet" title="看不清点击刷新" id="check"/></a>
        </div>
        <hr/>
        <div class="form-group" style="text-align: center;">
            <a class="btn btn-primary" href="javascript:void(0)" id="regis">注册</a>
            <a class="btn btn-default" href="${pageContext.request.contextPath}/login.jsp">返回</a>
        </div>
    </form>
    <!-- 出错显示的信息框 -->
    <div class="alert alert-warning alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert">
            <span>&times;</span></button>
        <strong>${success}</strong>
        <strong>${fail}</strong>
    </div>
</div>
</body>
</html>