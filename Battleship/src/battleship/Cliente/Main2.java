import java.net.*;
import java.io.*;
import java.util.Scanner;


public class Main2 {
    final static int AUTHPORT = 5000;
    final static int SERVPORT = 15001;

    public static void main(String[] args) {
        String serverHostname = "10.7.24.222"; // "192.168.0.100";// 
        int serverPort = 5000;

        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        String message;
        String username;
        String password;

        try {
            Socket socket = new Socket(serverHostname, serverPort);

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter out = new PrintWriter(outputStream, true);

            System.out.println("Connected to server");

            char response = '0';
            InputStream inputStream = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

            // Until correc auth
            while (response == '0') {
                System.out.print("Username: ");
                username = userInput.readLine().trim();
                System.out.print("Password: ");
                password = userInput.readLine().trim();

                message = username + ":" +password;
                System.out.println(message);
                out.println(message);
                out.flush();

                response = (char) inputStream.read();
                System.out.println("Server response: " + response);
            }
            
            System.out.println("Waiting to start...");

            // Wait for the other player to authenticate
            response = (char) inputStream.read();
            

            System.out.println("Server response: " + response);

            // Both players are authenticated, so start the game
            String start_game = "1";
            out.println(start_game);

            socket.close();
        } catch (UnknownHostException e) {
            System.err.println("Error: Unknown host " + serverHostname);
        } catch (IOException e) {
            System.err.println("Error: I/O error with server " + serverHostname);
        }
    }
}