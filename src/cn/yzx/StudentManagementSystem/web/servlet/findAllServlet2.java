package cn.yzx.StudentManagementSystem.web.servlet;

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

@WebServlet("/findAllServlet2")
public class findAllServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、设置编码
        request.setCharacterEncoding("utf-8");
        //4、调用service
        studentService service = new studentServiceImpl();
        List<student> stus = service.findAll();
        //5、跳转页面刷新
        request.setAttribute("stus",stus);
        request.getRequestDispatcher("/list3.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
