package com.test.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author brink
 * 2021/9/3 17:57
 * 用于监听，测测看
 * 好像，只运行一次，没啥用处，应该是用于获取context对象了，然后用这个对象干很多事情？
 */
public class TestServletContextListener implements ServletContextListener {
    @Override
    // 只看到初始化一次
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("初始化："+sce.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("破坏："+sce.getServletContext());
    }


}
