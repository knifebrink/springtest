package com.spring.test.chapter6.redis;

/**
 * @author brink
 * 2021/11/17 17:56
 */
public class IncreaseThread extends Thread{
    public  static int sum = 0;
    public IncreaseThread(){
        super("IncreaseThread");
    }
    @Override
    public void run() {
        needSync();


    }

    public void needSync(){
        for (int i = 0; i < 1000; i++) {
            sum++;
        }
    }
}
