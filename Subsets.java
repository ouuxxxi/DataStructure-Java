//给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {
    public static List<List<Integer>> subsets(int[] nums){
        List<List<Integer>> arr = new ArrayList<>();
        arr.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) { // ①遍历数组 从数组中取出每个元素
            int size = arr.size();
            for (int j = 0; j < size; j++) {
                List<Integer> tmp = new ArrayList<>(arr.get(j));// ②逐一取出中间结果集
                tmp.add(nums[i]);//// ③将 nums 放入中间结果集
                arr.add(tmp);//// ④加入到结果集中
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] nums={1,2,3};
        List<List<Integer>> nums1=subsets(nums);
        System.out.println(Arrays.toString(new List[]{nums1}));
    }
}
