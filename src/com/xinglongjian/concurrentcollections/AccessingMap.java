package com.xinglongjian.concurrentcollections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AccessingMap {

	private static void useMap(final Map<String,Integer> scores)
	{
		scores.put("Fred", 10);
		scores.put("Sara", 12);
		
		try {
			for(final String key:scores.keySet()){
				System.out.println(key+" score "+scores.get(key));
				scores.put("Joe", 14);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Failed:"+e);
		}
		
		System.out.println("Number of elements in the map:"+scores.keySet().size());
		
	}
	
	public static void main(String[] args) {
		System.out.println("Using plain vanilla hashmap");
		useMap(new HashMap<String, Integer>());
		
		System.out.println("Using Synchronized HashMap");
		useMap(Collections.synchronizedMap(new HashMap<String, Integer>()));
		
		System.out.println("Using concurrent hashmap");
		useMap(new ConcurrentHashMap<String, Integer>());
	}
	
}
