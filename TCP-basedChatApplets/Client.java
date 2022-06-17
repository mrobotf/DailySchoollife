package part3;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private Socket socket;

    public Client() throws IOException {
        socket = new Socket("localhost", 8088);
        work(socket);
    }

    // 客户端任务
    public static void work(Socket socket) throws IOException {
        OutputStreamWriter osw = null;
        InputStreamReader isr = null;

        try {
            Scanner scanner = new Scanner(System.in);
            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();

            // 向服务器发送信息
            osw = new OutputStreamWriter(os, "utf8");

            while(true) {
                String messageOut = scanner.next();
                osw.write(messageOut);
                osw.flush();

                // 接收从服务器传入的信息
                isr = new InputStreamReader(is, "utf8");
                char[] arr = new char[1024];
                int len = isr.read(arr);
                String message = new String(arr, 0, len);
                System.out.println("服务器:" + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            isr.close();
            osw.close();
        }
    }

    public static void main(String[] args) throws IOException {
        new Client();
    }
}