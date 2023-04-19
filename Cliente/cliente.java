import java.net.*;
import java.io.*;
import java.util.Scanner;


public class TCPClient {
    final int AUTHPORT = 15000
    final int SERVPORT = 15001

    public static void main(String[] args) {
        // READ USER INPUT FORM CLASS USER
        Scanner scanner = new Scanner(System.in);
        String message = "0";
        String coords = "(x,y):A";
        Boolean authExitoso = false;
        Boolean endGame = false;

        // AUTHENTICATION PROTOCOL UDP
        while(authExitoso == false){
            try {

                // Create a UDP socket
                DatagramSocket socket = new DatagramSocket();

                // Set the destination address and port number  (GET IP ADDRESS)
                InetAddress IP = InetAddress.getByName("localhost");

                // GET USERNAME AND PASSWORD
                System.out.print("Enter your username: ");
                String username = scanner.nextLine();        
                System.out.print("Enter your password: ");
                String pswrd = scanner.nextLine();
                scanner.close();

                // Send data to the server
                String msg = username+":"+pswrd;
                byte[] buffer = msg.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, IP, AUTHPORT);
                socket.send(packet);

                // Receive data from the server
                buffer = new byte[1024];
                packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                message = new String(packet.getData(), 0, packet.getLength());

                // Close the socket
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Stops asking username and password once authenticated
            if(message!="0"){
                authExitoso = true;
            }
        }

        // GAME CONNECTION TCP
        // Create a socket object and connect to the server
        Socket socket = new Socket("localhost", SERVPORT);

        // Create input and output streams
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        while(endGame == false){
            try {             
                // ONCE PLAYER HAS SELECTED COORDINATES SEND THEM
                System.out.print("Coords x: ");
                String x = scanner.nextLine();        
                System.out.print("Coords y: ");
                String y = scanner.nextLine();
                System.out.print("Action: ");
                String A = scanner.nextLine();

                coords = "("+x+",+"+y+"):"+A;
                // Send data to the server
                out.writeUTF(coords);
                
                // Receive data from the server
                String message = in.readUTF();
                System.out.println("Server response: " + message);
                
                // DESGLOSAR RESPUESTA Y EDITAR CAMPOS DEL UI
                
                // Close the connection
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // CHECK END CONDITIONS
            Boolean end = true;
            if(end){
                endGame = true;
            }
        }
    }
}
