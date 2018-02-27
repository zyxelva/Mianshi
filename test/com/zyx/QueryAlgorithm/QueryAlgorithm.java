package com.zyx.QueryAlgorithm;

public class QueryAlgorithm
{
	public int NormalQuery(int[] list,int key){
		if(list.length<=0){
			return -1;
		}
		int i=0;
		while(key!=list[i]&&i<list.length){
			i++;
		}
		if(i>=list.length)
		{
			return -1;
		}
		else
		{
			return i;
		}
	}

	public int BinarySearch(int[] list,int key){
		if(list.length<=0){
			return -1;
		}
		int end=list.length-1;
		int start=0;
		int mid=0;
		while(start<=end){
			mid=start+(end-start)/2;
			//差值查找 改进自二分查找算法
			//mid=start+(key-list[start])/(list[end]-list[start])*(end-start);
			if(key<list[mid]){
				end=mid-1;
			}
			else if(key==list[mid])
			{
				return mid;
			}
			else
			{
				start=mid+1;
			}
		}
		return -1;
	}

	public static void main(String[] args)
	{
		int[] list = { 6, 3, 2, 1, 4, 7, 9, 5, 8, 0 };
		int[] list2 = { 0,1,2,3,4,5,6,7,8,9 };
		long startTime;
		long endTime;
		System.out.println("+++++++++++++++++++++++++++++++++++++++++");

		QueryAlgorithm qa=new QueryAlgorithm();
		System.out.println("一般查找");
		startTime=System.nanoTime();
		System.out.println("元素7的下标为："+qa.NormalQuery(list, 7));
		endTime=System.nanoTime();
		System.out.println("运行时间为："+(endTime-startTime)+" ns");

		System.out.println(" 二分查找");
		startTime=System.nanoTime();
		System.out.println("元素7的下标为："+qa. BinarySearch(list2, 7));
		endTime=System.nanoTime();
		System.out.println("运行时间为："+(endTime-startTime)+" ns");
	}

}
