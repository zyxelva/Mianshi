package com.zyx.funInterface;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyFunctionalInterface
{

	public static void main(String[] args)
	{
		ExecutorService pool=Executors.newFixedThreadPool(10);

		Runnable target=()->{
			 System.out.println("++++++++++++++");
		 };

		 pool.submit(target);
		 pool.submit(target);

		 pool.shutdown();

	}

}
