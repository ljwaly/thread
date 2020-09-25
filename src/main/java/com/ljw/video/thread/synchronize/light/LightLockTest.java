package com.ljw.video.thread.synchronize.light;

import org.openjdk.jol.info.ClassLayout;

import com.ljw.video.thread.synchronize.L;

import lombok.extern.slf4j.Slf4j;

/**
 * 无锁状态下（偏向锁延迟加载）
 * 线程启动
 * 单线程情况下，加锁前、后，都是无锁是001
 * 
 * 加锁中：直接是轻量锁000
 * 
 * 开启偏向锁延迟加载或者计算hashcode都会使对象处于无锁状态
 * -XX:BiasedLockingStartupDelay=4
 * log.debug(Integer.toHexString(l.hashCode()));
 * 
 * 
 * @Auth lijinwu
 * @Date 2020年9月23日 下午2:12:41
 *
 */
@Slf4j(topic = "light")
public class LightLockTest {
	static L l = new L();
	static Thread t1;
	
	static void gg() {
		synchronized (l) {
			log.debug(Thread.currentThread().getName() + ":id=" + Thread.currentThread().getId());
			log.debug(ClassLayout.parseInstance(l).toPrintable());
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		log.debug(Integer.toHexString(l.hashCode()));
		log.debug(ClassLayout.parseInstance(l).toPrintable());

		t1 = new Thread(()->{
			gg();
		});  
		
		t1.setName("t1");
		t1.start();
		t1.join();//阻塞，等待线程t1执行完毕
		
		log.debug(ClassLayout.parseInstance(l).toPrintable());
		

		
		
	}
	
	
	
}
