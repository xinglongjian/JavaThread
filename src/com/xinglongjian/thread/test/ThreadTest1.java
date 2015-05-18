package com.xinglongjian.thread.test;

public class ThreadTest1 {

	private class CorrectorThread extends Thread{

		private PoJo p;
		public CorrectorThread(PoJo p)
		{
			this.p=p;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true)
			{
				if(p.list.size()>0)
				{
					System.out.println("flag change!");
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadTest1 tt=new ThreadTest1();
		PoJo p=new PoJo();
        CorrectorThread c=tt.new CorrectorThread(p);
        c.start();
		
		//p.flag=false;
        
        p.list.add("sdfadsf");
	}

}
