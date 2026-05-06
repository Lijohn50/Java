public class SavingsAccount extends BankAccount {

    double calculateSavingInterest(double balance, double interestRate)
    {
        return balance * interestRate / 100;
    }
}
