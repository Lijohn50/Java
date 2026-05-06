public class DpsAccount extends BankAccount {

    double calculateDpsInterest(double balance, double interestRate, int time)
    {
        return balance * (interestRate / 100) * time;
    }
}
