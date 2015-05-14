package com.xinglongjian.thread.test;
/**
 * 该测试验证共享数据的可见性，修改sleep时间,打印结果不同
 * 
 * @author zwl
 *
 */
public class BadThread {

	static String message;
	
	private static class CorrectorThread extends Thread{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				sleep(4000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			message="Mares do eat oats";
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		CorrectorThread t=new CorrectorThread();
		t.start();
		message="Mares do not eat oats";
		Thread.sleep(2000);
		t.join();//等待t完成后，再执行后续语句
		System.out.println(message);
	}

}
