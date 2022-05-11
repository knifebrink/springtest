package com.spring.test.chapter6.injection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BeanPostProcessorExample implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(bean.getClass().getName().contains("com.spring.test.chapter6.injection"))
			log.warn("加载的bean，参数【"
				+ bean.getClass().getSimpleName()+ "】【" +beanName+"】 ");
		return bean;
	}

	

}
