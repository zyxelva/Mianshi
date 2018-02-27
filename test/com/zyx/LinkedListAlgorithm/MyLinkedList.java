package com.zyx.LinkedListAlgorithm;

import java.util.HashSet;
import java.util.Set;

public class MyLinkedList<T>
{
	public static class Node<T>
	{
		T data;
		Node next;

		public Node(T t)
		{
			this.data = t;
			this.next = null;
		}
	}

	public boolean initLinkedList(Node node)
	{
		if (null == node)
		{
			return false;
		}
		return true;
	}

	public Node<T> convertArrayToLinkedList(T[] t)
	{
		if (t.length <= 0)
		{
			return null;
		}
		Node<T> myNode = new Node<T>(t[0]);
		Node<T> tempNode = null;
		Node<T> preNode = myNode;
		for (int i = 1; i < t.length; i++)
		{
			tempNode = new Node<T>(t[i]);
			preNode.next = tempNode;
			preNode = tempNode;
		}
		return myNode;
	}

	public void printLinkedList(Node<T> myNode)
	{
		Node<T> node = myNode;
		while (null != node)
		{
			System.out.print(node.data + "\t");
			node = node.next;
		}
		System.out.println();
	}

	public Node<T> revertLinkedList(Node<T> node)
	{
		if (null == node || node.next == null)
		{
			return null;
		}
		Node<T> pre = node;
		Node<T> current = pre.next;
		Node<T> next = null;

		while (current != null)
		{
			next = current.next;
			current.next = pre;
			pre = current;
			current = next;
		}
		node.next = null;
		node = pre;

		return node;
	}

	public T hasCross(Node node1, Node node2)
	{
		if (null == node1 || null == node2)
		{
			return null;
		}
		Node<?> temp = node1;
		T t = null;
		Set<T> set = new HashSet<T>();

		while (temp != null)
		{
			set.add((T) temp.data);
			temp = temp.next;
		}
		temp = node2;
		while (temp != null)
		{
			if (set.contains(temp.data))
			{
				t = (T) temp.data;
				break;
			}
			else
			{
				set.add((T) temp.data);
				temp = temp.next;
			}
		}

		return t;
	}

	public void linkListSort(Node<T> node)
	{
		if (null == node || node.next == null)
		{
			return;
		}
		Node<T> head = new Node<T>(node.data);
		node = node.next;
		Node<T> index = null;
		Node<T> temp = null;
		while (node != null)
		{
			index = head;
			while (index != null)
			{
				// 链表头前插入
				if (index == head && Integer.parseInt((String) node.data) <= Integer.parseInt((String) index.data))
				{
					temp = node.next;
					node.next = head;
					head = node;
					node = temp;
					break;
				}

				else if (Integer.parseInt((String) node.data) > Integer.parseInt((String) index.data))
				{
					// 链表尾部
					if (null == index.next)
					{
						index.next = node;
						node = node.next;
						index.next.next = null;
						break;
					}
					// 链表中部
					else
					{
						if (Integer.parseInt((String) index.next.data) > Integer.parseInt((String) node.data))
						{
							// 有问题的地方
							temp = node.next;
							node.next = index.next;
							index.next = node;
							node= temp;
							break;
						}
						else
						{
							index = index.next;
						}

					}
				}
			}
		}
		this.printLinkedList(head);

	}

	public Node<T> getKthNodeByInverse(Node<T> node,int k){
		if(null==node||k<=0)
		{
			return null;
		}
		Node<T> p1=null;
		Node<T> p2=null;
		for(int i=0;i<k;i++	){
			if(i==0)
			{
				p2=node;
			}
			else
			{
				p2=p2.next;
			}
		}
		if(p2==null)
		{
			return null;
		}
		p1=node;
		while(p2.next!=null){
			p1=p1.next;
			p2=p2.next;
		}
		return p1;
	}

	public static void main(String[] args)
	{
		MyLinkedList<String> mll = new MyLinkedList<String>();
		String[] shuzu = { "1", "3", "4", "7", "2", "6", "8", "9", "0", "5" };
		Node<String> myNode = mll.convertArrayToLinkedList(shuzu);
		mll.initLinkedList(myNode);
		mll.printLinkedList(myNode);
		System.out.println("++++++++++++++华丽的分割线++++++++++++++++");

		// System.out.println("逆序输出链表：");
		//
		// mll.printLinkedList(mll.revertLinkedList(myNode));

		System.out.println("+++++++++++++++++++++++构建第二个链表+++++++++++++++++++++");
		String[] shuzu2 = { "11", "33", "44", "77", "22", "6", "8", "9", "0", "5" };
		Node<String> myNode2 = mll.convertArrayToLinkedList(shuzu2);
		mll.printLinkedList(myNode2);

		String t = mll.hasCross(myNode, myNode2);
		if (t != null)
		{
			System.out.println("相交的节点值为：" + t);
		}
		else
		{
			System.out.println("无相交！");
		}

		System.out.println("++++++++++++++++++++排序++++++++++++++++++++");
		mll.linkListSort(myNode);

		System.out.println("++++++++++++++++++++倒数第2个数++++++++++++++++++++");
		System.out.println(mll.getKthNodeByInverse(myNode2, 2).data);

		System.out.println("++++++++++++++++++++倒数第10个数++++++++++++++++++++");
		System.out.println(mll.getKthNodeByInverse(myNode2, 10).data);
	}
}
