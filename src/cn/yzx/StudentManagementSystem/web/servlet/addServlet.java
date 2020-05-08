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

@WebServlet("/addServlet")
public class addServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> map = request.getParameterMap();
        String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        if(checkcode_server.equalsIgnoreCase(map.get("verifycode")[0])){
            studentService service = new studentServiceImpl();
            student stu = new student();
            try {
                BeanUtils.populate(stu,map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            student result = service.duplicate(stu);
            if(result==null){
                //没有重复学号
                service.insertStudent(stu);
                response.sendRedirect(request.getContextPath() +"/findByPageServlet");
            }else {
                //有重复的学号
                request.setAttribute("duplicate","已存在该学号");
                request.getRequestDispatcher("/add.jsp").forward(request,response);
            }
        }else{
            request.setAttribute("checkError","验证码错误");
            request.getRequestDispatcher("/add.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
