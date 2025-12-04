import java.lang.reflect.Field;
import java.lang.reflect.Method;

// 复用第8题的Product类
public class ReflectGetClassInfo {
    public static void main(String[] args) {
        Class<?> clazz = Product.class;
        
        // 获取所有属性
        System.out.println("类的属性：");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("- " + field.getType().getSimpleName() + " " + field.getName());
        }
        
        // 获取所有方法
        System.out.println("\n类的方法：");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.print("- " + method.getReturnType().getSimpleName() + " " + method.getName() + "(");
            Class<?>[] paramTypes = method.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) System.out.print(", ");
                System.out.print(paramTypes[i].getSimpleName());
            }
            System.out.println(")");
        }
    }
}
