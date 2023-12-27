//Using TCP/IP sockets, write a client – server program to make the client send the file name and to make the server send back the contents of the requested file if present
//java TCPServer
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class TCPServer {
    public static void main(String [] args) {
      try{
        ServerSocket serverSocket= new ServerSocket(1300);
        Socket socket = serverSocket.accept();
        System.out.println("Accepted");
        Scanner socketScanner= new Scanner(socket.getInputStream());
        String filename = socketScanner.nextLine().trim();
        PrintStream printStream = new PrintStream(socket.getOutputStream());
        File file = new File(filename);
        if(file.exists()){
          Scanner fileScanner = new Scanner(file);
          while(fileScanner.hasNextLine()){
            printStream.println(fileScanner.nextLine());
          }
          fileScanner.close();
        }
        else{
          System.out.println("File Does not Exists");
        }
        System.in.read();
        socket.close();
        serverSocket.close();
      }
      catch(IOException e){
        System.out.println(e.getMessage());
      }
    }
}
