import java.util.Scanner;

public class ArrayReverse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("输入数组长度：");
        int len = scanner.nextInt();
        int[] arr = new int[len];
        
        System.out.println("输入数组元素：");
        for (int i = 0; i < len; i++) {
            arr[i] = scanner.nextInt();
        }
        
        System.out.println("逆序输出：");
        for (int i = len - 1; i >= 0; i--) {
            System.out.print(arr[i] + " ");
        }
        scanner.close();
    }
}
