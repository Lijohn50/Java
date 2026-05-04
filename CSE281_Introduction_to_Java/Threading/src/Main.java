//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        PrintEven printEven = new PrintEven(1, 1000);
        PrintOdd printOdd = new PrintOdd(1, 1000);
        printEven.start();
        printOdd.start();
    }
}