import java.net.*;
import java.io.*;
import java.util.Scanner;


public class Main {
    final static int AUTHPORT = 5000;
    final static int SERVPORT = 15001;

    public static void main(String[] args) {
        String serverHostname = "10.7.6.103"; //"192.168.0.100";
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
            System.out.print("Username: ");
            username = userInput.readLine();
            System.out.print("Password: ");
            password = userInput.readLine();

            message = username + ":" +password;
            System.out.println(message);
            out.println(message);

            InputStream inputStream = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

            String response = in.readLine();
            System.out.println("Server response: " + response);

            socket.close();
        } catch (UnknownHostException e) {
            System.err.println("Error: Unknown host " + serverHostname);
        } catch (IOException e) {
            System.err.println("Error: I/O error with server " + serverHostname);
        }
        /*try ( DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName("10.7.6.103");
            int serverPort = 5000;

            System.out.println("Sending message to server");
            String message = "hello world";
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String response = new String(receivePacket.getData());
            System.out.println("Server response: " + response.trim());

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
        /*String serverIP = "192.168.0.100";
        int serverPort = 5000;

        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName(serverIP);

            String username;
            String password;
            byte[] sendBuffer;
            byte[] receiveBuffer = new byte[1024];

            System.out.println("Connected to server");
            System.out.print("Username: ");
            username = userInput.readLine();
            System.out.print("Password: ");
            password = userInput.readLine();

            sendBuffer = (username + "," + password).getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            clientSocket.receive(receivePacket);

            String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());

            if (serverResponse.equals("1")) {
                System.out.println("Authentication successful");
            } else {
                System.out.println("Authentication failed");
            }
        /*try {
            Socket socket = new Socket("192.168.0.100", 5000);
            System.out.println("Connected to server");

            // Get input and output streams
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            // Read username and password from console
            BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Username: ");
            String username = consoleIn.readLine();
            System.out.print("Password: ");
            String password = consoleIn.readLine();

            // Send authentication request to server
            out.writeUTF(username + ":" + password);

            // Keep the connection open until we receive a response from the server
            while (true) {
                // Wait for response from server
                int response = in.readInt();
                int userNum = in.readInt();
                String message = in.readUTF();
                if (response != null) {
                    System.out.println("Authentication failed");
                    break;
                }else {
                    System.out.println("Authentication successful");
                    if (userNum == 1) {
                        System.out.println("You are the first user to connect");
                    } else if (userNum == 2) {
                        System.out.println("You are the second user to connect");
                    }
                }
            }

            socket.close();

            // Print response from server
            // if (response == 0) {
            //     System.out.println("Authentication failed");
            // } else {
            //     System.out.println("Authentication successful");
            //     if (userNum == 1) {
            //         System.out.println("You are the first user to connect");
            //     } else if (userNum == 2) {
            //         System.out.println("You are the second user to connect");
            //     }
            // }

            // Close socket and streams
            socket.close();
            in.close();
            out.close();*/
        /* } catch (IOException e) {
            e.printStackTrace();
        }*/
        // String serverHostname = "192.168.0.100";
        // int serverPort = 5000;
        // String message = "Hello from client";

        // try {
        //     Socket socket = new Socket(serverHostname, serverPort);

        //     OutputStream outputStream = socket.getOutputStream();
        //     PrintWriter out = new PrintWriter(outputStream, true);

        //     out.println(message);

        //     InputStream inputStream = socket.getInputStream();
        //     BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

        //     String response = in.readLine();
        //     System.out.println("Server response: " + response);

        //     socket.close();
        // } catch (UnknownHostException e) {
        //     System.err.println("Error: Unknown host " + serverHostname);
        // } catch (IOException e) {
        //     System.err.println("Error: I/O error with server " + serverHostname);
        // }

        // DatagramSocket socket = null;
        // InetAddress address = null;
        // int port = 0;

        // try {
        //     BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //     System.out.print("Enter the server IP address: ");
        //     String ip = reader.readLine();
        //     System.out.print("Enter the server port number: ");
        //     port = Integer.parseInt(reader.readLine());

        //     System.out.print("Waiting... ");
        //     address = InetAddress.getByName(ip);
        //     socket = new DatagramSocket();
        //     byte[] message = "Hello Server".getBytes();

