/**
 * 2018年2月18日
 * MyArrayQueue
 * zyxelva
 */
package com.zyx.myQueue;

/**
 * @author zyxelva
 *
 */
public class MyArrayQueue<T> implements IMyQueue<T>
{

	private int capacity;
	private int head;
	private int tail;
	private T[] queue;

	public MyArrayQueue(int capacity){
		this.capacity=capacity+1;
		this.queue=newArray(capacity+1);
		this.head=0;
		this.tail=0;
	};

	@SuppressWarnings("unchecked")
	public T[] newArray(int size){
		return (T[]) new Object[size];
	}

	@Override
	public int size(){
		return (this.tail-this.head+this.capacity)%this.capacity;
	}

	@Override
	public T get(int i){
		int size=this.size();
		if(i<0||i>size){
			throw new IndexOutOfBoundsException("越界了！");
		}
		int index=(head+i)%this.capacity;
		return queue[index];
	}

	@Override
	public boolean add(T t){
		queue[tail]=t;
		int newTail=(tail+1)%this.capacity;
		//this.showQueue();
		if(newTail==head){
//			System.out.println("队列已满，无法插入新元素至队列中！");
//			return false;
			throw new IndexOutOfBoundsException("Queue full");
		}
		tail=newTail;
		return true;
	}

	@Override
	public T remove(int i){
		if(i!=0){
			System.out.println("只能删除队列的头元素！");
			return null;
		}
		if(this.head==this.tail){
			System.out.println("队列为空，无法删除队列头元素！");
			return null;
		}
		T t=queue[head];
		queue[head]=null;
		head=(head+1)%this.capacity;
		return t;
	}

	@Override
	public void resize(int newCapacity){
		int size=this.size();
		if(newCapacity<this.capacity){
			throw new IndexOutOfBoundsException("Resizing would lose data");
		}
		newCapacity++;
		if(newCapacity==this.capacity){
			return;
		}

		T[] newQueue=newArray(newCapacity);
		for(int i=0;i<size;i++){
			newQueue[i]=this.get(i);
		}
		this.queue=newQueue;
		this.head=0;
		this.tail=size;
		this.capacity=newCapacity;
	}

	@Override
	public void showQueue(){
		if(this.size()<=0){
			System.out.println("队列为空，无法输出！");
			return;
		}
		for(T t:queue){
//			if(t!=null)
//			{
				System.out.print(t+"\t");
//			}
		}
		System.out.println();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		MyArrayQueue<Integer> mq=new MyArrayQueue<Integer>(6);
		for (int i = 0; i < 5; i++)
		{
			mq.add(i);
		}
		mq.showQueue();
		System.out.println("获取元素："+mq.get(4));
		mq.add(100);
		mq.showQueue();
		System.out.println("获取元素："+mq.get(4));
		System.out.println("队列 head= "+mq.head);
		System.out.println("队列 tail= "+mq.tail);

		mq.remove(0);
		System.out.println("删除元素后，队列为");
		mq.showQueue();
		System.out.println("队列 head= "+mq.head);
		System.out.println("队列 tail= "+mq.tail);


//		System.out.println("++++++++++++++第二次增加新元素++++++++++++++++++");
//		mq.add(200);
//		mq.add(101);
//		mq.showQueue();

		System.out.println("+++++++++++++扩容后，再次增加新元素++++++++++++++");
		mq.resize(20);
		mq.showQueue();
		System.out.println("+++++++++++++华丽的分割线+++++++++++++++++++++");

		mq.add(250);
		mq.add(107);
		mq.showQueue();
		System.out.println("队列 head= "+mq.head);
		System.out.println("队列 tail= "+mq.tail);
	}

}
