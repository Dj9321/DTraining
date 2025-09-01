package com.main.classes;

public class RunnableThread implements Runnable {

	@Override
	public void run() {
		// Using Thread.sleep() method for Custom thread
		// If you call sleep() method in run() for Custom thread, the main thread will
		// resume with remaining code.
		System.out.println("Runnable thread running ...");
		try {
			Thread.sleep(5 * 1000);
			waitForReady();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Runnable thread almost done ...");
//		for (int i = 0; i < 10000; i++) {
//			System.out.print(" " + i);
//		}
	}

	private boolean ready = false;

	public synchronized void waitForReady() throws InterruptedException {
		while (!ready) {
			System.out.println("Waiting");
			wait(); // Releases lock and waits until notified
		}
		System.out.println("Resource is ready, proceeding...");
	}

	public synchronized void setReady() {
		ready = true;
		System.out.println("Back to ready");
		notify(); // Notifies waiting thread to wake up
	}

}
