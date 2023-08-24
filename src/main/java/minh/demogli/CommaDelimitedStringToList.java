package minh.demogli;

import java.util.*;

public class CommaDelimitedStringToList {
    public static void main(String[] args) {
        //Get input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input String");
        String input = scanner.nextLine();

        //Get string numbers from input string
        List<String> stringList = Arrays.asList(input.split(",",-1));
        //cast string to integer
        List<Integer> listIntegers = new ArrayList<>(stringList.size());
        for(String current:stringList){
            listIntegers.add(Integer.parseInt(current));
        }

        //Reverse sort
        Collections.sort(listIntegers,Collections.reverseOrder());

        //Print
        System.out.println(listIntegers.toString());
    }
}
