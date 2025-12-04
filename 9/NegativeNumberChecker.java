import java.util.Scanner;

class NegativeNumberException extends Exception {
    public NegativeNumberException() {
        super("错误：输入了负数");
    }
}

public class NegativeNumberChecker {
    public static void checkPositive(int num) throws NegativeNumberException {
        if (num < 0) {
            throw new NegativeNumberException();
        }
        System.out.println("输入的正数：" + num);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("输入一个整数：");
        int num = scanner.nextInt();
        try {
            checkPositive(num);
        } catch (NegativeNumberException e) {
            System.out.println(e.getMessage());
        }
        scanner.close();
    }
}
