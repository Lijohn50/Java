public class FixedDepositAccount extends BankAccount {

    double calculateFixedDepositInterest(double balance, double interestRate, int time )
    {
        return (balance*(1 + interestRate / time) - balance);
    }
}
