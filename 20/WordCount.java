import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordCount {
    public static void main(String[] args) {
        String filePath = "input.txt";
        int count = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 按空格分割，过滤空字符串
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (!word.isEmpty()) count++;
                }
            }
            System.out.println("文件中的单词数量：" + count);
        } catch (IOException e) {
            System.out.println("读取文件失败：" + e.getMessage());
        }
    }
}
