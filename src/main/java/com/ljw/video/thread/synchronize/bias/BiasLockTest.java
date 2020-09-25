package com.ljw.video.thread.synchronize.bias;

import org.openjdk.jol.info.ClassLayout;

import com.ljw.video.thread.synchronize.L;

import lombok.extern.slf4j.Slf4j;

/**
 * 这里的前提是每个线程执行完，下个线程进行
 * 
 * 互斥偏向锁
 * 1.单线程情况下，加锁前、中、后，都是偏向锁（101）
 * 2.随着线程增多，会移除偏向锁，并向轻量锁进行膨胀
 * 
 * -XX:BiasedLockingStartupDelay=0
 * 
 * @Auth lijinwu
 * @Date 2020年9月23日 下午2:12:41
 *
 */
@Slf4j(topic = "bias")
public class BiasLockTest {
	
	
	static void gg(L l) {
		synchronized (l) {
			log.debug(ClassLayout.parseInstance(l).toPrintable());
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		L l = new L();
		log.debug(ClassLayout.parseInstance(l).toPrintable());

		Thread t1 = new Thread(()->{
			gg(l);
		});  
		
		t1.setName("t1");
		t1.start();
		t1.join();//阻塞，等待线程t1执行完毕
		log.debug(ClassLayout.parseInstance(l).toPrintable());
		
		Thread t2 = new Thread(()->{
			gg(l);
		});  
		
		t2.setName("t2");
		t2.start();
		t2.join();//阻塞，等待线程t1执行完毕
		
		log.debug(ClassLayout.parseInstance(l).toPrintable());
		
		Thread t3 = new Thread(()->{
			gg(l);
		});  
		
		t3.setName("t3");
		t3.start();
		t3.join();//阻塞，等待线程t1执行完毕
		
		log.debug(ClassLayout.parseInstance(l).toPrintable());
		
		Thread t4 = new Thread(()->{
			gg(l);
		});  
		
		t4.setName("t4");
		t4.start();
		t4.join();//阻塞，等待线程t1执行完毕
		
		log.debug(ClassLayout.parseInstance(l).toPrintable());
	}
	
	
	
}
