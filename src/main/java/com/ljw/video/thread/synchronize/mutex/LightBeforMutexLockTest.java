package com.ljw.video.thread.synchronize.mutex;

import org.openjdk.jol.info.ClassLayout;

import com.ljw.video.thread.synchronize.L;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auth lijinwu
 * @Date 2020年9月24日 下午5:33:32
 *
 * -XX:BiasedLockingStartupDelay=0
 */
@Slf4j(topic = "lightbeformutex")
public class LightBeforMutexLockTest {
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
		t1.join();
		
		t2.start();
		t2.join();
		log.debug(ClassLayout.parseInstance(l).toPrintable());
	}
}
