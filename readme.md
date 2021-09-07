### 一个测试spring全家的项目

* spring、springMVC、SpringBoot是三个东西
* spring主要就是IOC容器和AOP
* springMVC是java web开发的一个框架，依赖或基于spring
* SpringBoot有点类似脚手架，继承和自动配置spring和springMVC，以及更方便继承其他框架



#### 模块

分模块最主要的是，依赖不同，配置环境不同。

1. chapter3 深入浅出springboot的章节3测试
2. chapter6 类比1
3. javaservlet 原生javaservlet的测试，方便理解springMVC
4. sourcetest springboot包括spring和springMVC的源码测试
5. springmvc springMVC测试及原理
6. stest spring的其他测试

#### spring

1. 主要是IOC(依赖注入)和AOP
2. IOC主要是ApplicationContext容器，依赖beanFactory接口，通过反射构造对象，并把对象组合起来。（实例化、组合、装配）
3. 