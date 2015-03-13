package com.xinglongjian.thread.synchronizer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
	static final SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	static final int N=5;
	
	class Worker implements Runnable
	{
        private final CountDownLatch startSign;
        private final CountDownLatch doneSign;
        
        Worker(CountDownLatch startSign,CountDownLatch doneSign)
        {
        	this.startSign=startSign;
        	this.doneSign=doneSign;
        }
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				startSign.await();
				doWork();
				synchronized(doneSign)
				{
					doneSign.countDown();
					System.out.println(Thread.currentThread().getName()+":"+doneSign.getCount());
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				return;
			}
		}
		
		private void doWork()
		{
			System.out.println(Thread.currentThread().getName()+"doWork:"+format.format(new Date()));
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"doWork:"+format.format(new Date()));
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException {
	  	CountDownLatchTest test=new CountDownLatchTest();
	  	
		final CountDownLatch startSign=new CountDownLatch(1);
	  	final CountDownLatch doneSign=new CountDownLatch(N);
	  	
	  	for(int i=0;i<N;i++)
	  		new Thread(test.new Worker(startSign,doneSign)).start();
	  	
	  	doSomethingElse1();
	  	startSign.countDown();
	  	doSomethingElse2();
	  	synchronized (doneSign) {
	  		System.out.println("doneSign wait.");
	  		doneSign.wait();
		}
	  	System.out.println("Over:"+format.format(new Date()));
	}

	
	private static void doSomethingElse1()
	{
         System.out.println("doSomethingElse:"+format.format(new Date()));
         try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	private static void doSomethingElse2()
	{
         System.out.println("doSomethingElse:"+format.format(new Date()));
	}
}
