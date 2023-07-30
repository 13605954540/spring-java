package algorithm;

import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 力扣算法题
 *
 */
public class Algorithm {

    public static List<List<Integer>> threeSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 3; i++) {
            if(nums[i] == nums[i + 1]) {
                continue;
            }
            if((long)nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if((long)nums[i] + nums[nums.length - 3] + nums[nums.length - 2] + nums[nums.length - 1] < target) {
                continue;
            }
            for(int y = i + 1; y < nums.length - 2; y++) {
                if(nums[y + 1] == nums[y]) {
                    continue;
                }
                if((long)nums[i] + nums[y] + nums[y + 1] + nums[y + 2] > target) {
                    break;
                }
                if((long)nums[i] + nums[y] + nums[nums.length - 2] + nums[nums.length - 1] < target) {
                    continue;
                }
                int left = y + 1;
                int right = nums.length - 1;
                while (left < right) {
                    if(nums[left] == nums[right]) {
                        break;
                    }
                    long sum = (long)nums[i] + nums[y] + nums[left] + nums[right];
                    if(sum < target) {
                        left++;
                        continue;
                    }
                    if(sum > target) {
                        right--;
                        continue;
                    }
                    List<Integer> innerList = new ArrayList<>();
                    innerList.add(nums[i]);
                    innerList.add(nums[y]);
                    innerList.add(nums[left]);
                    innerList.add(nums[right]);
                    if(!res.contains(innerList)) {
                        res.add(innerList);
                    }
                    right--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{-2, -1,-2, -4, 0, 2, 1, 2,  3,  4, 5};
        System.err.println(Algorithm.threeSum(ints, 0));
    }

    /**
     * 两数之和
     *
     * @deprecated 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 你可以按任意顺序返回答案
     */
    public class Inner1 {
        public int[] twoSum(int[] nums, int target) {
            int[] ins = new int[2];
            flag: {
                for(int i = 0; i < nums.length; i++) {
                    for(int y = i + 1; y < nums.length;y++) {
                        if(nums[i] + nums[y] == target) {
                            ins[0] = i;
                            ins[1] = y;
                            break flag;
                        }
                    }
                }
            }
            return ins;
        }
    }

    /**
     * 三数之和
     *
     * @deprecated 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
     * 你返回所有和为 0 且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     */
    public class Inner2 {
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> res = new ArrayList<>();
            for(int i = 0; i < nums.length;i++) {
                System.err.println("初次进入i = " + i);
                if(nums[i] > 0) {
                    break;
                }
                int left = i + 1;
                int right = nums.length - 1;
                int sum = 0;
                while(left < right) {
                    sum = nums[i] + nums[left] + nums[right];
                    if(sum == 0) {
                        System.err.println("--------- 进入 ---------");
                        List<Integer> innerList = new ArrayList<>();
                        innerList.add(nums[i]);
                        innerList.add(nums[left]);
                        innerList.add(nums[right]);
                        if(res.contains(innerList)) {
                            continue;
                        }
                        res.add(innerList);
                        right--;
                        continue;
                    }
                    if(sum < 0) {
                        left++;
                    }
                    if(sum > 0) {
                        right--;
                    }
                }
            }
            return res;
        }
    }

    /**
     * 四数之和
     *
     * 中等
     * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
     * 0 <= a, b, c, d < n
     * a、b、c 和 d 互不相同
     * nums[a] + nums[b] + nums[c] + nums[d] == target
     * 你可以按 任意顺序 返回答案 。
     */
    public class Inner3 {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(nums);
            for(int i = 0; i < nums.length - 3; i++) {
                if(nums[i] == nums[i + 1]) {
                    continue;
                }
                if((long)nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                    break;
                }
                if((long)nums[i] + nums[nums.length - 3] + nums[nums.length - 2] + nums[nums.length - 1] < target) {
                    continue;
                }
                for(int y = i + 1; y < nums.length - 2; y++) {
                    if(nums[y + 1] == nums[y]) {
                        continue;
                    }
                    if((long)nums[i] + nums[y] + nums[y + 1] + nums[y + 2] > target) {
                        break;
                    }
                    if((long)nums[i] + nums[y] + nums[nums.length - 2] + nums[nums.length - 1] < target) {
                        continue;
                    }
                    int left = y + 1;
                    int right = nums.length - 1;
                    while (left < right) {
                        if(nums[left] == nums[right]) {
                            break;
                        }
                        long sum = (long)nums[i] + nums[y] + nums[left] + nums[right];
                        if(sum < target) {
                            left++;
                            continue;
                        }
                        if(sum > target) {
                            right--;
                            continue;
                        }
                        List<Integer> innerList = new ArrayList<>();
                        innerList.add(nums[i]);
                        innerList.add(nums[y]);
                        innerList.add(nums[left]);
                        innerList.add(nums[right]);
                        if(!res.contains(innerList)) {
                            res.add(innerList);
                        }
                        right--;
                    }
                }
            }
            return res;
        }
    }
}
