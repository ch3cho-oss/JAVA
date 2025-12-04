import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.DoubleStream;

// 抽象员工类
abstract class Employee {
    private String id;
    private String name;
    private double baseSalary;

    public Employee(String id, String name, double baseSalary) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public abstract double calculateSalary();

    public String getEmployeeInfo() {
        return "员工ID：" + id + "，姓名：" + name + "，基础工资：" + baseSalary + "，实际工资：" + calculateSalary();
    }

    // Getter方法
    public String getId() { return id; }
    public double getBaseSalary() { return baseSalary; }
    public double getActualSalary() { return calculateSalary(); }

    // 重写equals和hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

// 全职员工类
class FullTimeEmployee extends Employee {
    private double performanceBonus;

    public FullTimeEmployee(String id, String name, double baseSalary, double performanceBonus) {
        super(id, name, baseSalary);
        this.performanceBonus = performanceBonus;
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + performanceBonus;
    }
}

// 兼职员工类
class PartTimeEmployee extends Employee {
    private double hourlyWage;
    private int workHours;

    public PartTimeEmployee(String id, String name, double baseSalary, double hourlyWage, int workHours) {
        super(id, name, baseSalary); // 兼职基础工资可设为0
        this.hourlyWage = hourlyWage;
        this.workHours = workHours;
    }

    @Override
    public double calculateSalary() {
        return hourlyWage * workHours;
    }
}

// 销售员类
class SalesEmployee extends Employee {
    private double salesAmount;

    public SalesEmployee(String id, String name, double baseSalary, double salesAmount) {
        super(id, name, baseSalary);
        this.salesAmount = salesAmount;
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + salesAmount * 0.05; // 5%提成
    }
}

// 公司类
class Company {
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        if (!employees.contains(employee)) {
            employees.add(employee);
            System.out.println("添加员工成功：" + employee.getEmployeeInfo());
        } else {
            System.out.println("员工ID已存在，添加失败");
        }
    }

    public void removeEmployee(String employeeId) {
        employees.removeIf(emp -> emp.getId().equals(employeeId));
        System.out.println("删除员工ID：" + employeeId);
    }

    public double calculateTotalSalary() {
        return employees.stream().mapToDouble(Employee::calculateSalary).sum();
    }

    public void sortEmployeesBySalary() {
        Collections.sort(employees, Comparator.comparingDouble(Employee::calculateSalary));
        System.out.println("\n按工资升序排列的员工信息：");
        employees.forEach(emp -> System.out.println(emp.getEmployeeInfo()));
    }

    public Employee findHighestSalaryEmployee() {
        return employees.stream().max(Comparator.comparingDouble(Employee::calculateSalary)).orElse(null);
    }

    public Employee findLowestSalaryEmployee() {
        return employees.stream().min(Comparator.comparingDouble(Employee::calculateSalary)).orElse(null);
    }
}

// 测试类
public class EmployeeSalarySystem {
    public static void main(String[] args) {
        Company company = new Company();
        
        // 添加员工
        company.addEmployee(new FullTimeEmployee("001", "张三", 8000, 2000));
        company.addEmployee(new PartTimeEmployee("002", "李四", 0, 50, 80));
        company.addEmployee(new SalesEmployee("003", "王五", 5000, 100000));
        company.addEmployee(new FullTimeEmployee("001", "赵六", 9000, 3000)); // 重复ID
        
        // 计算总工资
        System.out.println("\n公司总工资：" + company.calculateTotalSalary());
        
        // 排序并显示
        company.sortEmployeesBySalary();
        
        // 查找工资最高和最低员工
        Employee highest = company.findHighestSalaryEmployee();
        Employee lowest = company.findLowestSalaryEmployee();
        System.out.println("\n工资最高员工：" + (highest != null ? highest.getEmployeeInfo() : "无"));
        System.out.println("工资最低员工：" + (lowest != null ? lowest.getEmployeeInfo() : "无"));
        
        // 删除员工
        company.removeEmployee("002");
        System.out.println("\n删除后总工资：" + company.calculateTotalSalary());
    }
}
