public class Main {
    public static void main(String[] args) {

        BankAccount account = new BankAccount();
        account.deposit(100);
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.deposit(100);
        System.out.println(savingsAccount.calculateSavingInterest(200, 6));

        DpsAccount dpsAccount = new DpsAccount();
        System.out.println(dpsAccount.calculateDpsInterest(200, 6, 4));

        FixedDepositAccount fixedDepositAccount = new FixedDepositAccount();
        System.out.println(fixedDepositAccount.calculateFixedDepositInterest(200, 6, 4));


    }
}