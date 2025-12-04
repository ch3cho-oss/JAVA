public class BankAccount {
    private double balance;

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("存款成功，存入金额：" + amount);
        } else {
            System.out.println("存款金额必须为正数");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0) {
            if (amount <= balance) {
                balance -= amount;
                System.out.println("取款成功，取出金额：" + amount);
            } else {
                System.out.println("余额不足");
            }
        } else {
            System.out.println("取款金额必须为正数");
        }
    }

    public double checkBalance() {
        System.out.println("当前余额：" + balance);
        return balance;
    }

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        BankAccount account = new BankAccount();
        
        while (true) {
            System.out.println("\n1.存款 2.取款 3.查询余额 4.退出");
            System.out.print("选择操作：");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.print("输入存款金额：");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("输入取款金额：");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    account.checkBalance();
                    break;
                case 4:
                    System.out.println("退出系统");
                    scanner.close();
                    return;
                default:
                    System.out.println("无效选择");
            }
        }
    }
}
