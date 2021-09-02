package cn.itcast.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class Interceptor2 implements HandlerInterceptor{
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		System.out.println("这是第二个拦截器Interceptor2。。。preHandle");
		return true;
	}


	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		System.out.println("这是第二个拦截器Interceptor2。。。postHandle");
		
	}

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
					throws Exception {
		System.out.println("这是第二个拦截器Interceptor2。。。afterCompletion");
		
	}

}
