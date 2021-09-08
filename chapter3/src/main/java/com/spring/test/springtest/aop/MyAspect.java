package com.spring.test.springtest.aop;

import com.spring.test.springtest.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import com.spring.test.springtest.aop.service.*;


/**
 * 主要是织入流程，流程图不错
 * 切面编程测试
 * 连接点：被拦截的对象，AOP将通过动态代理技术把它织入对应的流程中。特定方法，如{@link UserService#printUser(User)}
 * 切点：切面应用于多个连接点，适配连接点。可以说是正则式或规则所表示的所有点
 * 通知：就是按照约定的流程下的方法，如本类中注释标注的方法
 * 				前置通知（before advice）、后置通知（after advice）、环绕通知（around advice）、事后返回通知（afterReturning advice）
 * 				和异常通知（afterThrowing advice），它会根据约定织入流程中，需要弄明白它们在流程中的顺序和运行的条件。
 * 目标对象：被代理对象如{@link UserServiceImpl 实例}
 * 引入：引入新的累，增强现有bean方法
 * 织入：就是生成代理，将连接点置入到切面
 * 切面：可以定义切点、各类通知、引入的内容，此类就是一个切面
 */
@Aspect
public class MyAspect {

	@Pointcut("execution(* com.spring.test.springtest.aop.service.UserServiceImpl.printUser(..))")
	public void pointCut() {
	}
	
	@Before("pointCut() && args(user)")
	public void beforeParam( User user) {
		System.out.println("beforeParam ......");
	} 
	
	@Before("pointCut()")
	public void before() {
		System.out.println("before ......");
	}
	
	@After("pointCut()")
	public void after() {
		System.out.println("after ......");
	}
	
	
	@AfterReturning("pointCut()")
	public void afterReturning() {
		System.out.println("afterReturning ......");
	}
	
	@AfterThrowing("pointCut()")
	public void afterThrowing() {
		System.out.println("afterThrowing ......");
	}
	

	@Around("pointCut()")
	public void around(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("around before......");
		try {
			jp.proceed();
		}catch (Exception e){
			System.out.println("aroundError:"+e.toString());
//			e.printStackTrace();
		}
		System.out.println("around after......");
	}
}
