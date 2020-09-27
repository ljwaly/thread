package com.ljw.video.thread.synchronize.i0908.testljw;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * @Auth lijinwu
 * @Date 2020年9月27日 上午10:59:11
 *
 */
@Slf4j(topic = "DemoCollection")
public class DemoCollection {

	List<String> list = new ArrayList<>();
	
	public  void add(String abc) {
		list.add(abc);
	}
	
	public Integer size() {
		return list.size();
	}
	
	public static void main(String[] args) {
		DemoCollection dCollection = new DemoCollection();
		
		Object object = new Object();
		Object object2 = new Object();
		CountDownLatch latch = new CountDownLatch(5);
		
		new Thread(()->{
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			
			log.debug("当前容器大小超过5,线程终止:" + Thread.currentThread().getName());
			
		}, "t2").start() ;
		
		
		
		
		
		new Thread(()->{
			
			
			  
			for (int i = 0; i < 10; i++) {
				
				dCollection.add(i+"");
				log.debug("当前容器大小：" + dCollection.size());
				
				latch.countDown();
				
			}
			
		}, "t1").start(); 
		
		
		
		
		
		
	}
	
}
