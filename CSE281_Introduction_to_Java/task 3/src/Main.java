import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

       /* System.out.println("Enter two integers: ");
        int x = input.nextInt();
        int y = input.nextInt();

        System.out.println("Sum = " + (x + y));
        System.out.println("Difference = " + (x - y));
        System.out.println("Product = " + (x * y));
        if(y == 0)
        {
            System.out.println("Division by 0 not possible");
        }
        else
        {
            System.out.println("Quotient = " + (x / y));
        }*/

        /*System.out.println("enter a string:");

        String word = input.nextLine();
        int vowel = 0;
        int consonant = 0;
        int space = 0;

        for(int i = 0; i < word.length(); i++)
        {
            if (word.charAt(i) == 'a' || word.charAt(i) == 'e' || word.charAt(i) == 'i' || word.charAt(i) == 'o' || word.charAt(i) == 'u')
            {
                vowel++;
            }
            else if (word.charAt(i) == ' ')
            {
                space++;
            }
        }

        System.out.println("vowels = " + vowel);
        System.out.println("consonant = " + (word.length() - (vowel + space)));*/

        /*int sum = 0;
        for(int i = 1; i <= 100; i++)
        {
            if(i % 2 == 0)
            {
                sum += i;
            }
        }
        System.out.println(sum);*/

        System.out.println("enter a fahrenheit temperature:");

        double temp = input.nextDouble();
        System.out.println("Celsius = " + (temp - 32.0) * 5.0 / 9.0);

        /*System.out.println("enter thee numbers :");
        int num1 = input.nextInt();
        int num2 = input.nextInt();
        int num3 = input.nextInt();

        if(num1 >= num2 && num1 >= num3)
        {
            System.out.println(num1);
        }
        else if(num2 >= num1 && num2 >= num3)
        {
            System.out.println(num2);
        }
        else if(num3 >= num1 && num3 >= num2)
        {
            System.out.println(num3);
        }*/
    }
}