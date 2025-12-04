import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
    public static void main(String[] args) {
        Properties props = new Properties();
        String configPath = "config.properties";
        
        try (FileInputStream fis = new FileInputStream(configPath)) {
            props.load(fis);
            String username = props.getProperty("username");
            String password = props.getProperty("password");
            String port = props.getProperty("port", "8080"); // 默认值8080
            
            System.out.println("配置信息：");
            System.out.println("用户名：" + username);
            System.out.println("密码：" + password);
            System.out.println("端口：" + port);
        } catch (IOException e) {
            System.out.println("读取配置文件失败：" + e.getMessage());
        }
    }
}
