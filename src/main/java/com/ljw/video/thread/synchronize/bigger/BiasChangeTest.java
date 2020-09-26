package com.ljw.video.thread.synchronize.bigger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

import org.openjdk.jol.info.ClassLayout;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * 使用LockSupport才能
 * 
 * -XX:BiasedLockingStartupDelay=0
 * 
 * @author Administrator
 *
 */
@Slf4j(topic = "biaschange")
public class BiasChangeTest {

	
	static int times = 50;
	
	static Thread t1;
	static Thread t2;
	static Thread t3;
	
	public static void main(String[] args) throws Exception {
		
		
		
		
		List<A> list = new ArrayList<>();
		
			
			
		t1 = new Thread(() -> {
			for (int i = 1; i < times + 1; i++) {
				A a = new A(); 
				list.add(a);
				
				log.debug("t1=" +i + " " + printLock(ClassLayout.parseInstance(a).toPrintable()));
				synchronized (a) {
					log.debug("t1=" +i + " " + printLock(ClassLayout.parseInstance(a).toPrintable()));
				}
				log.debug("t1=" +i + " " + printLock(ClassLayout.parseInstance(a).toPrintable()));
			}
			log.debug("==================================================================================");
			LockSupport.unpark(t2);
			
		});
		
		t2 = new Thread(() -> {
			LockSupport.park();
			for (int i = 1; i < list.size() +1; i++) {
				A a = list.get(i-1);
				
				log.debug("t2=" +i + " " + printLock(ClassLayout.parseInstance(a).toPrintable()));
				synchronized (a) {
					log.debug("t2=" +i + " " + printLock(ClassLayout.parseInstance(a).toPrintable()));
				}
				log.debug("t2=" +i + " " + printLock(ClassLayout.parseInstance(a).toPrintable()));
			}
			log.debug("==================================================================================");
			LockSupport.unpark(t3);
			
		});
		
		t3 = new Thread(() -> {
			LockSupport.park();
			for (int i = 1; i < list.size() +1; i++) {
				A a = list.get(i-1);
				
				log.debug("t3=" +i + " " + printLock(ClassLayout.parseInstance(a).toPrintable()));
				synchronized (a) {
					log.debug("t3=" +i + " " + printLock(ClassLayout.parseInstance(a).toPrintable()));
				}
				log.debug("t3=" +i + " " + printLock(ClassLayout.parseInstance(a).toPrintable()));
			}
			
		});
			
		t1.start();	
		
		t2.start();
		t3.start();
		t3.join();
		log.debug("==================================================================================");
		log.debug("te=e"  + " " + printLock(ClassLayout.parseInstance(new A()).toPrintable()));
	}
		
		
		
	
	
	
	
	
	
	
	public static String printLock(String abc) {
		String[] split = abc.split("\n");
		for (int i = 0; i < split.length; i++) {
			String trim = split[i].trim();
			if (trim.startsWith("0")) {
				String substring = trim.substring(0, trim.lastIndexOf("("));
				return substring.substring(substring.lastIndexOf("("), substring.length()).substring(1, 9);
			}
		}
		return abc;
			
	}
}
