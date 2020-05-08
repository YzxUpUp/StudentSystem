package cn.yzx.StudentManagementSystem.web.servlet;

import cn.yzx.StudentManagementSystem.service.Impl.userServiceImpl;
import cn.yzx.StudentManagementSystem.service.userService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String verifycode = request.getParameter("verifycode");
        String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        if(checkcode_server.equalsIgnoreCase(verifycode)){
            userService service = new userServiceImpl();
            service.insertUser(username,password);
            request.setAttribute("success","注册成功!");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }else{
            request.setAttribute("fail","验证码错误!");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
