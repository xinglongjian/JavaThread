package com.xinglongjian.thread.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock解决死锁问题，重入锁ReentrantLock
 *
 * @author zwl
 *
 */
public class Safelock {
	
	static class Friend{
		private final String name;
		private final Lock lock=new ReentrantLock();
		
		public Friend(String name){
			this.name=name;
		}
		
		public String getName(){
			return this.name;
		}
		
		public boolean impendingBow(Friend bower){
			boolean myLock=false;
			boolean yourLock=false;
			try {
				myLock=lock.tryLock();
				yourLock=bower.lock.tryLock();
				System.out.println("impendingBow,myLock="+myLock+",yourLock="+yourLock);
			} finally{
				if(!(myLock&&yourLock)){
					if(myLock){
						lock.unlock();
					}
					if(yourLock){
						bower.lock.unlock();
					}
				}
			}
			return myLock&&yourLock;
		}
		
		public void bow(Friend bower){
			if(impendingBow(bower)){
				try {
					System.out.format("%s: %s has"
	                        + " bowed to me!%n", 
	                        this.name, bower.getName());
					bower.bowBack(bower);
					
				} finally{
					lock.unlock();
					bower.lock.unlock();
				}
			}
			else{
				 System.out.format("%s: %s started"
		                    + " to bow to me, but saw that"
		                    + " I was already bowing to"
		                    + " him.%n",
		                    this.name, bower.getName());
			}
		}
		
		public void bowBack(Friend bower){
			System.out.format("%s: %s has" +
	                " bowed back to me!%n",
	                this.name, bower.getName());
		}
		
		
	}
	
	static class BowLoop implements Runnable{

		private Friend bower;
		private Friend bowee;
		
		public BowLoop(Friend bower,Friend bowee){
			this.bowee=bowee;
			this.bower=bower;
		}
		
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Random random=new Random();
			for(int i=0;i<5;i++){
				try {
					System.out.println("time:"+i);
					Thread.sleep(random.nextInt(20));
				} catch (Exception e) {
					// TODO: handle exception
				}
				bowee.bow(bower);
			}
		}
		
	}
	
	public static void main(String[] args) {
		final Friend alphonse=new Friend("Alphonse");
		final Friend gaston=new Friend("Gaston");
		
		new Thread(new BowLoop(alphonse, gaston)).start();
		new Thread(new BowLoop(gaston, alphonse)).start();
	}

}
