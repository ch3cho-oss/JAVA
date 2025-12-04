import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() { return age; }

    @Override
    public String toString() {
        return "姓名：" + name + "，年龄：" + age;
    }
}

public class PersonSort {
    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person("张三", 25));
        people.add(new Person("李四", 20));
        people.add(new Person("王五", 30));
        
        // 按年龄升序排序
        Collections.sort(people, Comparator.comparingInt(Person::getAge));
        
        System.out.println("按年龄升序排列：");
        for (Person p : people) {
            System.out.println(p);
        }
    }
}
