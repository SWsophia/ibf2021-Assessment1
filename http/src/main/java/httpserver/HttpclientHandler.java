package httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;


public class HttpclientHandler implements Runnable
{
    private final Socket socket;
    private int id;
    private String arguments;
    


    public HttpclientHandler(Socket socket, int id, String arguments) {
        this.socket = socket;
        this.id = id;
        this.arguments = arguments;
        
        
    }

    @Override
    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;
        String line = "";
        System.out.println("Connection ID: " + id);

        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            line = in.readLine();
        } catch (IOException ioe) {
            System.out.println("Something went wrong..");
        }

        while (!"close".equals(line) && null != line) {

            System.out.println("Client " + id + ": " + line);
           
            try {

                if ("--port".equals(line)){
                   
                    out.println("Port: 3000");
                    out.flush();
                    line = in.readLine();

                }  
                else if ("--docRoot".equals(line))
                {
                    Path p = Paths.get("/Users/wensun/Desktop/sunwen/ibf2021-Assessment1/http/target");
                    out.println("doc Root is: " + p);
                    out.flush();
                    line = in.readLine();

                }
                else {
                    out.println ("Server received message is " + line);
                    out.flush();
                    line = in.readLine();
                }    
            } catch (Exception e) {
                e.printStackTrace();
                break;
            } 
   
        }

        String[] strArray = arguments.trim().split(",");
        
            
        try {
            while (!arguments.contains("GET"))
                out.println("HTTP/1.1 405 Method Not Allowed\r\n\r\n" + strArray[0]+ " not supported\r\n");
                out.flush();
            socket.close();
           
        } catch (IOException e) {
            e.printStackTrace();
         
        }
        
        try {
            while ("GET".equals(strArray[0]) && !arguments.contains("index.html"))
                out.println("HTTP/1.1 404 Not Found\r\n\r\n" + strArray[1]+ " not found\r\n");
                out.flush();
            socket.close();
           
        } catch (IOException e) {
            e.printStackTrace();
          
        }
        
        try {
            while ("GET".equals(strArray[0]) && !arguments.contains("index.html"))
                out.println("HTTP/1.1 404 Not Found\r\n\r\n" + strArray[1]+ " not found\r\n");
                out.flush();
            socket.close();
           
        } catch (IOException e) {
            e.printStackTrace();
          
        }

    }
    

    
        
    
    
}
