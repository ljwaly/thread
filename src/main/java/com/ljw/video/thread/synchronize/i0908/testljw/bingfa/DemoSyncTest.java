package com.ljw.video.thread.synchronize.i0908.testljw.bingfa;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auth lijinwu
 * @Date 2020年9月27日 下午6:18:25
 *
 */
@Slf4j(topic = "DemoSyncTest")
public class DemoSyncTest {
	public static void main(String[] args) {
		DemoService dService = new DemoService();
		
		
		Thread t1  = new Thread(()->{
			
			log.debug(dService.getResponse(3000L));;
			
		}, "t1");
		
		Thread t2  = new Thread(()->{
			
			dService.setResponse("abc");;
			
		}, "t2");
		
		t1.start();
		t2.start();
		
		
	}

}
