import java.util.HashMap;
import java.util.Scanner;

public class CharCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("输入一个字符串：");
        String str = scanner.nextLine();
        HashMap<Character, Integer> map = new HashMap<>();
        
        for (char c : str.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        System.out.println("字符出现次数统计：");
        for (HashMap.Entry<Character, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "：" + entry.getValue() + "次");
        }
        scanner.close();
    }
}
