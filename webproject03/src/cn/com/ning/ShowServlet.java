package cn.com.ning;

import cn.com.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求编码格式
        request.setCharacterEncoding("utf-8");
        //设置响应编码格式
        response.setContentType("text/html;charset=utf-8");
        //获取请求信息
        HttpSession hs = request.getSession();
        User user = (User) hs.getAttribute("user");
        //处理请求信息
        response.getWriter().write("<html>");
        response.getWriter().write("<head>");
        response.getWriter().write("</head>");
        response.getWriter().write("<body>");
        response.getWriter().write("<table border='1px'>");
        response.getWriter().write("<tr>");
        response.getWriter().write("<td>用户名</td>");
        response.getWriter().write("<td>"+user.getUname()+"</td>");
        response.getWriter().write("</tr>");
        response.getWriter().write("<tr>");
        response.getWriter().write("<td>密码</td>");
        response.getWriter().write("<td>"+user.getPwd()+"</td>");
        response.getWriter().write("</tr>");
        response.getWriter().write("</table>");
        response.getWriter().write("</body>");
        response.getWriter().write("</html>");
        //响应处理结果
    }
}
