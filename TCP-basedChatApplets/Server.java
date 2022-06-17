package part3;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;


public class Server {
    private ServerSocket serverSocket;
    private static Socket socket;

    public Server() throws IOException {
        serverSocket = new ServerSocket(8088);
        System.out.println("等待客户端连接");
        socket = serverSocket.accept();  // 监听
        System.out.println(socket.getInetAddress() + "客户端已连接"); // 客户ip
        work(socket);
    }

    // 服务器任务 读取客户端发送过来的数据
    public void work(Socket socket) throws IOException {
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        Scanner scanner = new Scanner(System.in);
        try {
            InputStream is = socket.getInputStream();
            isr = new InputStreamReader(is, "utf8");
            char[] arr = new char[1024];
            while(true) {
                int len = isr.read(arr);
                String message = new String(arr, 0, len);
                System.out.println(socket.getInetAddress() + ": " + message);

                // 服务器数据发回
                OutputStream os = socket.getOutputStream();
                osw = new OutputStreamWriter(os, "utf8");
                String messageToClient = scanner.next();
                osw.write(messageToClient);
                osw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            osw.close();
            isr.close();
        }
    }

    public static void main(String[] args) throws IOException {
        new Server();
    }
}