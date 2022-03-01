package com.paceket.test;

public class PacketTestRun {
    /**
     * idea运行的主要指令
     * java "-javaagent:{idea_path}" -Dfile.encoding=UTF-8 -classpath "{system.jar;project.jar;classes}" {ClassName}
     * @param args
     * 几种方式运行
     * 1. 是idea的run，生成java命令，直接运行类java
     * 2. 是Artifacts方式，需要在idea中设置
     * 3. 直接使用mvn命令，或者使用idea提供的maven管理界面，设置相应的pom.xml，引入相关的maven插件等去进行打包
     * 最终jar包可直接用java -jar name.jar 运行
     *
     */
    public static void main(String[] args){
        System.out.println("这是一个运行测试");
    }
}
