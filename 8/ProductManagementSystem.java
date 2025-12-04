import java.util.ArrayList;
import java.util.Scanner;

class Product {
    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getter方法
    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return "商品ID：" + id + "，名称：" + name + "，价格：" + price;
    }
}

public class ProductManagementSystem {
    private static ArrayList<Product> products = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void addProduct() {
        System.out.print("输入商品ID：");
        String id = scanner.next();
        System.out.print("输入商品名称：");
        String name = scanner.next();
        System.out.print("输入商品价格：");
        double price = scanner.nextDouble();
        products.add(new Product(id, name, price));
        System.out.println("商品添加成功");
    }

    public static void queryProducts() {
        if (products.isEmpty()) {
            System.out.println("暂无商品信息");
            return;
        }
        System.out.println("商品列表：");
        for (Product p : products) {
            System.out.println(p);
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1.添加商品 2.查询商品 3.退出");
            System.out.print("选择操作：");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: addProduct(); break;
                case 2: queryProducts(); break;
                case 3: 
                    System.out.println("退出系统");
                    scanner.close();
                    return;
                default: System.out.println("无效选择");
            }
        }
    }
}
