/**
 * 2018年2月18日
 * MyLinkedQueue
 * zyxelva
 */
package com.zyx.myQueue;

/**
 * @author zyxelva
 *
 */
public class MyLinkedQueue<T> implements IMyQueue<T>
{
	private int size=0;
	private MyNode head;
	private MyNode tail;

	public MyLinkedQueue(){
		this.head=this.tail=null;
	}

	private class MyNode{
		private T data;
		private MyNode next;

		public MyNode(T t,MyNode mn){
			this.data=t;
			this.next=mn;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.out.println("新建队列：");
		MyLinkedQueue<Integer> mlq=new MyLinkedQueue<Integer>();
		for(int i=0;i<10;i++){
			mlq.add(i);
		}

		mlq.showQueue();
		System.out.println("+++++++++++输出头结点和尾节点位置的数据++++++++++++++++");
		System.out.println("Head: "+mlq.head.data);
		System.out.println("Tail: "+mlq.tail.data);
		System.out.println("+++++++++++队列元素个数：");
		System.out.println(mlq.size());


		System.out.println("++++++++++++++出列后，队列信息为：");
		mlq.remove(0);
		mlq.showQueue();

		System.out.println("+++++++++++输出头结点和尾节点位置的数据++++++++++++++++");
		System.out.println("Head: "+mlq.head.data);
		System.out.println("Tail: "+mlq.tail.data);
		System.out.println("+++++++++++队列元素个数：");
		System.out.println(mlq.size());
	}

	@Override
	public int size()
	{
		return this.size;
	}

	@Override
	public T get(int i)
	{
//		if(i!=0){
//			throw new IndexOutOfBoundsException("只能从队列的头获取元素！");
//		}
		if(i<0||i>this.size)
		{
			throw new IndexOutOfBoundsException("越界了！");
		}
		if(this.head==this.tail){
			throw new IndexOutOfBoundsException("队列为空！");
		}
		MyNode mn=this.head;
		for(int j=0;j<this.size;j++){
			if(j==i&&mn!=null)
			{
				return mn.data;
			}
			else{
				mn=mn.next;
			}
		}
		return null;
	}

	@Override
	public boolean add(T t)
	{

		MyNode mn=new MyNode(t,null);
		this.size++;
		if(isEmpty()){
			this.head=mn;
			this.tail=mn;
			//this.size++;
			return true;
		}
		this.tail.next=mn;
		this.tail=mn;
		//this.size++;

		return true;
	}

	@Override
	public void resize(int newCapacity)
	{
		// TODO Auto-generated method stub

	}

	public boolean isEmpty(){
		return this.head==null;
	}

	@Override
	public T remove(int i)
	{
		if(i!=0){
			throw new IndexOutOfBoundsException("只能从头结点删除元素！");
		}
		if(this.size()<1){
			throw new IndexOutOfBoundsException("空队列，无法删除!");
		}
		T t=this.head.data;
		this.head=this.head.next;
		this.size--;
		return t;
	}

	@Override
	public void showQueue()
	{
		if(this.size<1){
			throw new  IndexOutOfBoundsException("Queue empty");
		}
		System.out.println("+++++++++++++++开始输出队列信息+++++++++++++++");
		MyNode countNode=this.head;
		while(countNode!=null){
			System.out.print(countNode.data+"\t");
			countNode=countNode.next;
		}
	}

}
