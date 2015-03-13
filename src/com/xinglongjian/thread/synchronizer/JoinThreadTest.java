package com.xinglongjian.thread.synchronizer;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.xinglongjian.thread.synchronizer.CountDownLatchTest.Worker;

public class JoinThreadTest {
	static final SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	static final int N=5;
	
	class Worker implements Runnable
	{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			doWork();
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
		System.out.println("main thread start......"+format.format(new Date()));
		JoinThreadTest test=new JoinThreadTest();
		Thread[] ths=new Thread[N];
		for(int i=0;i<N;i++)
		{
			ths[i]=new Thread(test.new Worker());
			ths[i].start();
		}
		
		for(int i=0;i<N;i++)
		{
			ths[i].join();
		}
		
		System.out.println("main thread end......"+format.format(new Date()));
		
	}

}
