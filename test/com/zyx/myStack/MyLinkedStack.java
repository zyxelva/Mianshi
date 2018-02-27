/**
 * 2018年2月18日
 * MyStack2
 * zyxelva
 */
package com.zyx.myStack;

/**
 * @author zyxelva
 *
 */
public class MyLinkedStack<E>implements IMyStack<E>
{
	private int size = 0;
	private MyNode top;

	private class MyNode
	{
		public E e = null;
		public MyNode next = null;

		MyNode(E e, MyNode next)
		{
			this.e = e;
			this.next = next;
		}
	}

	@Override
	public int size()
	{
		return this.size;
	}

	@Override
	public E pop()
	{
		E e = this.peek();
		this.size--;
		this.top = this.top.next;
		return e;
	}

	@Override
	public E peek()
	{
		if (isEmpty())
		{
			System.out.println("栈为空，无法输出栈顶元素！");
			return null;
		}
		return this.top.e;
	}

	@Override
	public boolean push(E e)
	{
		MyNode newNode = new MyNode(e, this.top);
		this.top = newNode;
		this.size++;
		return true;
	}

	@Override
	public boolean isEmpty()
	{
		return this.size <= 0 ? true : false;
	}

	@Override
	public void showStack()
	{
		if (isEmpty())
		{
			System.out.println("栈为空，无元素输出!");
			return;
		}

		MyNode mn = this.top;
		while (mn != null)
		{
			System.out.print(mn.e + "\t");
			mn = mn.next;
		}
		System.out.println();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		MyLinkedStack<Integer> stack = new MyLinkedStack<Integer>();
		// 入栈
		for (int i = 0; i < 5; i++)
		{
			stack.push(i);
		}
		stack.showStack();
		System.out.println("栈元素个数：" + stack.size());
		// System.out.println("栈容量："+stack.maxSize());
		System.out.println("栈顶元素：" + stack.peek());

		stack.pop();
		System.out.println("出栈后，栈情况：");

		stack.showStack();
		System.out.println("栈元素个数：" + stack.size());
		// System.out.println("栈容量："+stack.maxSize());
		System.out.println("栈顶元素：" + stack.peek());
	}

	@Override
	public int maxSize()
	{
		return 0;
	}

	@Override
	public boolean isMaxSize()
	{
		return false;
	}

}
