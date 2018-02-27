/**
 * 2018年2月19日
 * DoubleStackForQueue
 * zyxelva
 */
package com.zyx.mianshi;

import java.util.Stack;

import com.zyx.myQueue.IMyQueue;

/**
 * @author zyxelva
 * @param <T>
 *
 */
public class DoubleStackForQueue<T> implements IMyQueue<T>
{
	private Stack<T> stack1=new Stack<T>();
	private Stack<T> stack2=new Stack<T>();

//	DoubleStackForQueue(int initSize){
//		stack1=super(initSize);
//	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		DoubleStackForQueue<Integer> mq=new DoubleStackForQueue<Integer>();
		for (int i = 0; i < 5; i++)
		{
			mq.add(i);
		}
		mq.showQueue();
		System.out.println("获取元素："+mq.get(0));
		mq.add(100);
		mq.showQueue();
		System.out.println("队列元素个数："+mq.size());
		//System.out.println("获取元素："+mq.get(4));

		System.out.println("+++++++++++++删除一个元素++++++++++++++");
		mq.remove(0);
		mq.showQueue();
		System.out.println("队列元素个数："+mq.size());
	}



	@Override
	public int size()
	{
		if(stack1.isEmpty())
		{
			System.out.println("队列为空！");
			return -1;
		}

		return stack1.size();
	}



	@Override
	public T get(int i)
	{
		if(i!=0){
			throw new IndexOutOfBoundsException("只能获取队列的头元素！");
		}

		while(!stack1.isEmpty()){
			stack2.push(stack1.pop());
		}
		if(stack2.isEmpty()){
			throw new IndexOutOfBoundsException("队列为空!");
		}
		T t=stack2.peek();
		while(!stack2.isEmpty()){
			stack1.push(stack2.pop());
		}
		return t;
	}



	@Override
	public boolean add(T t)
	{
		int size=stack1.size();
		if(size>stack1.capacity()){
			throw new IndexOutOfBoundsException("队列已满！");
		}
		stack1.push(t);

		return true;
	}



	@Override
	public void resize(int newCapacity)
	{
		// TODO Auto-generated method stub

	}



	@Override
	public T remove(int i)
	{
		if(i!=0){
			throw new IndexOutOfBoundsException("只能删除队列的头元素！");
		}
		while(!stack1.isEmpty()){
			stack2.push(stack1.pop());
		}
		T t=null;
		if(!stack2.isEmpty()){
			t=stack2.pop();
		}
		while(!stack2.isEmpty()){
			stack1.push(stack2.pop());
		}
		return t;
	}



	@Override
	public void showQueue()
	{
		if(stack1.isEmpty()){
			throw new IndexOutOfBoundsException("stack1,队列为空!");
		}
		T t=null;
		while(!stack1.isEmpty()){
			stack2.push(stack1.pop());
		}
		if(stack2.isEmpty()){
			throw new IndexOutOfBoundsException("stack2,队列为空!");
		}
		while(!stack2.isEmpty()){
			t=stack2.pop();
			stack1.push(t);
			System.out.print(t+"\t");
		}

	}

}
