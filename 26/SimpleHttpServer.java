import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("HTTP服务器已启动，端口8080");
            while (true) {
                Socket socket = serverSocket.accept();
                handleRequest(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleRequest(Socket socket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             OutputStream out = socket.getOutputStream()) {
            
            // 读取HTTP请求头（仅读取第一行）
            String requestLine = in.readLine();
            System.out.println("收到请求：" + requestLine);
            
            // 构造HTTP响应
            String response = "HTTP/1.1 200 OK\r\n" +
                              "Content-Type: text/html;charset=UTF-8\r\n" +
                              "Content-Length: 20\r\n" +
                              "\r\n" +
                              "<h1>Hello HTTP!</h1>";
            
            out.write(response.getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
