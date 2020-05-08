<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>学生信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
        function del(sno) {
            if (confirm("确定删除吗？")) {
                location.href = "${pageContext.request.contextPath}/deleteSingleStudentServlet?sno=" + sno + "&currentPage=${pg.currentPage}&rows=10";
            }
        }

        window.onload = function () {
            document.getElementById("time").innerText = new Date().toLocaleString();
            function time() {
                document.getElementById("time").innerText = new Date().toLocaleString();
            }

            setInterval(time, 1000);
        }
    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">学生信息列表</h3>
    <div align="right">
        <nav>
            <ul class="pagination">
                <c:if test="${pg.currentPage - 1 <= 0}">
                <li style="pointer-events: none;">
                    </c:if>
                    <c:if test="${pg.currentPage - 1 > 0}">
                <li>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/findByPageServlet?currentPage=${pg.currentPage - 1}&rows=5&sname=${map.sname[0]}&saddress=${map.saddress[0]}&semail=${map.semail[0]}"
                       aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach begin="1" end="${pg.totalPage}" var="i" step="1">
                    <c:if test="${pg.currentPage == i}">
                        <li class="active">
                            <a href="${pageContext.request.contextPath}/findByPageServlet?currentPage=${i}&rows=5&sname=${map.sname[0]}&saddress=${map.saddress[0]}&semail=${map.semail[0]}">${i}</a>
                        </li>
                    </c:if>
                    <c:if test="${pg.currentPage != i}">
                        <li>
                            <a href="${pageContext.request.contextPath}/findByPageServlet?currentPage=${i}&rows=5&sname=${map.sname[0]}&saddress=${map.saddress[0]}&semail=${map.semail[0]}">${i}</a>
                        </li>
                    </c:if>
                </c:forEach>
                <c:if test="${pg.currentPage + 1 > pg.totalPage}">
                <li style="pointer-events: none;">
                    </c:if>
                    <c:if test="${pg.currentPage + 1 <= pg.totalPage}">
                <li>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/findByPageServlet?currentPage=${pg.currentPage + 1}&rows=5&sname=${map.sname[0]}&saddress=${map.saddress[0]}&semail=${map.semail[0]}"
                       aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
    <div align="right" style="margin-bottom: 20px">
        <span id="time"></span>
        <a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/login.jsp">注销</a>
    </div>
    <div style="float: left;margin-bottom: 5px">
        <form action="${pageContext.request.contextPath}/findByPageServlet" method="post" class="form-inline">
            <div class="form-group">
                <label for="exampleInputName2">姓名</label>
                <input type="text" class="form-control" id="exampleInputName2" name="sname">
            </div>
            <div class="form-group">
                <label for="exampleInputName3">籍贯</label>
                <input type="text" class="form-control" id="exampleInputName3" name="saddress">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail2">邮箱</label>
                <input type="text" class="form-control" id="exampleInputEmail2" name="semail">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>
    <div style="float: right;margin-bottom: 5px">
        <td colspan="8" align="center">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/findAllServlet">展示所有信息</a>
        </td>
        <td colspan="8" align="center">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加联系人</a>
        </td>
        <td colspan="8" align="center">
            <a class="btn btn-primary" href="javascript:void(0)" id="delSelected">删除选中的</a>
        </td>
    </div>
    <form action="${pageContext.request.contextPath}/delSelectedServlet?currentPage=${pg.currentPage}" method="post" id="delsel">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" id="sid_all"></th>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>籍贯</th>
                <th>QQ</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${pg.list}" var="stu">
                <tr>
                    <td><input type="checkbox" name="sid" value="${stu.sno}"></td>
                    <td>${stu.sno}</td>
                    <td>${stu.sname}</td>
                    <td>${stu.ssex}</td>
                    <td>${stu.sage}</td>
                    <td>${stu.saddress}</td>
                    <td>${stu.sqq}</td>
                    <td>${stu.semail}</td>
                    <td>
                        <a class="btn btn-default btn-sm"
                           href="${pageContext.request.contextPath}/relookServlet?sno=${stu.sno}&sname=${stu.sname}&ssex=${stu.ssex}&sage=${stu.sage}&saddress=${stu.saddress}&sqq=${stu.sqq}&semail=${stu.semail}&currentPage=${pg.currentPage}">修改</a>&nbsp;
                        <a class="btn btn-default btn-sm" href="javascript:del(${stu.sno})">删除</a>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="9">共查询到${pg.totalCount}条数据</td>
            </tr>
        </table>
    </form>
</div>
<script>
    var sids = document.getElementsByName("sid");
    var num = 0;
    document.getElementById("sid_all").onclick = function () {
        for (var i = 0; i < sids.length; i++){
            sids[i].checked = this.checked;
        }
    }

    document.getElementById("delSelected").onclick = function () {
        for (var i = 0; i < sids.length; i++){
            if(sids[i].checked){
                num++;
            }
        }

        if(num != 0){
            if(confirm("已选中"+num+"条信息，确认删除吗？")){
                document.getElementById("delsel").submit();
            }
        }else{
            alert("未选中任何信息");
        }
    }
</script>
</body>
</html>
