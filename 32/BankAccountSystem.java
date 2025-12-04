import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

class BankAccountV2 {
    private String accountId;
    private String username;
    private double balance;

    public BankAccountV2(String accountId, String username, double initialBalance) {
        this.accountId = accountId;
        this.username = username;
        this.balance = initialBalance;
    }

    // 存款（线程安全）
    public synchronized void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(username + " 存款成功，存入：" + amount + "，当前余额：" + balance);
        } else {
            System.out.println("存款金额必须为正数");
        }
    }

    // 取款（线程安全）
    public synchronized void withdraw(double amount) {
        if (amount > 0) {
            if (amount <= balance) {
                balance -= amount;
                System.out.println(username + " 取款成功，取出：" + amount + "，当前余额：" + balance);
            } else {
                System.out.println(username + " 余额不足，取款失败");
            }
        } else {
            System.out.println("取款金额必须为正数");
        }
    }

    // 查询余额（线程安全）
    public synchronized double checkBalance() {
        System.out.println(username + " 的当前余额：" + balance);
        return balance;
    }

    // Getter方法
    public String getAccountId() { return accountId; }
    public String getUsername() { return username; }
    public double getBalance() { return balance; }
}

public class BankAccountSystem {
    private static HashMap<String, BankAccountV2> accounts = new HashMap<>();
    private static final String FILE_PATH = "accounts.txt";
    private static Scanner scanner = new Scanner(System.in);

    // 创建账户
    public static void createAccount() {
        System.out.print("输入账户ID：");
        String accountId = scanner.next();
        if (accounts.containsKey(accountId)) {
            System.out.println("账户ID已存在，创建失败");
            return;
        }
        System.out.print("输入用户名：");
        String username = scanner.next();
        System.out.print("输入初始存款：");
        double initialBalance = scanner.nextDouble();
        BankAccountV2 account = new BankAccountV2(accountId, username, initialBalance);
        accounts.put(accountId, account);
        System.out.println("账户创建成功");
    }

    // 存款操作
    public static void deposit() {
        System.out.print("输入账户ID：");
        String accountId = scanner.next();
        BankAccountV2 account = accounts.get(accountId);
        if (account == null) {
            System.out.println("未找到该账户");
            return;
        }
        System.out.print("输入存款金额：");
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }

    // 取款操作
    public static void withdraw() {
        System.out.print("输入账户ID：");
        String accountId = scanner.next();
        BankAccountV2 account = accounts.get(accountId);
        if (account == null) {
            System.out.println("未找到该账户");
            return;
        }
        System.out.print("输入取款金额：");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }

    // 查询余额
    public static void checkBalance() {
        System.out.print("输入账户ID：");
        String accountId = scanner.next();
        BankAccountV2 account = accounts.get(accountId);
        if (account == null) {
            System.out.println("未找到该账户");
            return;
        }
        account.checkBalance();
    }

    // 保存账户信息到文件
    public static void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (BankAccountV2 account : accounts.values()) {
                writer.write(account.getAccountId() + "," + account.getUsername() + "," + account.getBalance());
                writer.newLine();
            }
            System.out.println("账户信息已保存到文件");
        } catch (IOException e) {
            System.out.println("保存文件失败：" + e.getMessage());
        }
    }

    // 从文件读取账户信息
    public static void readFromFile() {
        accounts.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String accountId = parts[0];
                    String username = parts[1];
                    double balance = Double.parseDouble(parts[2]);
                    accounts.put(accountId, new BankAccountV2(accountId, username, balance));
                }
            }
            System.out.println("已从文件加载账户信息");
        } catch (IOException e) {
            System.out.println("读取文件失败：" + e.getMessage());
        }
    }

    // 测试多线程并发操作
    public static void testMultiThread() {
        BankAccountV2 account = accounts.get("001"); // 假设存在账户001
        if (account == null) {
            System.out.println("请先创建账户001");
            return;
        }

        // 线程1：存款3次，每次100
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                account.deposit(100);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 线程2：取款2次，每次150
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                account.withdraw(150);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        readFromFile();
        while (true) {
            System.out.println("\n1.创建账户 2.存款 3.取款 4.查询余额 5.测试多线程 6.保存文件 7.退出");
            System.out.print("选择操作：");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: createAccount(); break;
                case 2: deposit(); break;
                case 3: withdraw(); break;
                case 4: checkBalance(); break;
                case 5: testMultiThread(); break;
                case 6: saveToFile(); break;
                case 7:
                    saveToFile();
                    System.out.println("退出系统");
                    scanner.close();
                    return;
                default: System.out.println("无效选择");
            }
        }
    }
}
