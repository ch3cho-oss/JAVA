import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8888)) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("输入要发送的消息：");
            String msg = scanner.nextLine();
            
            // 发送消息给服务器
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            out.write(msg);
            out.newLine();
            out.flush();
            
            // 接收服务器回复
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverReply = in.readLine();
            System.out.println("服务器回复：" + serverReply);
            
            scanner.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
