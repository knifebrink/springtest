<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜鸟教程</title>
</head>
<body>
<%
    out.println("Hello World!");
%>
<p>
    今天的日期是: <%= (new java.util.Date()).toLocaleString()%>
</p>

<%
    if((new java.util.Date()).getSeconds()%2==0){
            out.println("这是奇数秒");
            }else{
        out.println("这是偶数秒");
                    }
%>
</body>
</html>