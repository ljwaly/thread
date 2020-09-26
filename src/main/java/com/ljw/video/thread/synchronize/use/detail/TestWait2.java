package com.ljw.video.thread.synchronize.use.detail;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "enjoy")
public class TestWait2 {

    static final Object pc = new Object();
    static boolean isPrettyGril = false; // 女人

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (pc) {
                log.debug("有没有女人[{}]", isPrettyGril);
                if (!isPrettyGril) {
                    log.debug("没有女人！等女人");
                    try {
                        pc.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("老板喊我了 有没有女人？[{}]", isPrettyGril);
                if (isPrettyGril) {
                    log.debug("---男女搭配干活不累；啪啪啪写完了代码");
                }else{
                    log.debug("下班回家");
                }
            }
        }, "jack").start();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                synchronized (pc) {
                    log.debug("我们10个屌丝工作了");
                }
            }, "其它人").start();
        }

       Thread.sleep(1000);
        new Thread(() -> {
            synchronized (pc) {
                isPrettyGril = true;
                log.debug("桥本有菜来了");
                pc.notify();
            }
        }, "boss").start();
    }




}
