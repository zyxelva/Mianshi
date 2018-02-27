package com.zyx.sortAlgorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.sun.jmx.remote.internal.ArrayQueue;

public class TestClass
{

	public static void main(String[] args)
	{
		Queue<String> q=new LinkedList();

		q.add("dddd");
		q.add("eee");

		System.out.println("print queue: ");
		for(String str:q){
			System.out.println(str);
		}


		Stack<String> s=new Stack<String>();
		ArrayQueue q1=new ArrayQueue(10);

		//ArrayBlockingQueue<E>
//		LinkedBlockingQueue<E>
//		linkedBlockingQueue/
	}

}
