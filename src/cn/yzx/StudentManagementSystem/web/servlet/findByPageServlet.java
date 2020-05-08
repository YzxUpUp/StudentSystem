package cn.yzx.StudentManagementSystem.web.servlet;

import cn.yzx.StudentManagementSystem.bean.page;
import cn.yzx.StudentManagementSystem.bean.student;
import cn.yzx.StudentManagementSystem.service.Impl.studentServiceImpl;
import cn.yzx.StudentManagementSystem.service.studentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/findByPageServlet")
public class findByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> map = request.getParameterMap();
        //1、获取每页显示条数和当前页码
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        //2、设置默认值
        if ("".equals(rows) || rows == null) {
            rows = "5";
        }
        if ("".equals(currentPage) || currentPage == null){
            currentPage = "1";
        }
        //3、调用方法进行分页查询
        studentService service = new studentServiceImpl();
        page<student> pg = service.findByPage(currentPage,rows,map);
        //4、设置共享数据，并转发
        request.setAttribute("pg",pg);
        request.setAttribute("map",map);
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
