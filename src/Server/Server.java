package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {

        final int PORT = 1889;

        try (ServerSocket server = new ServerSocket(PORT)){
            System.out.println("Сервер запущен!");
            try(Socket socket = server.accept();){
                System.out.println("Клиент подключился");
                try(Scanner in = new Scanner(socket.getInputStream());
                    PrintWriter out = new PrintWriter(socket.getOutputStream(),true);){

                    while (true) {

                        String str = in.nextLine();

                        if(str.equals("/end")){
                            break;
                        }

                        System.out.println("Клиент: " + str);

                        Thread ServerOut = new Thread(()-> ServerMsg(out));

                            ServerOut.start();




                        //                        out.println("Server: "+ str);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        }

    public static void ServerMsg(PrintWriter text){
        Scanner scanner = new Scanner(System.in);
        String msg = scanner.nextLine();

        text.println("Server: " + msg);

    }
}
