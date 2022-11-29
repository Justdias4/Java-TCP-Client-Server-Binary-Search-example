import java.net.*;
import java.io.*;
import java.lang.Math;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        int numberToFind = (int) Math.round( (Math.random()*100) );
        int foundedClients = 0;
        System.out.println("Number to Find :" + numberToFind + "\n");
        while (true){
            Socket clientSocket = serverSocket.accept();
            BufferedReader reader =
                    new BufferedReader(
                        new InputStreamReader(
                            clientSocket.getInputStream()));

            int req = reader.read();

            BufferedWriter writer =
                    new BufferedWriter(
                        new OutputStreamWriter(
                            clientSocket.getOutputStream()));

            int res;
            if(req == numberToFind){
                res = 0;
                System.out.println((++ foundedClients) + " clients already founded the number");
            }else if(req < numberToFind){
                res = -1;
            }else{
                res = 1;
            }

            writer.write(res);
            writer.flush();

            reader.close();
            writer.close();
            clientSocket.close();
        }
    }
}