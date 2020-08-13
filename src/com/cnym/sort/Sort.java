package com.cnym.sort;

/**
 * @author : 城南有梦
 * @date : 2020-07-26 15:34:10
 * @description:
 */
public class Sort {
    /**
     * 冒泡排序 传入要排序的数组 升序 时间复杂度为o(n^2)
     * @param arr
     */
    public static void bubbleSort(Integer[] arr){
        int n = arr.length;
        for(int i = 0;i<n-1;i++){
            for(int j=0;j<n-i-1;j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    /**
     * 选择排序 升序 时间复杂度为o(n^2)
     * @param arr
     */
    public static void selectSort(Integer[] arr){
        int n = arr.length;
        for(int i=0;i<n;i++){
            //默认当前的i为最小的
            int minIndex = i;
            for(int j=i+1;j<n;j++){
                if(arr[j]<arr[minIndex]){
                    minIndex = j;
                }
            }
            //找到最小的minIndex 交换
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    /**
     * 插入排序 升序 时间复杂度为o(n^2)
     * @param arr
     */
    public static void insertSort(Integer[] arr){
        int n = arr.length;
        for(int i =1;i<n;i++){
            //记录下要插入的数据
            int temp = arr[i];
            int j;
            for(j=i;j>0 && arr[j-1]>temp;j--){
                arr[j] = arr[j-1];
            }
            //留出来的空位就是给要插入的数据的
            arr[j] = temp;
        }
    }

    /**
     * 归并排序 升序 时间复杂度为o(nlogn) 自顶向下 递归
     * @param arr
     * @param start
     * @param end
     */
    public static void mergeSort(Integer[] arr,int start,int end){
        if(start>=end){
            return;
        }
        //计算中间值相当于 start+(end-start)/2
        int mid = start + ((end-start)>>1);
        mergeSort(arr,start,mid);
        mergeSort(arr,mid+1,end);
        //开始合并
        merge(arr,start,mid,end);
    }

    static void merge(Integer[] arr,int start,int mid,int end){
        //临时数组
        int[] temp = new int[end-start+1];
        int left = start;
        int right = mid+1;
        int index = 0;
        while(left<=mid && right<=end){
            if(arr[left]<arr[right]){
                temp[index++] = arr[left++];
            }else{
                temp[index++] = arr[right++];
            }
        }

        //可能left有剩余 或者 right有剩余
        while(left<=mid){
            temp[index++] = arr[left++];
        }

        while(right<=end){
            temp[index++] = arr[right++];
        }

        //将temp数组对应位置赋值给arr
        for(int i=0;i<temp.length;i++){
            arr[start+i] = temp[i];
        }
    }

    /**
     * 快速排序 升序 时间复杂度o(nlogn)
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort(Integer[] arr,int left,int right){
        if(left>=right){
            return;
        }
        int leftIndex = left;
        int rightIndex = right;
        //待排序的第一个作为基准值
        int key = arr[left];
        while(leftIndex<rightIndex){
            //从右往左开始扫描 比key小的
            while(rightIndex>left && arr[rightIndex]>=key){
                rightIndex--;
            }

            //找到小于等于key的了
            arr[leftIndex] = arr[rightIndex];

            //从左往右开始扫描 找比key大的
            while(leftIndex<rightIndex && arr[leftIndex]<=key){
                leftIndex++;
            }

            //找到大于等于key的了
            arr[rightIndex] = arr[leftIndex];
        }

        //结束循环 基准值归位
        arr[leftIndex] = key;
        //对基准值左边的递归排序
        quickSort(arr,0,leftIndex-1);
        //对基准值右边的递归排序
        quickSort(arr,leftIndex+1,right);
    }

    /**
     * 堆排序 升序 时间复杂度 o(nlogn)
     * @param arr
     */
    public static void heapSort(Integer[] arr){
        if(arr==null || arr.length==0){
            return;
        }
        //先构建大顶堆
        for(int i=arr.length/2-1;i>=0;i--){
            adjustHeap(arr,i,arr.length);
        }

        for(int i=arr.length-1;i>0;i--){
            //将arr[i]和arr[0]交换
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            //调整剩下n-1个元素 构建大顶堆
            adjustHeap(arr,0,i);
        }
    }

    public static void adjustHeap(Integer[] arr,int i,int length){
        int leftIndex = 2*i+1;
        int rightIndex = leftIndex + 1;
        int largest = i;

        if(leftIndex < length && arr[leftIndex]>arr[largest]){
            largest = leftIndex;
        }

        if(rightIndex < length && arr[rightIndex]>arr[largest]){
            largest = rightIndex;
        }

        if(largest!=i){
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            adjustHeap(arr,largest,length);
        }
    }
}
