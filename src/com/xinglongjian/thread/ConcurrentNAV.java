package com.xinglongjian.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
/**
 * this class aim to study the use of Callable\Executors
 * @author root
 *
 */
public class ConcurrentNAV {
	
	public static double computeNetAssetValue(final Map<String,Integer> stocks) throws InterruptedException, ExecutionException
	{
		System.out.println("Pool size is "+5);
		final List<Callable<Double>> partitions=new ArrayList<Callable<Double>>();//tasks
		
		for(final String tiker:stocks.keySet())
		{
			partitions.add(new Callable<Double>() {

				@Override
				public Double call() throws Exception {
					// TODO Auto-generated method stub
					return stocks.get(tiker)*0.11;
				}
				
			});
		}
		System.out.println("partitions size="+partitions.size());
		final ExecutorService executorPool=Executors.newFixedThreadPool(5);
		final List<Future<Double>> values=executorPool.invokeAll(partitions,1000,TimeUnit.SECONDS);
		
		double netValues=0.0;
		for(final Future<Double> v:values)
		{
			netValues+=v.get();
		}
		executorPool.shutdown();
		return netValues;
	}

	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		Map<String, Integer> maps=new HashMap<String, Integer>();
		for(int i=0;i<10000;i++)
		{
			maps.put(i+"", i);
		}
		
		System.out.println(computeNetAssetValue(maps));
		
	}
}
