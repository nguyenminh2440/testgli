package minh.demogli;
import java.util.Scanner;

public class QuadraticEquation {

    public static void main(String[] args) {
        //Get input
        Scanner scanner = new Scanner(System.in);
        System.out.println("a=:");
        float a = scanner.nextFloat();
        System.out.println("b=:");
        float b = scanner.nextFloat();
        System.out.println("c=:");
        float c = scanner.nextFloat();
        //Check special cases
        if (a == 0) {
            if (b == 0) {
                System.out.println("No roots");
            } else {
                System.out.println("x = " + (-c / b));
            }
            return;
        }

        //Calculate delta
        float delta = b*b - 4*a*c;
        //Initialize roots
        float x1;
        float x2;

        //Solve
        if (delta < 0) {
            System.out.println("No roots");
        } else if (delta == 0) {
            x1 = (-b / (2 * a));
            System.out.println("x1 = x2 = " + x1);
        } else {
            x1 = (float) ((-b + Math.sqrt(delta)) / (2*a));
            x2 = (float) ((-b - Math.sqrt(delta)) / (2*a));
            System.out.println("x1=" + x1 + "and x2= " + x2);
        }




    }

}
