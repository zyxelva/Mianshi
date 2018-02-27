package com.zyx;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class DrawTest
{

	public static void main(String[] args)
	{
//		Account2 acct=new Account2("123456", 0);
//		new DrawThread("取钱者",acct,800).start();
//		new DepositThread("甲", acct, 800).start();
//		new DepositThread("乙", acct, 800).start();
//		new DepositThread("丁", acct, 800).start();

		ExecutorService pool=Executors.newFixedThreadPool(6);
		Runnable target=()->{
			for(int i=0;i<100;i++){
				System.out.println(Thread.currentThread().getName()+"'s value="+i);
			}
		};
		pool.submit(target);
		pool.submit(target);
		pool.shutdown();

		HashMap map=new HashMap();
		//map.p
//		ThreadPool t=ThreadPool.get
	}

}
