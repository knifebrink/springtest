package com.spring.test.springtest.lifecycle;

import com.spring.test.springtest.lifecycle.LifeAppConfig;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import javax.annotation.PostConstruct;
/**
 * @author brink
 * 2021/9/8 9:05
 * 生命周期测试，bean经过
 * 资源定位、bean定义、发布定义到容器、实例化、依赖注入
 * 在依赖注入后，可对bean进行一系列接口自定义初始化，可以去{@link BeanFactory}看所有的bean生命周期接口
 */
public class LifeCycleTest {

    /**
     * 生命周期测试，以{@link BussinessPerson}作为测试
     * 1. 实例化单例bean(包括bean的构造器)
     * 2. 配置属性以及有依赖的bean进行依赖bean实例化
     * 3. ->如果bean有实现{@link BeanNameAware}方法，则调用其方法
     * 4. ->如果bean有实现{@link BeanFactoryAware}方法，
     * 5. ->如果bean有实现其他*Aware方法，也调用其方法
     * 6. 如果容器有注入{@link BeanPostProcessor}，则调前置处理postProcessBeforeInitialization()方法
     * 6.5 如果有注解@PostConstruct，则调用其注解的方法。
     * 7. 如果bean有实现{@link InitializingBean}，则调用其afterPropertiesSet()方法
     * 8. 如果容器有注入{@link BeanPostProcessor},则调用后置处理postProcessAfterInitialization()方法
     * 9. 如果bean有实现入{@link DisposableBean},则执行其销毁方法
     *
     * 比较常用的@PostConstruct({@link PostConstruct})和@PreDestroy不在生命周期内，但大概执行是在6和7之间，9之前。
     * https://www.cnblogs.com/zrtqsk/p/3735273.html
     * https://blog.csdn.net/qq360694660/article/details/82877222 @PostConstruct注解
     */
    public static void main(String[] args) {
//        testOther();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LifeAppConfig.class);
        context.close();
    }

    private static void testOther() {
        String in = convertCardId("33 36 34 36 35 39 39 31 36 32 0D0A".replace(" ", ""));
        System.out.println(in);
    }

    // 公司的测试
    public static byte[] hexToByteArray(String inHex) {
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1) {
            //奇数
            hexlen++;
            result = new byte[(hexlen / 2)];
            inHex = "0" + inHex;
        } else {
            //偶数
            result = new byte[(hexlen / 2)];
        }
        int j = 0;
        for (int i = 0; i < hexlen; i += 2) {
            result[j] = (byte) Integer.parseInt(inHex.substring(i, i + 2), 16);
            j++;
        }
        return result;
    }

    // 33 36 34 36 35 39 39 31 36 32 0D0A to FAAF5AD9
    public static String convertCardId(String input) {
        String result = input;
        if (!input.endsWith("0D0A")) {
            return result;
        }
        input = input.replace("0D0A","");
        byte[] bytes = hexToByteArray(input);
        StringBuilder intStrBul = new StringBuilder();
        for (byte b : bytes) {
            char c = (char) b;
            if(!(c>='0'&&c<='9')){
                System.out.println(b);
                System.out.println(c);
                return result;
            }
            intStrBul.append(c);
        }
        long cardNum = Long.parseLong(intStrBul.toString());
        String card = Long.toHexString(cardNum);
        while(card.length()<8){
            card = "0"+card;
        }
        StringBuilder output = new StringBuilder();
        for(int i=card.length();i>=2;i-=2){
            output.append(card.substring(i - 2, i));
        }
        return output.toString().toUpperCase();
    }
}
