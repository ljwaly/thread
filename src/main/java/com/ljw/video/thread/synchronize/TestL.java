package com.ljw.video.thread.synchronize;

import org.openjdk.jol.info.ClassLayout;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auth lijinwu
 * @Date 2020年9月23日 下午2:12:41
 *
 */
@Slf4j(topic = "e")
public class TestL {
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
		System.out.println(Integer.toHexString(l.hashCode()));
		log.debug(ClassLayout.parseInstance(l).toPrintable());
//		synchronized (l) {
//			log.debug(ClassLayout.parseInstance(l).toPrintable());
//		}
//		Thread.sleep(5000);
		
		t1 = new Thread(()->{
			gg();
		});  
		t2 = new Thread(()->{
			gg();
		}); 
		
		t1.setName("t1");
		t2.setName("t2");
		
		t1.start();
		t1.join();//阻塞，等待线程t1执行完毕
		
		
//		t2.start();
		
		
	}
	
	
	
}
