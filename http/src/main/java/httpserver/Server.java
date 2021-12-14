package httpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws IOException {
        Socket socket;
        ServerSocket serverSocket;
        String inputFile = "cookie_file.txt";

        if (args != null && args.length >= 1)
            inputFile = args[0];
        else {
            System.out.println("You didn't provide a file to" +
                    "read the cookies.Will try to read from default file.");
        }

        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        serverSocket = new ServerSocket(3000);
        System.out.println("Server listening at port 3000...");

        try {

            while (true) {
                socket = serverSocket.accept();
                int id = (int) (Math.random()*100);
                HttpclientHandler worker = new HttpclientHandler(socket, id, inputFile);
                threadPool.submit(worker);
            }

        } finally {
           serverSocket.close();
        }
    }
    
}
