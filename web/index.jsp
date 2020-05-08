<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>首页</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
    </script>
  </head>
  <body>
  <div align="center">
    <span>欢迎${user.username}</span><br>
    <c:if test="${testing == 0}">
      <a href="${pageContext.request.contextPath}/findByPageServlet" style="text-decoration:none;font-size:33px">
        查询所有用户信息
      </a>
    </c:if>
    <c:if test="${testing == 1}">
      <a href="${pageContext.request.contextPath}/findAllServlet2" style="text-decoration:none;font-size:33px">
        查询所有用户信息
      </a>
    </c:if>
  </div>
  </body>
</html>