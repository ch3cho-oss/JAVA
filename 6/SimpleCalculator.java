import java.util.Scanner;

public class SimpleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("输入第一个数：");
        double num1 = scanner.nextDouble();
        System.out.print("输入运算符(+,-,*,/)：");
        char op = scanner.next().charAt(0);
        System.out.print("输入第二个数：");
        double num2 = scanner.nextDouble();
        
        double result;
        switch (op) {
            case '+': result = num1 + num2; break;
            case '-': result = num1 - num2; break;
            case '*': result = num1 * num2; break;
            case '/': 
                if (num2 == 0) {
                    System.out.println("错误：除数不能为0");
                    scanner.close();
                    return;
                }
                result = num1 / num2; 
                break;
            default: 
                System.out.println("错误：无效运算符");
                scanner.close();
                return;
        }
        System.out.printf("结果：%.2f%n", result);
        scanner.close();
    }
}
