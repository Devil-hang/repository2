package com.caibei;

public class AppTest {
        public int[] twoSum(int[] nums, int target) {
            for(int i = 0; i < nums.length; i++){
                for(int j = i+1; j < nums.length; j++){
                    int k = nums[i]+nums[j];
                    if(k == target){
                        return new int[]{i, j};
                    }
                }
            }
        return null;
        }

    public int reverse(int x) {
        String str = x + "";
        char c = 0;
        for(int i = str.length()-1; i >= 0; i--){
            c = str.charAt(i);
//            System.out.print(c);
        }
        System.out.print(c);
        System.out.println( c-'0');
        return c-'0';
    }

        public static void main(String[] args){
            int[] nums = {2, 7, 11, 15};
            AppTest s = new AppTest();
//            int i[] = s.twoSum(nums, 13);
//            System.out.println(i[0]+"   "+i[1]);
              int c = s.reverse(1230);
              System.out.println(c);
              System.out.println( Math.round(40/6));
            System.out.println( Math.round(6.6));
        }
    }

