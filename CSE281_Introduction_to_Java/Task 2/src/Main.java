import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        /*System.out.println("enter your name");
        String name = input.nextLine();

        System.out.println("enter your age");
        int age = input.nextInt();

        System.out.println("enter your height");
        double height = input.nextDouble();

        System.out.println("Hello," + name + "! You are " + age + " years old and your height is " + height + " feet");*/

        /*
        System.out.println("enter your name");
        String name = input.nextLine();

        System.out.println("enter your birth year");
        int year = input.nextInt();

        System.out.println("Hello," + name + "! your birth year is " + year + " and you are " + (2025 - year) + " years old");
        */

        System.out.println("enter the length of the rectangle");
        double length = input.nextDouble();

        System.out.println("enter the width of the rectangle");
        double width = input.nextDouble();

        String name = input.nextLine();
        System.out.println();

        System.out.println("The area of the rectangle is " + (length * width) + " square unit");
    }
}