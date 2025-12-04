import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8888)) {
            System.out.println("服务器已启动，等待客户端连接...");
            Socket socket = serverSocket.accept();
            System.out.println("客户端已连接");
            
            // 读取客户端消息
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientMsg = in.readLine();
            System.out.println("收到客户端消息：" + clientMsg);
            
            // 回复客户端
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            out.write("服务器已收到消息：" + clientMsg);
            out.newLine();
            out.flush();
            
            // 关闭资源
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
