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
@WebServlet("/HiServlet")
public class HiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 使用 GBK 设置中文正常显示
        response.setCharacterEncoding("GBK");
        response.getWriter().write("第二个servlet,这就是：servlet"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}