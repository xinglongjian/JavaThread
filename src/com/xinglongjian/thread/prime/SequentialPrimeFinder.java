package com.xinglongjian.thread.prime;

public class SequentialPrimeFinder extends AbstractPrimeFInder {

	@Override
	public int countPrimes(final int number) {
		// TODO Auto-generated method stub
		return countPrimesInRange(1, number);
	}

	public static void main(String[] args) {
		new SequentialPrimeFinder().timeAndCompute(Integer.parseInt(args[0]));
	}
	
}
