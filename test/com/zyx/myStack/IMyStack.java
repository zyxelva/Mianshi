package com.zyx.myStack;

public interface IMyStack<E>
{

	int size();

	int maxSize();

	boolean isEmpty();

	boolean isMaxSize();

	void showStack();

	E peek();

	E pop();

	boolean push(E obj);

}
