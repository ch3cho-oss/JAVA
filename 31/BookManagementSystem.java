import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Book {
    private String isbn;
    private String name;
    private String author;
    private int stock;

    public Book(String isbn, String name, String author, int stock) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.stock = stock;
    }

    // Getter和Setter方法
    public String getIsbn() { return isbn; }
    public String getName() { return name; }
    public String getAuthor() { return author; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return "ISBN：" + isbn + "，书名：" + name + "，作者：" + author + "，库存：" + stock;
    }
}

public class BookManagementSystem {
    private static ArrayList<Book> books = new ArrayList<>();
    private static final String FILE_PATH = "books.txt";
    private static Scanner scanner = new Scanner(System.in);

    // 添加图书
    public static void addBook() {
        System.out.print("输入ISBN：");
        String isbn = scanner.next();
        System.out.print("输入书名：");
        String name = scanner.next();
        System.out.print("输入作者：");
        String author = scanner.next();
        System.out.print("输入库存数量：");
        int stock = scanner.nextInt();
        
        books.add(new Book(isbn, name, author, stock));
        System.out.println("图书添加成功");
    }

    // 查看所有图书（按ISBN升序）
    public static void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("暂无图书信息");
            return;
        }
        // 按ISBN排序
        Collections.sort(books, Comparator.comparing(Book::getIsbn));
        System.out.println("图书列表：");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // 借书
    public static void borrowBook() {
        System.out.print("输入要借的图书ISBN：");
        String isbn = scanner.next();
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (book.getStock() > 0) {
                    book.setStock(book.getStock() - 1);
                    System.out.println("借书成功！当前库存：" + book.getStock());
                } else {
                    System.out.println("库存不足，借书失败");
                }
                return;
            }
        }
        System.out.println("未找到该ISBN的图书");
    }

    // 还书
    public static void returnBook() {
        System.out.print("输入要还的图书ISBN：");
        String isbn = scanner.next();
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                book.setStock(book.getStock() + 1);
                System.out.println("还书成功！当前库存：" + book.getStock());
                return;
            }
        }
        System.out.println("未找到该ISBN的图书");
    }

    // 保存图书信息到文件
    public static void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Book book : books) {
                writer.write(book.getIsbn() + "," + book.getName() + "," + book.getAuthor() + "," + book.getStock());
                writer.newLine();
            }
            System.out.println("图书信息已保存到文件");
        } catch (IOException e) {
            System.out.println("保存文件失败：" + e.getMessage());
        }
    }

    // 从文件读取图书信息
    public static void readFromFile() {
        books.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String isbn = parts[0];
                    String name = parts[1];
                    String author = parts[2];
                    int stock = Integer.parseInt(parts[3]);
                    books.add(new Book(isbn, name, author, stock));
                }
            }
            System.out.println("已从文件加载图书信息");
        } catch (IOException e) {
            System.out.println("读取文件失败：" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        readFromFile(); // 启动时加载文件
        while (true) {
            System.out.println("\n1.添加图书 2.查看所有图书 3.借书 4.还书 5.保存文件 6.退出");
            System.out.print("选择操作：");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: addBook(); break;
                case 2: viewAllBooks(); break;
                case 3: borrowBook(); break;
                case 4: returnBook(); break;
                case 5: saveToFile(); break;
                case 6:
                    saveToFile(); // 退出时自动保存
                    System.out.println("退出系统");
                    scanner.close();
                    return;
                default: System.out.println("无效选择");
            }
        }
    }
}
