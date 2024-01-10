package com.test.temp;
import java.util.*;

class MergeIntervals {

    public static void mergeIntervals(ArrayList<ArrayList<Integer> > intervals) {
        boolean hit = false;
        int first1List=0, last1List=0;
        for(int i=0; i<intervals.size()-1;i++) {
             ArrayList<Integer> temp1List = new ArrayList<Integer>(); 
            temp1List = intervals.get(i);
            if(!hit) {
                first1List = temp1List.get(0);
                last1List = temp1List.get(1);
            }
            for(int j=i+1; j<intervals.size();j++) {
                 ArrayList<Integer> temp2List,temp3List = new ArrayList<Integer>(); 
                temp2List = intervals.get(j);
                int firstCompareList = temp2List.get(0);
                int lastCompareList = temp2List.get(1);            
                if((first1List <= firstCompareList) && (firstCompareList <= last1List)) {
                    intervals.remove(i);
                    intervals.remove(j-1);
                    temp3List.add(0,first1List);
                    if(lastCompareList <= last1List) 
                        temp3List.add(1,last1List);
                    else
                        temp3List.add(1,lastCompareList);
                    intervals.add(i,temp3List);
                    first1List = temp3List.get(0);
                    last1List = temp3List.get(1);
                    hit=true;
                    j--;
                } else if ((last1List >= firstCompareList) && (last1List <= lastCompareList)) {
                    intervals.remove(i);
                    intervals.remove(j-1);
                    temp3List.add(0,first1List);
                    if(lastCompareList >= last1List) 
                        temp3List.add(1,lastCompareList);
                    else
                        temp3List.add(1,last1List);
                    intervals.add(i,temp3List);
                    first1List = temp3List.get(0);
                    last1List = temp3List.get(1);
                    hit=true;
                    j--;
                } else {
                    hit=false;
                }
            }
        }
    }

    public static void main(String[] args)
    {
        //Scanner scanner = new Scanner(System.in);

        //int n = scanner.nextInt();
        ArrayList<ArrayList<Integer> > listNew = new ArrayList<ArrayList<Integer> > ();
        
        int[][] nums = new int[5][2];

        /*for(int i = 0 ; i < n ;i++) {
            ArrayList<Integer> range = new ArrayList<Integer>();
            nums[i][0] = scanner.nextInt();
            nums[i][1] = scanner.nextInt();
            range.add(nums[i][0]);
            range.add(nums[i][1]);
            listNew.add(i,range);
        }*/
        nums[0][0] = 1;
        nums[0][1] = 2;
        ArrayList<Integer> range1 = new ArrayList<Integer>();
        range1.add(nums[0][0]);
        range1.add(nums[0][1]);
        nums[1][0] = 2;
        nums[1][1] = 4;
        ArrayList<Integer> range2 = new ArrayList<Integer>();
        range2.add(nums[1][0]);
        range2.add(nums[1][1]);
        nums[2][0] = 6;
        nums[2][1] = 10;
        ArrayList<Integer> range3 = new ArrayList<Integer>();
        range3.add(nums[2][0]);
        range3.add(nums[2][1]);
        nums[3][0] = 9;
        nums[3][1] = 10;
        ArrayList<Integer> range4 = new ArrayList<Integer>();
        range4.add(nums[3][0]);
        range4.add(nums[3][1]);
        ArrayList<Integer> range5 = new ArrayList<Integer>();
        nums[4][0] = 10;
        nums[4][1] = 200;
        range5.add(nums[4][0]);
        range5.add(nums[4][1]);

        listNew.add(range1);
        listNew.add(range2);
        listNew.add(range3);
        listNew.add(range4);
        listNew.add(range5);

        //List<List <Integer> > list = Arrays.stream(nums).boxed().collect(Collectors.toList());;  
        /*for(int i=0; i<listNew.size();i++) {
                System.out.println(" index i,j" +listNew.get(i).get(0) +listNew.get(i).get(1));
        }*/
        //for(int k=0;k<listNew.size();k++){
            MergeIntervals.mergeIntervals(listNew);
        //}
        //int[][] results = new MergeIntervals().mergeIntervals(list);

        //for (int i = 0; i < results.length; ++i) {
        //    System.out.printf("%d %d\n", results[i][0], results[i][1]);
        //}
        for(int i=0; i<listNew.size();i++) {
            System.out.printf("%d %d\n", listNew.get(i).get(0), listNew.get(i).get(1));
        }
    }
}
