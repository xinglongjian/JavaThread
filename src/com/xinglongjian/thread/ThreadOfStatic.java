package com.xinglongjian.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ThreadOfStatic {

	private List<String> lists=Collections.synchronizedList(new ArrayList<String>());
	private static int count=0;
	public static void CovertArray(int [] array)
	{
		int first=array[0];
		for(int i=1;i<array.length;i++)
		{
			array[i-1]=array[i];
		}
		array[array.length-1]=first;
		Thread currThread=Thread.currentThread();
		System.out.println(currThread.getName());
		for(int j=0;j<array.length;j++)
		{
			System.out.print(array[j]+" ");
		}
		System.out.println();
	}
	
	public static void updateCount()
	{
		count++;
	}
	
	public static int getCount()
	{
		return count;
	}
	
	
	
	public static void main(String[] args) {
		
		//启动10个线程
		for(int i=0;i<10;i++)
		{
			System.out.println("第"+i+"个线程启动......");
			Random random=new Random();
			int[] temp=new int[10];
			for(int j=0;j<10;j++)
			{
				temp[j]=random.nextInt((j+1)*10);
			}
			
			for(int j=0;j<temp.length;j++)
			{
				System.out.print(temp[j]+" ");
			}
			System.out.println();
			TestThread tt=new TestThread(temp,i);
			tt.start();
		}
	}
}

class TestThread extends Thread
{
	int[] array=null;
	
	public TestThread(int[] array,int i) {
		this.array=array;
		setName("TestThread-"+i);
	}
	
	@Override
	public void run() {
		ThreadOfStatic.CovertArray(array);
	}
}