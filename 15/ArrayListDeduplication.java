import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class ArrayListDeduplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        System.out.print("输入数字（以空格分隔，回车结束）：");
        String[] input = scanner.nextLine().split(" ");
        
        for (String s : input) {
            list.add(Integer.parseInt(s));
        }
        
        // 去重：利用HashSet
        HashSet<Integer> set = new HashSet<>(list);
        ArrayList<Integer> deduplicatedList = new ArrayList<>(set);
        
        System.out.println("去重后的列表：" + deduplicatedList);
        scanner.close();
    }
}
