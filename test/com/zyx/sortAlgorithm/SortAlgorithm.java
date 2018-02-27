/**
 *
 */
package com.zyx.sortAlgorithm;

/**
 * @author zyxelva
 *
 */
public class SortAlgorithm
{
	private static int[] list = { 6, 3, 2, 1, 4, 7, 9, 5, 8, 0 };

	public void printList(int[] a)
	{
		if (a.length <= 0)
		{
			System.out.println("Empty array!");
		}

		System.out.println("Print ArrayList: ");
		for (int b : a)
		{
			System.out.print(b + " ");
		}
		System.out.println("End");
	}

	public void simpleSort()
	{
		int[] a = list;
		int temp;
		for (int i = 0; i < a.length - 1; i++)
		{
			for (int j = i + 1; j < a.length; j++)
			{
				if (a[i] > a[j])
				{
					temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
				else
				{
					continue;
				}
			}
		}

		printList(a);
	}

	public void bubbleSort()
	{
		int[] a = list;
		int temp;
		for (int i = a.length - 1; i >= 0; i--)
		{
			for (int j = 0; j < i; j++)
			{
				if (a[j] > a[j + 1])
				{
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
			printList(a);
		}

		printList(a);
	}

	public void directInsertSort()
	{
		int[] a = list;
		int temp;

		for (int i = 1; i < a.length; i++)
		{
			int j = i - 1;
			temp = a[i];
			// while (j>=0)
			// {
			// if(temp < a[j]){
			// a[j + 1] = a[j];
			// j--;
			// }
			// else
			// {
			// break;
			// }
			// }
			while (j >= 0 && temp < a[j])
			{
				a[j + 1] = a[j];
				j--;
			}
			a[j + 1] = temp;
			printList(a);
		}

		printList(a);
	}

	public void halfSort()
	{
		System.out.println("二分插入排序：");
		int[] a = list;
		int temp;

		for (int i = 1; i < a.length; i++)
		{
			int high = i - 1;
			temp = a[i];

			int low = 0;
			int k = 0;
			while (low <= high)
			{
				k = (low + high) / 2;
				if (temp > a[k])
				{
					low = k + 1;
				}
				else
				{
					high = k - 1;
				}
			}

			for (int j = i - 1; j >= low; j--)
			{
				a[j + 1] = a[j];
			}
			a[high + 1] = temp;
			printList(a);
		}

	}

	public void quickSort()
	{
		int[] a = list;
		int lengh = a.length - 1;
		System.out.println("快排：");
		quickSort(a, 0, lengh);
		printList(a);
	}

	public void quickSort(int[] b, int low, int high)
	{
		if (low < high)
		{
			int index = partion(b, low, high);
			quickSort(b, low, index - 1);
			quickSort(b, index + 1, high);
		}
		printList(b);
	}

	public int partion(int[] c, int low, int high)
	{
		int key = c[low];
		while (low < high)
		{
			while (low < high && key <= c[high])
			{
				high--;
			}
			if (low < high)
			{
				c[low++] = c[high];
			}
			while (low < high && key >= c[low])
			{
				low++;
			}
			if (low < high)
			{
				c[high--] = c[low];
			}

		}
		c[high] = key;
		return high;
	}

	public void heapSort()
	{
		int[] a = list;
		int length = a.length;

		initHeap(a, length);

		int temp = 0;
		for (int j = a.length - 1; j > 0; j--)
		{
			temp = a[0];
			a[0] = a[j];
			a[j] = temp;

			heapAdjust(a, 0, j - 1);
			System.out.println("第" + (length - j) + "次排序");
			printList(a);
		}
	}

	public void initHeap(int[] b, int len)
	{
		for (int i = (len - 1) / 2; i >= 0; i--)
		{
			heapAdjust(b, i, len);
		}
		System.out.println("初始化堆后，序列：");
		printList(b);
	}

	public void heapAdjust(int[] c, int i, int length)
	{
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		int largest = i;
		int temp = 0;

		if (left < length && c[left] > c[i])
		{
			largest = left;
		}
		if (right < length && c[right] > c[largest])
		{
			largest = right;
		}

		if (largest != i)
		{
			temp = c[largest];
			c[largest] = c[i];
			c[i] = temp;
			heapAdjust(c, largest, length);
		}
	}

	public void mergeSort()
	{
		int[] a = list;
		int len = a.length;
		// int[] temp = null;
		MSort(a, 0, len - 1);
	}

	public void MSort(int[] b, int start, int end)
	{
		if (start <end)
		{
			int mid = start + (end - start) / 2;//防止栈溢出
			MSort(b, start, mid);
			MSort(b, mid + 1, end);
			MergeList(b, start, mid, end);
		}
	}

	public void MergeList(int[] c, int low, int mi, int end)
	{
		int k = 0;
		int[] temp3 = new int[end-low+1];

		int start = low;
		int mid = mi + 1;

		while (start <= mi && mid <= end)
		{
			if (c[start] <= c[mid])
			{
				temp3[k++] = c[start++];
			}
			else
			{
				temp3[k++] = c[mid++];
			}
		}

		while (start <=mi)
		{
			temp3[k++] = c[start++];
		}
		while (mid <=end)
		{
			temp3[k++] = c[mid++];
		}

		for (int j = 0; j < k; j++)
		{
			c[j + low] = temp3[j];
		}
		System.out.println("++++++++++++++++++++++++++++++++++");
		printList(c);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.out.println("Original List is ");
		new SortAlgorithm().printList(list);

		System.out.println("After sorting, List is ");
		// new SortAlgorithm().simpleSort();
		// new SortAlgorithm().bubbleSort();
		new SortAlgorithm().mergeSort();
	}

}
