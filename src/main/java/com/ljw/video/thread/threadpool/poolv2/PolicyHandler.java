package com.ljw.video.thread.threadpool.poolv2;

/**
 * @Author 钢牌讲师--子路
 **/
public interface PolicyHandler {
    public void handler(EnjoyQueue queue,CustomTask task);
}
