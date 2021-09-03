package com.test.servlet;

/**
 * @author brink
 * 2021/9/3 16:44
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String contextConfigLocation = "没有";
    private String contextConfigLocation_init = "也没有";
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() throws ServletException {
        super.init();
        // 获取web.xml的<param-name> 的值
        contextConfigLocation = getServletContext().getInitParameter("contextConfigLocation");
        contextConfigLocation_init = getInitParameter("contextConfigLocation_init");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 使用 GBK 设置中文正常显示
        response.setCharacterEncoding("GBK");
        response.getWriter().write("这是用于测试的servlet\n");
        response.getWriter().write("web.xml外面的参数为："+contextConfigLocation+"\n");
        response.getWriter().write("web.xml servlet里的参数为: "+contextConfigLocation_init);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}