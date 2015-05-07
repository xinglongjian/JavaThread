package com.xinglongjian.thread.prime;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinFileSize {
	
	private final static ForkJoinPool forkjoinPool=new ForkJoinPool();
	
	
	private static class FileSizeFinder extends RecursiveTask<Long>{

		File file=null;
		
		public FileSizeFinder(final File theFile){
			this.file=theFile;
		}
		
		@Override
		protected Long compute() {
			// TODO Auto-generated method stub
			long size=0;
			if(file.isFile()){
				size=file.length();
			}
			else{
				File[] children=file.listFiles();
				if(children!=null){
					List<ForkJoinTask<Long>> tasks=new ArrayList<ForkJoinTask<Long>>();
					for(File child:children){
						if(child.isFile())
							size+=child.length();
						else
							tasks.add(new FileSizeFinder(child));
					}
					
					for(ForkJoinTask<Long> task:invokeAll(tasks)){
						size+=task.join();
					}
				}
			}
			
			
			return size;
		}
		
		public static void main(String[] args) {
			long start=System.nanoTime();
			long total=forkjoinPool.invoke(new FileSizeFinder(new File(args[0])));
			long end=System.nanoTime();
			System.out.println("Total size="+total);
			System.out.println("Time taken:"+(end-start)/1.0e9);
		}
		
		
	}
	

}
