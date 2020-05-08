package cn.yzx.StudentManagementSystem.web.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class loginFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1、强转req类型为HttpServlet
        HttpServletRequest request = (HttpServletRequest) req;
        String requestURI = request.getRequestURI();
        //将与登录相关的资源放行，包括css、js等信息
        if(requestURI.contains("/checkCodeServlet") || requestURI.contains("/login.jsp") || requestURI.contains("/loginServlet")
        || requestURI.contains("/css/") || requestURI.contains("/fonts/" ) || requestURI.contains("/js/")){
            chain.doFilter(req,resp);
        }else{
            //2、判断session中是否有user对象
            Object user = request.getSession().getAttribute("user");
            if(user != null){
                chain.doFilter(req,resp);
            }else{
                request.setAttribute("upError","请先登录");
                request.getRequestDispatcher("/login.jsp").forward(request,resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {

    }

}
