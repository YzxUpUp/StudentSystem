package cn.yzx.StudentManagementSystem.web.servlet;

import cn.yzx.StudentManagementSystem.bean.student;
import cn.yzx.StudentManagementSystem.service.Impl.studentServiceImpl;
import cn.yzx.StudentManagementSystem.service.studentService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/updateServlet")
public class updateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> map = request.getParameterMap();
        String currentPage = request.getParameter("currentPage");
        student stu = new student();
        try {
            BeanUtils.populate(stu,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        studentService service = new studentServiceImpl();
        service.updateStudent(stu);
        response.sendRedirect(request.getContextPath()+"/findByPageServlet?currentPage="+currentPage);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
