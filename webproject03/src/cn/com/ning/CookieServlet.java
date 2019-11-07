package cn.com.ning;

import cn.com.pojo.User;
import cn.com.service.LoginService;
import cn.com.service.impl.LoginServiceImp;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@SuppressWarnings("all")
/*
* cookie信校验
*       判断请求中是否携带正确的cookie信息
*       如果有则校验cookie信息是否正确
*           如果校验正确则直接响应主页面给用户
*           如果不正确则响应登录页面给用户
*       没有则请求转发给登录页面
*
*
* */
public class CookieServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求编码格式
        request.setCharacterEncoding("utf-8");
        //设置响应编码格式
        response.setContentType("text/html;charset=utf-8");
        //获取请求信息
        //获取cookie信息
        Cookie[] cookies = request.getCookies();
        //处理请求信息
        if (cookies != null) {
            //遍历cookie信息
            String uid="";
            for (Cookie cookie:cookies) {
                if ("uid".equals(cookie.getName())) {
                    uid = cookie.getValue();
                }
            }
            //校验uid是否存在
            if ("".equals(uid)) {
                request.getRequestDispatcher("page").forward(request,response);
                return;
            }else {
                //校验uid用户信息
                //获取业务层对象
                LoginService ls = new LoginServiceImp();
                User user = ls.checkUidService(uid);
                if (user != null) {
                    //将用户数据存储到session对象中
                    request.getSession().setAttribute("user",user);

                    //网页计数器自增
//                    int nums = (int)this.getServletContext().getAttribute("nums");
//                    nums+=1;
//                    this.getServletContext().setAttribute("nums",nums);
                    //网页计数器自增
                    int nums=(int) this.getServletContext().getAttribute("nums");
                    nums+=1;
                    this.getServletContext().setAttribute("nums", nums);

                    //如果通过uid拿到user，判断user是否存在，存在直接重定向
                    response.sendRedirect("major");
                    return;
                }else {
                    //不存在请求转发到登录页面
                    request.getRequestDispatcher("page").forward(request,response);
                    return;
                }
            }
        }else {
            //响应处理结果
            //直接响应
            //请求转发
            request.getRequestDispatcher("page").forward(request,response);
            return;
            //重定向
        }
    }
}
