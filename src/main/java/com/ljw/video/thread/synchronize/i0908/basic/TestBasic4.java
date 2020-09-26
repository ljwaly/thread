package com.ljw.video.thread.synchronize.i0908.basic;

import lombok.extern.slf4j.Slf4j;

/**
 * 两个对象锁定的互不相同，不影响服务进行
 * 
 * @author Administrator
 *
 */
@Slf4j(topic = "enjoy")
public class TestBasic4 {
    public static void main(String[] args) {
        BasicLock1 basicLock1 = new BasicLock1();
       
        //x()方法相当于锁定的是clazz
        Class<BasicLock1> clazz = BasicLock1.class;

        new Thread(()->{
            log.debug("start");

            basicLock1.x();
        },"t1").start();

        
        //y()方法相当于锁定的是basicLock1对象
        new Thread(()->{
            log.debug("start");

            basicLock1.y();
        },"t2").start();


    }
}
