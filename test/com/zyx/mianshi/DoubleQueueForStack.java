/**
 * 2018年2月19日
 * DoubleQueueForStack
 * zyxelva
 */
package com.zyx.mianshi;

import java.util.ArrayDeque;
import java.util.Queue;

import com.zyx.myStack.IMyStack;
import com.zyx.myStack.MyArrayStack;

/**
 * @author zyxelva
 * @param <T>
 *
 */
public class DoubleQueueForStack<T> implements IMyStack<T>
{
	private Queue<T> queue1=new ArrayDeque<T>();
	private Queue<T> queue2=new ArrayDeque<T>();
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		DoubleQueueForStack<Integer> dqs=new DoubleQueueForStack<>();
		for(int i=0;i<6;i++){
			dqs.push(i);
		}
		dqs.showStack();

		System.out.println("++++++++++出栈++++++++++++");
		System.out.println("出栈："+dqs.pop());
		dqs.showStack();

		System.out.println("+++++++++输出栈顶元素++++++++");
		System.out.println("栈顶元素："+dqs.peek());
		dqs.showStack();

		System.out.println("++++++++++再次出栈++++++++++++");
		System.out.println("出栈："+dqs.pop());
		dqs.showStack();
	}
	@Override
	public int size()
	{
		return queue1.size();
	}
	@Override
	public int maxSize()
	{
		return queue1.size();
	}
	@Override
	public boolean isEmpty()
	{
		return queue1.isEmpty();
	}
	@Override
	public boolean isMaxSize()
	{
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void showStack()
	{
		if(isEmpty()){
			throw new IndexOutOfBoundsException("栈为空！");
		}
		MyArrayStack<T> sk=new MyArrayStack<>(20);
		while(queue1.size()!=0){
			//System.out.print(queue1.peek()+"\t");
			sk.push(queue1.peek());
			queue2.add(queue1.poll());

			if(queue1.isEmpty()){
				queue1.addAll(queue2);
				queue2=new ArrayDeque<T>();
				break;
			}
		}
		sk.showStack();
	}
	@Override
	public T peek()
	{
		if(isEmpty()){
			System.out.println("栈为空！");
			return null;
		}
		T t=null;
		while(queue1.size()!=0){
			if(queue1.size()==1){
				t=queue1.poll();
				queue2.add(t);
				break;
			}else{
				queue2.add(queue1.poll());
			}
		}
		queue1.addAll(queue2);
		queue2=new ArrayDeque<T>(queue1.size());
		return t;
	}
	@Override
	public T pop()
	{
		if(isEmpty()){
			System.out.println("栈为空！");
			return null;
		}
		T t=null;
		while(queue1.size()!=0){
			if(queue1.size()==1){
				t=queue1.poll();
				break;
			}else{
				queue2.add(queue1.poll());
			}
		}
		queue1.addAll(queue2);
		queue2=new ArrayDeque<T>(queue1.size());
		return t;
	}
	@Override
	public boolean push(T obj)
	{
		queue1.add(obj);
		return true;
	}

}
