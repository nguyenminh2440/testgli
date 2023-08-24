package minh.demogli;

import java.util.*;
import java.util.stream.Collectors;

public class CommaDelimitedStringToList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input String");
        String input = scanner.nextLine();

        List<String> stringList = Arrays.asList(input.split(",",-1));
        List<Integer> listIntegers = new ArrayList<>(stringList.size());
        for(String current:stringList){
            listIntegers.add(Integer.parseInt(current));
        }

        Collections.sort(listIntegers,Collections.reverseOrder());

        System.out.println(listIntegers.toString());
    }
}
