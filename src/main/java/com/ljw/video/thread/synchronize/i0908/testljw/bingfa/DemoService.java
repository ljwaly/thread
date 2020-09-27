package com.ljw.video.thread.synchronize.i0908.testljw.bingfa;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auth lijinwu
 * @Date 2020年9月27日 下午6:19:14
 *
 */
@Slf4j(topic = "DemoService")
public class DemoService {
	
	private String response;
	
	Object lock = new Object();
	
	public String getResponse(Long mililms) {
		// TODO Auto-generated method stub
		
		long timepassed = 0;
		synchronized (lock) {
			while (response == null) {
				long timewait = mililms - timepassed;
				try {
					
					if (timewait <= 0) {
						log.debug("超时了：timepassed=" + timepassed);
						break;
					}
					
					lock.wait(timewait);
					timepassed += timewait;

					
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		return response;

	}
	
	public void setResponse(String response) {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.response = response;
		synchronized(lock) {
			lock.notifyAll();
		}
	}

}
