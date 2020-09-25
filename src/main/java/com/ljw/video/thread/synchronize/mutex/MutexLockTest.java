package com.ljw.video.thread.synchronize.mutex;

import org.openjdk.jol.info.ClassLayout;

import com.ljw.video.thread.synchronize.L;

import lombok.extern.slf4j.Slf4j;

/**
 * 重量锁，互斥锁
 * 
 * 服务启动，直接给对象加了偏向锁，
 * 当启动线程t1和t2时候，直接发生了重度竞争，变成了重量锁
 * 
 * -XX:BiasedLockingStartupDelay=0
 * 
 * @Auth lijinwu
 * @Date 2020年9月23日 下午2:12:41
 *
 */
@Slf4j(topic = "mutex")
public class MutexLockTest {
	static L l = new L();
	static Thread t1;
	static Thread t2;
	
	static void gg() {
		
		synchronized (l) {
			log.debug(Thread.currentThread().getName() + ":id=" + Thread.currentThread().getId());
			log.debug(ClassLayout.parseInstance(l).toPrintable());
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		log.debug(ClassLayout.parseInstance(l).toPrintable());
		
		
		t1 = new Thread(()->{
			gg();
		});  
		t2 = new Thread(()->{
			gg();
		}); 
		t1.setName("t1");
		t2.setName("t2");
		
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		log.debug(ClassLayout.parseInstance(l).toPrintable());
		Thread.sleep(2000);
		log.debug(ClassLayout.parseInstance(l).toPrintable());
	}
	
	
	
}
