public class BankAccount {

    int accountNumber;
    double interestRate;
    double balance;

    void deposit(double amount)
    {
        balance += amount;
    }
    void withdraw(double amount)
    {
        balance -= amount;
    }
    double getBalance()
    {
        return balance;
    }
}
