import java.util.Scanner;
import java.util.TreeSet;

public class TreeSetSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreeSet<Integer> treeSet = new TreeSet<>();
        System.out.print("输入整数（以空格分隔，回车结束）：");
        String[] input = scanner.nextLine().split(" ");
        
        for (String s : input) {
            treeSet.add(Integer.parseInt(s));
        }
        
        System.out.println("自动升序排序结果：" + treeSet);
        scanner.close();
    }
}
