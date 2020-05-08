<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
    <head>
        <!-- 指定字符集 -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>修改学生信息</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <script src="js/jquery-2.1.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        
    </head>
    <body>
        <div class="container" style="width: 400px;">
        <h3 style="text-align: center;">修改联系人</h3>
        <form action="${pageContext.request.contextPath}/updateServlet" method="post">
            <input type="hidden" name="sno" value="${stu.sno}">
            <input type="hidden" name="currentPage" value="${currentPage}">
          <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="sname"  readonly="readonly" value="${stu.sname}"/>
          </div>

          <div class="form-group">
            <label>性别：</label>
              <c:if test="${stu.ssex == '男'}">
              <input type="radio" name="ssex" value="男" checked />男
              <input type="radio" name="ssex" value="女"  />女
              </c:if>
              <c:if test="${stu.ssex == '女'}">
                  <input type="radio" name="ssex" value="男"  />男
                  <input type="radio" name="ssex" value="女" checked />女
              </c:if>
          </div>

          <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age"  name="sage" value="${stu.sage}" />
          </div>

          <div class="form-group">
              <c:if test="${stu.saddress == '杭州'}">
                  <label for="address">籍贯：</label>
                  <select name="saddress" class="form-control" >
                      <option value="杭州" selected>杭州</option>
                      <option value="北京">北京</option>
                      <option value="中国台湾">中国台湾</option>
                  </select>
              </c:if>
              <c:if test="${stu.saddress == '北京'}">
                  <label for="address">籍贯：</label>
                  <select name="saddress" class="form-control" >
                      <option value="杭州" >杭州</option>
                      <option value="北京" selected>北京</option>
                      <option value="中国台湾">中国台湾</option>
                  </select>
              </c:if>
              <c:if test="${stu.saddress == '中国台湾'}">
                  <label for="address">籍贯：</label>
                  <select name="saddress" class="form-control" >
                      <option value="杭州" >杭州</option>
                      <option value="北京">北京</option>
                      <option value="中国台湾" selected>中国台湾</option>
                  </select>
              </c:if>
          </div>

          <div class="form-group">
            <label for="qq">QQ：</label>
            <input type="text" class="form-control" name="sqq" value="${stu.sqq}"/>
          </div>

          <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" class="form-control" name="semail" value="${stu.semail}"/>
          </div>

             <div class="form-group" style="text-align: center">
                <input class="btn btn-primary" type="submit" value="提交" />
                <input class="btn btn-default" type="reset" value="重置" id="reset"/>
                 <a class="btn btn-default" href="${pageContext.request.contextPath}/findByPageServlet?currentPage=${currentPage}">返回</a>
             </div>
        </form>
        </div>
    <script>
        document.getElementById("reset").onclick = function () {
            location.reload();
        }
    </script>
    </body>
</html>