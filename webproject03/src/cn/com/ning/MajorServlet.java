package cn.com.ning;

import cn.com.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MajorServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求编码格式
        request.setCharacterEncoding("utf-8");
        //设置响应编码格式
        response.setContentType("text/html;charset=utf-8");
        //获取请求信息
        HttpSession hs = request.getSession();
        if (hs.getAttribute("user") == null){
            //重定向到cs
            response.sendRedirect("cs");
            return;
        }
        //获取session中的数据
        User user = (User) request.getSession().getAttribute("user");

        //获取网页浏览次数
        int nums = (int)this.getServletContext().getAttribute("nums");
        //处理请求信息
        //响应处理结果
        response.getWriter().write("<html>");
        response.getWriter().write("<head>");
        response.getWriter().write("</head>");
        response.getWriter().write("<body>");
        response.getWriter().write("<h3>欢迎"+user.getUname()+"访问主页面</h3>");
        response.getWriter().write("当前网页浏览次数为："+nums);
        response.getWriter().write("<hr>");
        response.getWriter().write("<form action='show' method='get'>");
        response.getWriter().write("<input type='submit' value='查看个人信息'>");
        response.getWriter().write("</form>");
        response.getWriter().write("</body>");
        response.getWriter().write("</html>");
    }
}
