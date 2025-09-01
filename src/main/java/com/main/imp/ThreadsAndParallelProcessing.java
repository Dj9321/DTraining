package com.main.imp;

import com.main.classes.RunnableThread;

public class ThreadsAndParallelProcessing {

	public static void main(String[] args) throws InterruptedException {
		ThreadsAndParallelProcessing threadClass = new ThreadsAndParallelProcessing();
		threadClass.runnableThread();
	}

	public void runnableThread() throws InterruptedException {
		// Calling run() method directly doesn't run in a new thread, instead it runs in
		// the same thread in which it was invoked.
		RunnableThread nr = new RunnableThread();
		Thread t = new Thread(nr);
		Thread t1 = new Thread(nr);
		Thread t2 = new Thread(nr);
//		t.setName("Dj Thread");
		System.out.println(t.getName());
		System.out.println(Thread.currentThread().getName());
		t.start();
		Thread.sleep(5 * 1000);
		t1.start();
//		t1.interrupt();
//	    nr.waitForReady();
//	    Thread.sleep(5 * 1000);
	    nr.setReady();
		synchronized (nr) {
			System.out.println("In synchronized block");
//			Thread.sleep(5 * 1000);
//			nr.wait();
		}
//		t1.wait();
		
//		Thread.sleep(5 * 1000); // sleep for 5 seconds
		t2.start();
//		t2.interrupt();
		System.out.println(t.getName()); // if no name it will be Thread-0 , 1, 2, etc
		System.out.println(t.isAlive()); // its already done and dead
		System.out.println(t.getState()); // Terminated
		t.run(); // again runs the method // however we shouldn't do this
		// t.start(); // Illegal Thread State Exception if you start again
		// you can call getName() as well to get the name of the thread.
		System.out.println(Thread.currentThread().getName()); // main thread
		System.out.println(Thread.currentThread().getState()); // Runnable (main thread) // TIMED_WAITING
	}
}
