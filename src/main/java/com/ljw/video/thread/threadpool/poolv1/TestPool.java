package com.ljw.video.thread.threadpool.poolv1;


/**
 * @Author 钢牌讲师--子路
 **/
public class TestPool {
    public static void main(String[] args) {

       EnjoyThreadPool enjoyThreadPool = new EnjoyThreadPool(2);


        for (int i = 1; i <6 ; i++) {
            enjoyThreadPool.submitTask(new CustomTask("task"+i));
        }


    }
}
