package cn.yzx.StudentManagementSystem.web.servlet;

import cn.yzx.StudentManagementSystem.service.Impl.studentServiceImpl;
import cn.yzx.StudentManagementSystem.service.studentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteSingleStudentServlet")
public class deleteSingleStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sno = request.getParameter("sno");
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        studentService service = new studentServiceImpl();
        service.deleteSingle(sno);
        response.sendRedirect(request.getContextPath() + "/findByPageServlet?currentPage="+currentPage+"&rows="+rows);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
