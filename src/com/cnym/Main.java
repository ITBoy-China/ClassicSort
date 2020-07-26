package com.cnym;

import com.cnym.sort.Sort;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Integer[] arr = {6,3,2,8,7,9,10,1,5,4};
        Sort.quickSort(arr,0,arr.length-1);
        Arrays.asList(arr).forEach(System.out::println);
    }
}
