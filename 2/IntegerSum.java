import java.util.Scanner;

public class IntegerSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("输入第一个整数：");
        int a = scanner.nextInt();
        System.out.print("输入第二个整数：");
        int b = scanner.nextInt();
        System.out.println("两数之和：" + (a + b));
        scanner.close();
    }
}
