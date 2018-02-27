package com.sortAlgorithm.service;

import com.sortAlgorithm.service.ISortAlgorithm.ISortAlgorithms;

public class SortAlgorithms implements ISortAlgorithms
{
	public static void print(int[] array)
	{
		if (array.length <= 0)
			return;
		for (int i = 0; i < array.length; i++)
		{
			System.out.print(array[i] + " ");
		}
	}

	@Override
	public void BubbleSort(int[] array)
	{
		int length = array.length;
		int temp = 0;

		for (int i = 0; i < length - 1; i++)
		{
			for (int j = i + 1; j < length; j++)
			{
				if (array[i] > array[j])
				{
					temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
				else
					continue;
			}
		}
		System.out.println("Bubble Sort Algorithm");
		print(array);
	}

	@Override
	public void SimpleSelectSort(int[] array)
	{
		int length = array.length;
		int temp = 0;

		for (int i = 0; i < length - 1; i++)
		{
			int k = i;
			for (int j = i + 1; j < length; j++)
			{
				if (array[j] < array[k])
				{
					k = j;
				}
				else
					continue;
			}
			temp = array[i];
			array[i] = array[k];
			array[k] = temp;
		}
		System.out.println("\nSimple Select Sort Algorithm");
		print(array);
	}

	@Override
	public void SimpleSelectSortPro(int[] array)
	{
		int length = array.length;
		int temp = 0;
		int max;
		int min;

		for (int i = 1; i < length / 2; i++)
		{
			max = min = i;
			for (int j = i + 1; j <= length - i; j++)
			{
				if (array[j] > array[max])
				{
					max = j;
					continue;
				}
				if (array[j] < array[min])
				{
					min = j;
				}
			}
			temp = array[i - 1];
			array[i - 1] = array[min];
			array[min] = temp;
			temp = array[length - i];
			array[length - i] = array[max];
			array[max] = temp;
		}
		System.out.println("\nSelect Insertion Sort  Pro Algorithm");
		print(array);
	}

	@Override
	public void StraightInsertionSort(int[] array)
	{
		int length = array.length;
		int temp = 0;

		for (int i = 1; i < length; i++)
		{
			if (array[i] < array[i - 1])
			{
				temp = array[i];
				int j;
				// array[i]=array[i-1];
				for (j = i - 1; j >= 0 && temp < array[j]; j--)
				{
					array[j + 1] = array[j];
				}
				array[j + 1] = temp;
			}
		}
		System.out.println("\nSelect Insertion Sort Algorithm");
		print(array);
	}

	@Override
	public void ShellSort(int[] array)
	{
		int length = array.length;
		int dk = 0;
		while (dk < length)
		{
			dk = dk * 3 + 1;
		}

		while (dk > 0)
		{
			for (int i = dk; i < length; i++)
			{
				int temp = array[i];
				int j;
				for (j = i - dk; j >= 0 && array[j] > temp; j -= dk)
				{
					array[j + dk] = array[j];
				}
				array[j + dk] = temp;
			}
			dk = (dk - 1) / 3;
		}
		System.out.println("\nShell Sort Algorithm");
		print(array);
	}

	@Override
	public void HeapSort(int[] array)
	{
		int length = array.length;
		BuildHeap(array, length);
		System.out.println("After Building Heap: ");
		print(array);
		System.out.println();
		int temp;
		for (int i = length - 1; i > 0; i--)
		{
			temp = array[0];
			array[0] = array[i];
			array[i] = temp;
			HeapAdjust(array, 0, i - 1);
		}
	}

	public void BuildHeap(int[] array, int length)
	{
		// 从第一个非叶子节点开始调整堆
		for (int i = (length - 2) / 2; i >= 0; i--)
		{
			HeapAdjust(array, i, length);
		}
	}

	public void HeapAdjust(int[] array, int i, int length)
	{
		int largestNode = i;
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		if (left < length && array[left] > array[i])
		{
			largestNode = left;
		}
		
		if (right < length && array[right] > array[largestNode])
		{
			largestNode = right;
		}

		if (largestNode != i)
		{
			Swap(array, largestNode, i);
			HeapAdjust(array, largestNode, length);
		}
	}

	public void Swap(int[] data, int i, int j)
	{
		if (i == j)
		{
			return;
		}
		data[i] = data[i] + data[j];
		data[j] = data[i] - data[j];
		data[i] = data[i] - data[j];
	}

	//归并排序算法
	public void MergeSort(int[] array){
		if(array.length<=1){
			return;
		}
		int length=array.length;
		MergeSort(array,0,length-1);
	}
	
	public void MergeSort(int[] array,int low,int high){
		int mid=(low+high)/2;
		if(low<high){
			MergeSort(array, low, mid);
			MergeSort(array,mid+1,high);
			Merge(array,low,mid,high);
		}
	}
	
	public void Merge(int[] array,int low,int mid,int high){
		int first=low;
		int second=mid+1;
		int k=0;
		int[] temp=new int[high-low+1];
		while(first<=mid&&second<=high){
			if(array[first]<array[second]){
				temp[k++]=array[first++];
			}
			else temp[k++]=array[second++];
		}
		//处理剩下的元素
		while(first<=mid){
			temp[k++]=array[first++];
		}
		while(second<=high){
			temp[k++]=array[second++];
		}
		
		//将排序好的序列放回原数组
		for(int j=0;j<k;j++){
			array[low+j]=temp[j];
		}
	}
	
	public static void main(String[] args)
	{
		int array[] = { 3, 1, 5, 7, 2, 4, 9, 6, 10, 8 };
		System.out.println("Before Sorting the array: \n");
		print(array);
		System.out.println("\nAfter Sorting the array: \n");
		SortAlgorithms sa = new SortAlgorithms();
		// sa.BubbleSort(array);
		// sa.SimpleSelectSort(array);
		// sa.StraightInsertionSort(array);
		// sa.ShellSort(array);
		// sa.SimpleSelectSortPro(array);
		//sa.HeapSort(array);
		long start=System.nanoTime();
		sa.MergeSort(array);
		long end=System.nanoTime();
		System.out.println("time: "+(end-start)+" ns");
		print(array);
	}

}
