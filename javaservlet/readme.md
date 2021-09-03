
先有servlet后有jsp

怎么使用：
    servlet：make和maven:war打包，放置在tomcat里，访问路径：url/javaservlet/HelloServlet或者HiServlet
    jsp：直接放进tomcat里


servlet是需要运行在web服务容器的，如tomcat内，继承特定的类，并且根据协议编写相应的web.xml进行配置(如路径和java类的映射关系)
可以实现一个请求对应一个servlet的效果。
但是返回静态页面实在太麻烦了，最终jsp 动态网页技术出现了

jsp先转换成servlet类，编译才会进行响应。这个编译转换会由tomcat控制，根据java servlet协议。
但是确实如连接所说，控制和表现，以mvc角度形容就是 视图和控制都在一个文件上控制，实在混乱

jsp编程：一种比较原始的HTTP服务的编程，可以使用java类
直接将如hello.jsp文件放到tomcat中即可运行,tomcat本来就是开发来用于实现java servlet开发的
这次是在jsp上进行逻辑控制，同样很愚蠢，开发效率不高，问题也不好找

发展史
https://blog.csdn.net/weixin_42515372/article/details/115020690?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_utm_term~default-0.essearch_pc_relevant&spm=1001.2101.3001.4242