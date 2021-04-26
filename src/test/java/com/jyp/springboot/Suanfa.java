package com.jyp.springboot;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2020/2/26
 * Time: 11:38
 */
public class Suanfa {

    @Test
    public void test() {
        int[] nums = new int[]{0,0,0};
        List<List<Integer>> list = threeSum(nums);
        System.out.println(list.toString());
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        int len = nums.length;
        if(nums == null || len < 3) return list;
        Arrays.sort(nums); // 排序
        for(int i: nums){
            System.out.print(i + ",");
        }
        for(int i = 0;i < len;i++){
            if(nums[i] > 0){
                break;
            }
            if(i > 0 && nums[i] == nums[i-1]) continue; // 去重
            int L = i+1;
            int R = len-1;
            while(L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0){
                    list.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    while (L<R && nums[L] == nums[L+1]) L++; // 去重
                    while (L<R && nums[R] == nums[R-1]) R--; // 去重
                    L++;
                    R--;
                }
                else if (sum < 0) L++;
                else if (sum > 0) R--;
            }
        }
        return list;
    }
}