        //     DatagramPacket sendPacket = new DatagramPacket(message, message.length, address, port);
        //     socket.send(sendPacket);

        //     byte[] buffer = new byte[1000];
        //     DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        //     socket.receive(receivePacket);
        //     String received = new String(receivePacket.getData(), 0, receivePacket.getLength());
        //     System.out.println("\nReceived from server: " + received);

        //     String filePath = args[0];
        //     File file = new File(filePath);
        //     BufferedReader fileReader = new BufferedReader(new FileReader(file));
        //     String fileContent = fileReader.readLine();
        //     fileReader.close();
        //     message = fileContent.getBytes();

        //     sendPacket = new DatagramPacket(message, message.length, address, port);
        //     socket.send(sendPacket);

        //     buffer = new byte[1000];
        //     receivePacket = new DatagramPacket(buffer, buffer.length);
        //     socket.receive(receivePacket);
        //     received = new String(receivePacket.getData(), 0, receivePacket.getLength());
        //     System.out.println("\nThe result of the operation sent is: " + received);

        // } catch (IOException e) {
        //     e.printStackTrace();
        // } finally {
        //     if (socket != null) {
        //         socket.close();
        //     }
        // }
        
        // // READ USER INPUT FORM CLASS USER
        // Scanner scanner = new Scanner(System.in);
        // String message = "0";
        // String coords = "btnxy";
        // Boolean authExitoso = false;
        // Boolean endGame = false;

        // // AUTHENTICATION PROTOCOL UDP
        // while(authExitoso == false){
        //     try {

        //         // Create a UDP socket
        //         DatagramSocket socket = new DatagramSocket();

        //         // Set the destination address and port number  (GET IP ADDRESS)
        //         InetAddress IP = InetAddress.getByName("172.18.2.2"); //192.168.0.100

        //         //InetAddress serverIP = InetAddress.getLocalHost();
        //         //System.out.println("Server IP address: " + serverIP.getHostAddress());


        //         // GET USERNAME AND PASSWORD
        //         System.out.print("Enter your username: ");
        //         String username = scanner.nextLine();        
        //         System.out.print("Enter your password: ");
        //         String pswrd = scanner.nextLine();
        //         // scanner.close();

        //         // Send data to the server
        //         String msg = username+":"+pswrd;
        //         byte[] buffer = msg.getBytes();
        //         DatagramPacket packet = new DatagramPacket(buffer, buffer.length, IP, AUTHPORT);
        //         socket.send(packet);

        //         // Receive data from the server
        //         buffer = new byte[1024];
        //         packet = new DatagramPacket(buffer, buffer.length);
        //         System.out.println("Waiting for data...");
        //         socket.receive(packet);
        //         message = new String(packet.getData(), 0, packet.getLength());
        //         System.out.print("message");
        //         // Close the socket
        //         socket.close();
        //     } catch (Exception e) {
        //         e.printStackTrace();
        //     }
        //     // Stops asking username and password once authenticated
        //     if(message!="0"){
        //         authExitoso = true;
        //     }
        // }
   // }
//}

// // GAME CONNECTION TCP
// try{
//     // Create a socket object and connect to the server
//     Socket socket = new Socket("172.25.0.1", SERVPORT);

//     // Create input and output streams
//     DataInputStream in = new DataInputStream(socket.getInputStream());
//     DataOutputStream out = new DataOutputStream(socket.getOutputStream());
//     while(endGame == false){
//         try {             
//             // ONCE PLAYER HAS SELECTED COORDINATES SEND THEM
//             System.out.print("Coords Btnxy: ");
//             String btn = scanner.nextLine();        
//             System.out.print("Coords y: ");
//             String y = scanner.nextLine();

//             coords = "("+btn+"):";
//             // Send data to the server
//             out.writeUTF(coords);
            
//             // Receive data from the server
//             String message2 = in.readUTF();
//             System.out.println("Server response: " + message2);
            
//             // DESGLOSAR RESPUESTA Y EDITAR CAMPOS DEL UI
            
//             // Close the connection
//             socket.close();
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//         // CHECK END CONDITIONS
//         Boolean end = true;
//         if(end){
//             endGame = true;
//         }
//     }
//         } catch (UnknownHostException e) {
//     e.printStackTrace();
// } catch (IOException e) {
//     e.printStackTrace();
// }
// }
