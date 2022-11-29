import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Now server is hides one number between 1 and 100");
        System.out.println("Let's try to find it");
        do{
            Socket clientSocket = new Socket("127.0.0.1", 8000);
            Scanner scanner = new Scanner( System.in );
            BufferedWriter writer =
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    clientSocket.getOutputStream()));

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    clientSocket.getInputStream()));
            int req = scanner.nextInt();
            writer.write(req);
            writer.flush();


            int res = reader.read();
            if(res == 0){
                System.out.println("Congrats,You find the number !!!");
                break;
            }else if(res == 1){
                System.out.println("Your number is bigger");
            }else{
                System.out.println("Your number is less");
            }

            writer.close();
            reader.close();
            clientSocket.close();
        }while (true);
    }
}