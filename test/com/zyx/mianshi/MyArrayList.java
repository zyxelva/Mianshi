package com.zyx.mianshi;

public class MyArrayList
{
	//List<String> array=new ArrayList<String>();
	public int[] topK(int[] list,int k){
		if(k<=0||list.length<=0){
			return null;
		}
		int len=list.length;
		QuickSort(list,0,len-1,k);
		for(int i=0;i<k;i++){
			System.out.print(list[i]+"\t");
		}
		return list;

	}

	public void QuickSort(int[] list,int low,int high,int k){
		if(low<high){
			int index=Partition(list,low,high);
			if(index+1==k){
				return;
			}
			if(index+1>k)
			{
				QuickSort(list,low,index-1,k);
			}
			else
			{
				QuickSort(list,index+1,high,k);
			}
		}
	}

	public int Partition(int[] list,int start,int end){
		int low=start;
		int high=end;
		int temp=list[high];
		while(low<high){
			while(low<high&&list[low]>temp)
			{
				low++;
			}
			if(low<high)
			{
				list[high--]=list[low];
			}
			while(low<high&&list[high]<temp){
				high--;
			}
			if(low<high){
				list[low++]=list[high];
			}
		}
		list[low]=temp;
		return low;
	}

	public void HeapSort(int[] list,int k){
		if(null==list||list.length<=0||k<=0||k>list.length)
		{
			throw new IndexOutOfBoundsException("越界了！！！");
			//return;
		}
		int len=list.length;
		int temp;
		//建立小顶堆
		for(int i=(len-1)/2;i>=0;i--){
			HeapAdjust(list,i,len-1);
		}
		for(int j=len-1;j>0;j--){
			temp=list[j];
			list[j]=list[0];
			list[0]=temp;

			HeapAdjust(list,0,j-1);
			if(j==k-1)
			{
				break;
			}
		}
		for(int i=0;i<k;i++){
			System.out.print(list[i]+"\t");
		}
	}

	public void HeapAdjust(int[] list,int i,int len){
		if(i>len)
		{
			return;
		}
		int left=2*i+1;
		int right=2*i+2;
		int smallest=i;
		int temp;

		if(left<=len&&list[left]<list[smallest]){
			smallest=left;
		}
		if(right<=len&&list[right]<list[smallest]){
			smallest=right;
		}

		if(smallest!=i){
			temp=list[i];
			list[i]=list[smallest];
			list[smallest]=temp;

			HeapAdjust(list,smallest,len);
		}
	}

	public static void main(String[] args)
	{
		int[] list = { 6, 3, 2, 1, 4, 7, 9, 5, 8, 0 };
//		List<String> list = new ArrayList<String>();
//		list = Arrays.asList(shuzu);
		System.out.println("+++++++++++++++快速排序结果+++++++++++++++++++++");
		MyArrayList mal=new MyArrayList();
		long startTime=System.nanoTime();
		mal.topK(list, 6);
		long endTime=System.nanoTime();
		System.out.println("用时："+(endTime-startTime)+" ns");


		System.out.println("+++++++++++++++小顶堆排序结果+++++++++++++++++++++");
		startTime=System.nanoTime();
		mal.HeapSort(list,6);
		endTime=System.nanoTime();
		System.out.println("用时："+(endTime-startTime)+" ns");

	}

}
