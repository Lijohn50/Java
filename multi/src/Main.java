//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Even e = new Even(1, 1000);
        Odd o = new Odd(1, 1000);

        e.start();
        o.start();

    }
}