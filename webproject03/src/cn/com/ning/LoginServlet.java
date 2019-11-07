package cn.com.ning;

import cn.com.pojo.User;
import cn.com.service.LoginService;
import cn.com.service.impl.LoginServiceImp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
@SuppressWarnings("all")
/*
中文乱码解决
1,可以使用String来进行重新编码
    String uname = request.getParameter("uname");
    uname = new String(uname.getBytes("iso8859-1"),"utf-8");
2，使用公共配置
    get方式：
        步骤一：request.setCharacterEncoding("utf-8");
        步骤二：在server.xml文件 在coonector标签中的属性中加上useBodyEncodingForURI="true"

    post方式：
        request.setCharacterEncoding("utf-8");

   servlet的流程：
        浏览器发起请求到服务器
        服务器接收浏览器的请求，进行解析，创建request对象存储请求数据
        服务器调用对应的servlet进行请求处理，并将request对象作为实参传递给servlet的方法
        servlet的方法执行进行请求处理
                //设置请求编码格式
                //设置响应编码格式
                //获取请求信息
                //处理请求信息
                    //创建业务层对象
                    //调用业务层对象的方法
                //响应处理结果


    请求转发：
        作用：实现多个servlet联动操作处理请求，避免代码冗余，让每个servlet的职责更加明确
        使用方式：
            request.getRequestDispatcher("page").forward(request,response);
        特点：
            一次请求，浏览器地址信息栏不改变
        注意：
            请求转发后直接return结束即可

    request对象的作用域：基于请求转发
        一次请求内的所有servlet共享 使用request对象进行数据流转，数据只在一次请求内有效

    重定向：当前的请求，servlet无法处理
    或者使用请求转发，会造成表单数据重复提交

        使用：
             response.sendRedirect("major");
        特点：两次请求
            地址栏信息会改变
            两个request对象
        如果请求中有表单数据，而数据又比较重要，不能重复提交，建议使用重定向
        如果请求被servlet接收后，无法进行处理，建议使用重定向到可以处理的资源位置


     解决主页面用户名显示为null的问题：
        原因：
            因为在用户登录成功后重定向显示主页面，两次请求，用户的信息在第一次的请求中
            第二次请求中没有用户数据，所以显示为null

        解决：
            使用session
     使用ServletContext对象完整网站网页计数器
     在用户登录检验中创建计数器并自增，然后存储到ServletContext对象中
     在主页面中取出计数器数据显示给用户即可
*/
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求编码格式
        request.setCharacterEncoding("utf-8");
        //设置响应编码格式
        response.setContentType("text/html;charset=utf-8");
        //获取请求信息
        String uname = request.getParameter("uname");
//        uname = new String(uname.getBytes("iso8859-1"),"utf-8");
        String pwd = request.getParameter("pwd");
        System.out.println(uname+":"+pwd);
        //处理请求信息
            //获取业务对象
        LoginService ls = new LoginServiceImp();
        User u = ls.checkLoginService(uname,pwd);
        System.out.println(u);
        //响应处理结果
        if (u != null) {
//            response.getWriter().write("登录成功");
//            请求转发：
//            request.getRequestDispatcher("major").forward(request,response);
            //重定向

            //创建coolkie信息，实现三天免登录
            Cookie c = new Cookie("uid",u.getUid()+"");
            //设置cookie的有效期
            c.setMaxAge(3*24*3600);
            c.setPath("cs");
            //添加cookie信息
            response.addCookie(c);

            //将数据存储到session对象中
            HttpSession hs = request.getSession();
            hs.setAttribute("user",u);

            //创建网页计数器
            //获取计数器
//            ServletContext sc = this.getServletContext();
//            if (sc.getAttribute("nums") != null){
//                int nums = Integer.parseInt((String) sc.getAttribute("nums"));
//                //计数器自增
//                nums+=1;
//                //再次存储到ServletContext对象中
//                sc.setAttribute("nums",nums);
//            }else {
//                sc.setAttribute("nums",1);
//            }
            ServletContext sc=this.getServletContext();
            if(sc.getAttribute("nums")!=null){
                int nums=Integer.parseInt((String) sc.getAttribute("nums"));
//                int nums = (int) sc.getAttribute("nums");
                //计数器自增
                nums+=1;
                //再次存储到ServletContext对象中
                sc.setAttribute("nums", nums);
            }else{
                sc.setAttribute("nums", 1);
            }

            //重定向
            response.sendRedirect("major");
            return;
        }else {
//            response.getWriter().write("登录失败");

            //使用request对象实现不同的servlet之间的数据流转
            request.setAttribute("str","用户名或密码错误");

            //使用请求转发
            request.getRequestDispatcher("page").forward(request,response);
            return;
        }
    }
}
