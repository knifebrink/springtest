package cn.itcast.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class Interceptor1 implements HandlerInterceptor{
	
	//preHandle在处理器映射器之前进行执行
	//return false:拦截 return true:放行
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		System.out.println("这是第一个拦截器Interceptor1。。。preHandle");
		return true;
	}

	//还没有调用Controller，还没返回modelAndView执行
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		System.out.println("这是第一个拦截器Interceptor1。。。postHandle");
		
	}
	
	//返回modelAndView之后执行
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
					throws Exception {
		System.out.println("这是第一个拦截器Interceptor1。。。afterCompletion");
		
	}

}
