<%--
  Created by IntelliJ IDEA.
  User: HASEE
  Date: 2019/11/5
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp" %>
<%@ page isErrorPage="true" %>
<!--
    jsp的三种注释
        前端语言注释：
            会被转译，会被发送，但不会被浏览器执行
        java语言注释：
            会被转译，但是不会被servlet执行
        jsp注释：
            不会被转译

-->
<%--
    language:声明jsp要被转译的语言
    import:声明转译的java文件要导入的包，不同的包使用逗号隔开

    jsp的局部代码块<% java代码 %> 都转译成_jspService()里面
    jsp的全局代码块 声明的java代码作为全局代码转译到jspServlet中<%! java代码 %>

    jsp的静态引入和动态引入：
        静态引入:<%@include file="" %>
        特点：会将引入的jsp文件和当前jsp文件转译成一个java文件使用，在网页中显示的是合并后的效果
        静态引入的jsp文件不会单独的转译成一个java也就是servlet文件，当前文件和静态引入的jsp文件
        不能够使用Java代码块声明同名变量

        动态引入：<jsp:include page="active.jsp"></jsp:include>
        特点：会将引入的jsp文件单独转译成一个Java文件，在当前文件转译好的java文件中调用引入的jsp文件
        的转译文件，在网页中显示合并后的效果，可以使用同名变量

        jsp的转发标签forward:<jsp:forward page="forward.jsp"></jsp:forward>
        特点：一次请求，地址栏信息不改变
        注意：在转发标签的两个标签中间除了写 <jsp:param name="str" value="88888888" />的子标签不会报错以外
        其它任意字符都会报错 name为附带的数据的键名 value为附带的数据的内容 会将数据以？的形式拼接在转发路径的后面


        jsp的九大内置对象：jsp文件在转译对应的servlet文件时自动生成的并声明的对象，在jsp页面中直接使用
        因为是在_jspService()方法中，所以必须使用局部代码块或者脚本段语句来使用，不能声明在全局代码块中
        内容：九大对象
              pageContext:页面上下文对象，封存了其它内置对象，也就是封存了当前jsp的运行信息
                          每个jsp文件单独拥有一个pageContext对象
                          作用域：当前页面
              request:封存当前请求数据的对象，由tomcat服务器来创建 作用域：一次请求
              session:此对象用来存储同一用户的不同请求数据共享的问题 作用域：一次会话
              application:也就是ServletContext对象，一个项目只有一个，存储不同用户数据共享的对象，以及一些其它操作 作用域：项目内
              response:响应对象，用来响应请求处理结果给浏览器，设置响应头，重定向
              out:响应对象，jsp内部使用，带有缓冲区的响应对象，效率高于response对象
              page:代表当前jsp的对象，就是this对象
              exception:异常对象，存储了当前运行的异常信息
                注意：使用此对象时需要在page指定中使用属性isErrorPage="true"来开启
              config:也就是ServletConfig对象，主要用来获取web.xml中的配置数据，完成一些初始化数据的读取

        四个作用域对象:
            pageContext:当前页面，解决了在当前页面内数据共享的问题  主要获取其它内置对象
            request:一次请求，解决了一次请求的servlet数据共享的问题  通过请求转发，将数据流转给下一个servlet
            session:一次会话，解决了一个用户的不同请求的数据共享的问题 将数据从一次请求流转给其它请求
            application(ServletContext):项目内，解决了不同用户的数据共享的问题 将数据从一个用户流转给其它用户
            作用：数据流转

        jsp的路径跳转：
            相对路径：需要使用../来返回上级目录
            绝对路径：/项目名/项目资源路径
            在jsp中资源的第一个/表示的是服务器根目录，也就是webapps下面的你自己的虚拟项目
--%>
<%
//    int i = 5/0;
%>
<html>
<head>
    <title>jsp认识</title>
</head>
<body>
<h3>jsp认识</h3>
<hr>
<%
    //声明Java代码域
    String str = "jsp这样写代码贼烦";
    int a = 2;
    if (a > 3) {
%>
<b>jsp的简单认识</b>
<% }  else { %>
<i><%=str%></i>
<% } test();%>
<!--全局代码块-->
<%!
//全局代码块
    public void test(){
        System.out.println("这是全局代码块");
    }
%>
<!--jsp的静态引入-->
<%@include file="static.jsp"%>

<!--jsp的动态引入-->
<jsp:include page="active.jsp"></jsp:include>

<!--jsp的转发forward标签-->
<%--<jsp:forward page="forward.jsp">--%>
    <%--<jsp:param name="str" value="88888888" />--%>
<%--</jsp:forward>--%>

<!--jsp的九大内置对象-->
<%
    //获取请求数据
    String str1 = request.getParameter("str");
    request.getAttribute("str");
%>
<%= str1%>
<!--jsp的路径-->
<br>
<a href="a/a.jsp">a.jsp</a><br>
<a href="a/b/b.jsp">b.jsp</a>
</body>
</html>
