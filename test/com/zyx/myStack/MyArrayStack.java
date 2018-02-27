/**
 *
 */
package com.zyx.myStack;

/**
 * @author zyxelva
 *
 */
public class MyArrayStack<E> implements IMyStack<E>
{
	private Object[] data = null;
	private int top = -1;
	private int size = 0;

	public MyArrayStack()
	{
		this.size = 10;
		this.top = -1;
		data = new Object[10];
	};

	public MyArrayStack(int initSize)
	{
		this.size = initSize;
		data = new Object[initSize];
		this.top = -1;
	}

	@Override
	public int size()
	{
		return this.top + 1;
	}

	@Override
	public int maxSize()
	{
		return this.size;
	}

	@Override
	public boolean isEmpty()
	{
		return this.top == -1 ? true : false;
	}

	@Override
	public boolean isMaxSize()
	{
		return this.top > this.size ? true : false;
	}

	@Override
	public boolean push(E obj)
	{
		if (isMaxSize())
		{
			System.out.println("栈已满！无法入栈!	");
			return false;
		}

		this.top++;
		data[top] = obj;
		return true;
	}

	@Override
	public E pop()
	{
		if (isEmpty())
		{
			System.out.println("栈为空，无法出栈！");
			return null;
		}
		E o = this.peek();
		data[top] = null;
		top--;
		return o;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E peek()
	{
		if (isEmpty())
		{
			System.out.println("栈为空，无法出栈！");
			return null;
		}

		return (E) data[top];
	}

	@Override
	public void showStack()
	{
		if (this.top < 0)
		{
			System.out.println("栈为空，无元素输出!");
			return;
		}
		int index = this.top;

		while (index >= 0)
		{
			System.out.print(data[index--] + "\t");
		}
		System.out.println();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		MyArrayStack<Integer> stack = new MyArrayStack<Integer>();
		// 入栈
		for (int i = 0; i < 5; i++)
		{
			stack.push(i);
		}
		stack.showStack();
		System.out.println("栈元素个数：" + stack.size());
		System.out.println("栈容量：" + stack.maxSize());
		System.out.println("栈顶元素：" + stack.peek());

		stack.pop();
		System.out.println("出栈后，栈情况：");

		stack.showStack();
		System.out.println("栈元素个数：" + stack.size());
		System.out.println("栈容量：" + stack.maxSize());
		System.out.println("栈顶元素：" + stack.peek());
	}

}
