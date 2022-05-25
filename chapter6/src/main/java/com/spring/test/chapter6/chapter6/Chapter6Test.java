package com.spring.test.chapter6.chapter6;

import com.zaxxer.hikari.pool.HikariPool;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brink
 * 2021/7/6 14:26
 */
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest(classes = Chapter6Application.class)
public class Chapter6Test {
    @Autowired
    UserService userService;
    @Test
    public void test1(){
        User user = new User();
        user.setCount(100);
        user.setUserName("第二个");
        userService.insertUser(user);
    }

    /**
     * 进行事务线程测试
     * 默认配置下
     * 不使用事务@Transactional，100次最终值为16
     * 使用事务，隔离级别：读写提交(READ_COMMITTED)，最终值为46
     * READ_UNCOMMITTED 46
     * REPEATABLE_READ 53
     * SERIALIZABLE 69  但是会事务报错32次 共101次，实际上是不会有脏读脏写问题，但是会有报错的问题
     * 虽然前三种结果差不多，但是是不同类型的逻辑导致的
     * 分别会导致，脏读、不可重复度、幻读
     * 所以都解决不了
     */
    int kkkk = 0;
    boolean showE = true;
//    @Autowired
//    HikariPool hikariPool;
    @Test
    public void testMore(){
//        userService.reduceCount();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    userService.reduceCount2();
                }catch (Exception e){kkkk++;
//                    e.printStackTrace();
                    if(showE){
                        e.printStackTrace();
                        showE = false;
                    }
                }
            }
        };
        int i = 1000;
        int k = 0;
        while (i-->0){

            Thread thread = new Thread(runnable);
            thread.setName("reduceCount");
            thread.start();
            k++;
        }

        System.out.println("------------:"+k+" "+kkkk+" "+        Thread.activeCount());
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Thread thread : Thread.getAllStackTraces().keySet()) {
            if(thread.getName().endsWith("reduceCount"))
                System.out.println(thread.getName());
        }
        System.out.println("------------:"+k+" "+kkkk+" "+        Thread.activeCount() );
    }

    /**
     * 不能同步的原因，好像不管哪一个，都无法解决这个问题
     * 即使是采用内存赋值，也是不能同步的
     * 1. 在未完全插入的时候，已经读取了原有的值
     * 即 T1    a  b  c  同时读取了i=2，同时插入了i=1 。 这就是第二类丢失更新
     *
     */
    @Autowired
    UserBatch userBatch;
    /**
     * 传播行为测试
     * 主要是指，一个事务调用了另一个事务方法
     * 一般可以有三种常用
     * 当前事务执行，意味着回滚会大事务回滚
     * 新事务执行，每次都会新建事务执行子方法
     * 只回滚：使用保存点的方式，只回滚子方法
     */
    @Test
    public void testPropagation(){
        List<User> userList = new ArrayList<>();
        userList.add(new User("批量1",1));
        userList.add(new User("批量2",2));
        userList.add(new User("批量3",3));
        int count = userBatch.insertUsers(userList);
        System.out.println("批量加入："+count);
        // 测试提示：将日志调到debug，看日志
    }

    @Value("${fch.ddd}")
    private String aaa;
    @Test
    public void testInclue(){
        log.info("这是一个测试值：{}",aaa);
        log.info("这是一个查询：{}",userService.selectUser());
    }
}
