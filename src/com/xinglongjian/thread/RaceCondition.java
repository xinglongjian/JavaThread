package com.xinglongjian.thread;

public class RaceCondition {
	private static volatile boolean done;//must define volatile 
	
	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				int i=0;
				while(!done){
					i++;
				}
				System.out.println("Done!");
				
			}}).start();
		
		
		System.out.println("OS:"+System.getProperty("os.name"));
		Thread.sleep(2000);
		done=true;
		System.out.println("flag done set to true");
	}

}
