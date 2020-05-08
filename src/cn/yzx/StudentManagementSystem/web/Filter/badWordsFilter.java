package cn.yzx.StudentManagementSystem.web.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

@WebFilter("/*")
public class badWordsFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //增强request对象获取属性的方法
        //原理是增强了代理对象的getParameterMap和getParameter的执行结果，其他方法不增强，return method.invoke(req,args);只是表示
        //代理对象的该方法不做修改，执行结果和真实对象一致
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //判断是否为getParameterMap方法(添加和修改获取map集合)或getParameter方法(注册获取单个属性)
                if(method.getName().equals("getParameter")){
                    String value = (String) method.invoke(req,args); //获取方法返回的属性值
                    if(value != null){ //若存在值，判断是否包含敏感词汇
                        for(String str : list){
                            if(value.contains(str)){ //包含，则替换
                                value = value.replace(str,"**");
                            }
                        }
                    }
//                    System.out.println(value);
                    return value; //当调用代理对象的getParameter方法返回String字符串时，返回的是该String字符串
                }

                if(method.getName().equals("getParameterMap")){
                    Map<String, String[]> map = (Map<String, String[]>) method.invoke(req,args);
                    if(!map.isEmpty()){ //如过该map集合不为空
                        Set<Map.Entry<String, String[]>> entries = map.entrySet();
                        for(Iterator<Map.Entry<String, String[]>> iter = entries.iterator();iter.hasNext();){
                            Map.Entry<String, String[]> maps = iter.next(); //获取map中的键值对
                            String[] values = maps.getValue(); //获取单个键值对的值，此处是一个数组
                            for(String str : list){
                                if(values[0].contains(str)){ //包含，则替换
                                    values[0] = values[0].replace(str,"**"); //对数组的第一个元素进行过滤，若包含敏感词，则替换
                                    //此时会直接对元素进行修改，所以不需要再进行setvalue()操作，且会报错
                                }
                            }
//                            System.out.println(values[0]);
                        }
                    }
                    return map; //当调用代理对象的getParameterMap方法返回map集合时，返回的是该map集合
                }

                return method.invoke(req,args); //若不是，代理对象的该方法不做修改，执行结果和真实对象一致
            }
        });

        //2、无论是否包含敏感词汇，都要放行，传入代理对象
        chain.doFilter(proxy_req, resp);
    }

    private List<String> list = new ArrayList<>(); //创建list集合装载敏感词汇

    public void init(FilterConfig config) throws ServletException {
        try {
            //在服务器启动时，加载敏感词汇资源
            ServletContext servletContext = config.getServletContext(); //获取servletContext对象
            String realPath = servletContext.getRealPath("WEB-INF/classes/敏感词汇.txt"); //获取文件在服务器的真实路径
            BufferedReader bf = new BufferedReader(new FileReader(realPath)); //使用缓冲流读取文件
            String word;
            while((word = bf.readLine()) != null){ //遍历文件
                list.add(word); //装载敏感词汇
            }
//            System.out.println(list);
            bf.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void destroy() {

    }

}
