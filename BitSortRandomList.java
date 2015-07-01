import java.util.*;

public class BitSortRandomList {
    public static void main(String[] args) {
        Random generator = new Random();
        
        int l = generator.nextInt(20) + 1;
        l = 10;
        int[] arr = new int[l];
        
        for (int a = 0; a < 10; a++) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = generator.nextInt(100);
            }
            
            System.out.print("[" + arr[0]);
            for (int i = 1; i < arr.length; i++) {
                System.out.print(", " + arr[i]);
            }
            System.out.print("]\n");
            
            validate(setUp(arr));
        }
    }
    
    public static ArrayList<Integer> setUp(int[] arr) {
        ArrayList<String> list = new ArrayList<String>();
        int largest = 0;
        
        for (int i = 0; i < arr.length; i++) {
            int temp = (int)(Math.log(arr[i]) / Math.log(2)) + 1;
            if (temp > largest)
                largest = temp;
        }
        
        for (int i = 0; i < arr.length; i++) {
            list.add(String.format("%0" + largest + "d", Integer.parseInt(Integer.toBinaryString(arr[i]))));
        }
        
        ArrayList<String> temp = sort(list, largest, 0);            //done after this
        ArrayList<Integer> tempInt = new ArrayList<Integer>();
        for (int i = 0; i < temp.size(); i++) {
            tempInt.add(Integer.parseInt(temp.get(i), 2));          //base 10
        }
        System.out.println(tempInt + "\n");
        return tempInt;
    }
    
    public static boolean validate(ArrayList<Integer> sortedList)
    {
        for (int i = 0; i < sortedList.size() - 1; i++) {
            if (sortedList.get(i) > sortedList.get(i + 1)) {
                System.out.println("NOT SORTED!!!");
                return false;
            }
        }
        
        return true;
    }
    
    public static ArrayList<String> sort(ArrayList<String> list, int max, int index) {
        ArrayList<String> ones = new ArrayList<String>();
        ArrayList<String> newOnes = new ArrayList<String>();
        ArrayList<String> zeros = new ArrayList<String>();
        ArrayList<String> newZeros = new ArrayList<String>();
        ArrayList<String> combined = new ArrayList<String>();
        
        if (list.size() == 1) {                         //if list cannot be broken down any further
            combined.add(list.get(0).substring(index));
            return combined;
        }
        if (list.get(0).equals(list.get(1))) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(list.get(0))) {
                    combined.add(list.get(i).substring(index));
                }
                else {
                    combined.clear();
                    break;
                }
            }
            if (!combined.isEmpty()) {
                return combined;
            }
        }
        
        for (int i = 0; i < list.size() && index < max; i++) {
            if (list.get(i).charAt(index) == '1') {
                ones.add(list.get(i));
            }
            else {
                zeros.add(list.get(i));
            }
        }
        
        if (!ones.isEmpty()) {
            newOnes = sort(ones, max, index + 1);
            int p = 0;
        }
        if (!zeros.isEmpty()) {
            newZeros = sort(zeros, max, index + 1);
            int p = 0;
        }
        
        for (int i = 0; i < newZeros.size(); i++) {     //least to greatest
            combined.add("0" + newZeros.get(i));
        }
        
        for (int i = 0; i < newOnes.size(); i++) {      //greatest to least
            combined.add("1" + newOnes.get(i));
        }
        
        return combined;
    }
}