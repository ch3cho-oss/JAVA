import java.io.*;
import java.util.Scanner;

public class FileReadWrite {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("输入要写入文件的内容：");
        String content = scanner.nextLine();
        String filePath = "output.txt";

        // 写入文件
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
            System.out.println("内容已写入文件");
        } catch (IOException e) {
            System.out.println("写入文件失败：" + e.getMessage());
        }

        // 读取文件
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String readContent = reader.readLine();
            System.out.println("从文件读取的内容：" + readContent);
        } catch (IOException e) {
            System.out.println("读取文件失败：" + e.getMessage());
        }
        scanner.close();
    }
}
