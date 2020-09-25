package com.ljw.video.thread.synchronize;


import sun.misc.Unsafe;

/**
 * @Auth lijinwu
 * @Date 2020年9月23日 下午1:58:39
 *
 */
public class MyCASLock {
	
	private volatile int status = 0;

	private static final Unsafe unsafe = Unsafe.getUnsafe();
	
}
