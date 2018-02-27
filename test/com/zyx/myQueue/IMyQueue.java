package com.zyx.myQueue;

public interface IMyQueue<T>
{
	public int size();

	public T get(int i);

	public boolean add(T t);

	public void resize(int newCapacity);

	public T remove(int i);

	public void showQueue();
}
