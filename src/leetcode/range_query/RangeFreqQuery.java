package leetcode.range_query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RangeFreqQuery {

    // we can sort arr that maintains their natural order 
    // same value will appear adjacent in a run, run-start  < run-end
    List<Integer>[] valMap;
    public RangeFreqQuery(int[] arr) {
        valMap = new List[10*10*10*10+1];
         
        for(int idx=0; idx<arr.length; idx++){
            int val = arr[idx];
            if (valMap[val] == null){
                valMap[val] = new ArrayList<>();
            }
            valMap[val].add(idx);
        }

    }
    
    public int query(int left, int right, int value) {
        List<Integer> idxList = valMap[value];
        int count = 0;

        int ldx = Collections.binarySearch(idxList, left);
        if(ldx < 0){
            ldx = (-ldx) - 1;
        }

        if(ldx == idxList.size()){
            return 0;
        }

        int rdx = Collections.binarySearch(idxList, right);
        if(rdx < 0){
            rdx = (-rdx) - 1;
        }

        return (rdx - ldx);

    }
}