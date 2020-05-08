package cn.yzx.StudentManagementSystem.web.servlet;

import cn.yzx.StudentManagementSystem.bean.user;
import cn.yzx.StudentManagementSystem.service.Impl.userServiceImpl;
import cn.yzx.StudentManagementSystem.service.userService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、设置编码
        request.setCharacterEncoding("utf-8");
        //2、获取数据
        Map<String, String[]> map = request.getParameterMap();
        String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        //3、判断验证码、用户名、密码
        if (checkcode_server.equalsIgnoreCase(map.get("verifycode")[0])){
            //验证码正确
            user u = new user();
            try {
                BeanUtils.populate(u,map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            userService service = new userServiceImpl();
            user uu = service.findUser(u);
            if(uu != null){
                //登录成功
                //判断账户类型
                if("admin".equals(uu.getUsername())){
                    //管理员账户
                    request.getSession().setAttribute("user",uu); //使用session共享数据，因为之后使用过滤器一次会话中会有多次用到共享数据
                    request.getSession().setAttribute("testing",0);
                    request.getRequestDispatcher("/index.jsp").forward(request,response);
                }else{
                    //教师账户
                    request.getSession().setAttribute("user",uu);
                    request.getSession().setAttribute("testing",1);
                    request.getRequestDispatcher("/index.jsp").forward(request,response);
                }
            }else{
                //登录失败
                request.setAttribute("upError","用户名或密码错误!");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        }else {
            //验证码错误
            request.setAttribute("checkError","验证码错误!");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
