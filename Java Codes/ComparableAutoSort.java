import java.util.*;

public class ComparableAutoSort {

    public static void main(String[]args){

        
        List<String> names = Arrays.asList("Sushant","Ram","Laxman");
        List<Integer> nums = Arrays.asList(10, 4 ,8);
        List<Boolean> flag = Arrays.asList(true, false, false);

        Collections.sort(names);
        Collections.sort(nums);
        Collections.sort(flag);

        System.out.println(names);
         System.out.println(nums);
          System.out.println(flag);
        
    }
}