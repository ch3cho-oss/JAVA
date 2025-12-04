import java.lang.reflect.Method;

// 复用第7题的BankAccount类
public class ReflectCallMethod {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = BankAccount.class;
        Object account = clazz.newInstance();
        Method depositMethod = clazz.getMethod("deposit", double.class);
        depositMethod.invoke(account, 500.0); // 调用存款方法
        Method checkBalanceMethod = clazz.getMethod("checkBalance");
        checkBalanceMethod.invoke(account); // 调用查询余额方法
    }
}
