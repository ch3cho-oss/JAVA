// Shape接口
interface Shape {
    double calculateArea();
    double calculatePerimeter();
    String getShapeName();
    void displayInfo();
}

// 圆形类
class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String getShapeName() {
        return "圆形";
    }

    @Override
    public void displayInfo() {
        System.out.printf("%s - 半径：%.2f，面积：%.2f，周长：%.2f%n",
                getShapeName(), radius, calculateArea(), calculatePerimeter());
    }
}

// 矩形类
class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }

    @Override
    public String getShapeName() {
        return "矩形";
    }

    @Override
    public void displayInfo() {
        System.out.printf("%s - 宽：%.2f，高：%.2f，面积：%.2f，周长：%.2f%n",
                getShapeName(), width, height, calculateArea(), calculatePerimeter());
    }
}

// 三角形类（假设为等腰三角形，需满足两边之和大于第三边）
class Triangle implements Shape {
    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c) {
        if (!isValidTriangle(a, b, c)) {
            throw new IllegalArgumentException("不能构成三角形");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private boolean isValidTriangle(double a, double b, double c) {
        return a + b > c && a + c > b && b + c > a;
    }

    @Override
    public double calculateArea() {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c)); // 海伦公式
    }

    @Override
    public double calculatePerimeter() {
        return a + b + c;
    }

    @Override
    public String getShapeName() {
        return "三角形";
    }

    @Override
    public void displayInfo() {
        System.out.printf("%s - 三边长：%.2f, %.2f, %.2f，面积：%.2f，周长：%.2f%n",
                getShapeName(), a, b, c, calculateArea(), calculatePerimeter());
    }
}

// 测试类
class ShapeManager {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle(5),
                new Rectangle(4, 6),
                new Triangle(3, 4, 5)
        };

        for (Shape shape : shapes) {
            shape.displayInfo();
        }
    }
}
