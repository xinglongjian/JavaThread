package com.xinglongjian.thread;

public class MyThread {
	
	private static class MyDaemonThread extends Thread
	{
		public MyDaemonThread()
		{
			setDaemon(true);
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ee) {
				// TODO: handle exception
			}
		}
		
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread thread=new MyDaemonThread();
		thread.start();
		thread.join();
		System.out.println(thread.isAlive());
		
		String key="";
		switch (key) {
		case "dd":
			break;

		default:
			break;
		}
	}

}
