/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.UI;

import battleship.Battleship;
import battleship.elements.Aircraft_Carrier;
import battleship.elements.Destroyer;
import battleship.elements.Patrol_Boat;
import battleship.elements.Player;
import battleship.elements.Ship;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 */


public class AttackController implements Initializable {
    @FXML private ToggleButton btnPatBt;
    @FXML private ToggleButton btnDest;
    @FXML private ToggleButton btnAir;
    @FXML private Button btnNext;
    
    @FXML private Label lblPlayer;
    @FXML private Label lblResult;
    @FXML private Label lblAttack;
    @FXML private Label lblPatBt;
    @FXML private Label lblDest;
    @FXML private Label lblAir;
    @FXML private Label lblDestCD;
    @FXML private Label lblAirCD;
    
    @FXML private GridPane attackBoard;
    @FXML private GridPane shipsBoard;
    
    @FXML private Button btn00;
    @FXML private Button btn01;
    @FXML private Button btn02;
    @FXML private Button btn03;
    @FXML private Button btn04;
    @FXML private Button btn05;
    @FXML private Button btn06;
    @FXML private Button btn07;
    
    @FXML private Button btn10;
    @FXML private Button btn11;
    @FXML private Button btn12;
    @FXML private Button btn13;
    @FXML private Button btn14;
    @FXML private Button btn15;
    @FXML private Button btn16;
    @FXML private Button btn17;
    
    @FXML private Button btn20;
    @FXML private Button btn21;
    @FXML private Button btn22;
    @FXML private Button btn23;
    @FXML private Button btn24;
    @FXML private Button btn25;
    @FXML private Button btn26;
    @FXML private Button btn27;
    
    @FXML private Button btn30;
    @FXML private Button btn31;
    @FXML private Button btn32;
    @FXML private Button btn33;
    @FXML private Button btn34;
    @FXML private Button btn35;
    @FXML private Button btn36;
    @FXML private Button btn37;
    
    @FXML private Button btn40;
    @FXML private Button btn41;
    @FXML private Button btn42;
    @FXML private Button btn43;
    @FXML private Button btn44;
    @FXML private Button btn45;
    @FXML private Button btn46;
    @FXML private Button btn47;
   
    @FXML private Button btn50;
    @FXML private Button btn51;
    @FXML private Button btn52;
    @FXML private Button btn53;
    @FXML private Button btn54;
    @FXML private Button btn55;
    @FXML private Button btn56;
    @FXML private Button btn57;
   
    @FXML private Button btn60;
    @FXML private Button btn61;
    @FXML private Button btn62;
    @FXML private Button btn63;
    @FXML private Button btn64;
    @FXML private Button btn65;
    @FXML private Button btn66;
    @FXML private Button btn67;
    
    @FXML private Button btn70;
    @FXML private Button btn71;
    @FXML private Button btn72;
    @FXML private Button btn73;
    @FXML private Button btn74;
    @FXML private Button btn75;
    @FXML private Button btn76;
    @FXML private Button btn77;
    
    @FXML private Button btn80;
    @FXML private Button btn81;
    @FXML private Button btn82;
    @FXML private Button btn83;
    @FXML private Button btn84;
    @FXML private Button btn85;
    @FXML private Button btn86;
    @FXML private Button btn87;
   
    @FXML private Button btn90;
    @FXML private Button btn91;
    @FXML private Button btn92;
    @FXML private Button btn93;
    @FXML private Button btn94;
    @FXML private Button btn95;
    @FXML private Button btn96;
    @FXML private Button btn97;
   
    @FXML private Button btn100;
    @FXML private Button btn101;
    @FXML private Button btn102;
    @FXML private Button btn103;
    @FXML private Button btn104;
    @FXML private Button btn105;
    @FXML private Button btn106;
    @FXML private Button btn107;
    
    @FXML private Button bta00;
    @FXML private Button bta01;
    @FXML private Button bta02;
    @FXML private Button bta03;
    @FXML private Button bta04;
    @FXML private Button bta05;
    @FXML private Button bta06;
    @FXML private Button bta07;
   
    @FXML private Button bta10;
    @FXML private Button bta11;
    @FXML private Button bta12;
    @FXML private Button bta13;
    @FXML private Button bta14;
    @FXML private Button bta15;
    @FXML private Button bta16;
    @FXML private Button bta17;
    
    @FXML private Button bta20;
    @FXML private Button bta21;
    @FXML private Button bta22;
    @FXML private Button bta23;
    @FXML private Button bta24;
    @FXML private Button bta25;
    @FXML private Button bta26;
    @FXML private Button bta27;
    
    @FXML private Button bta30;
    @FXML private Button bta31;
    @FXML private Button bta32;
    @FXML private Button bta33;
    @FXML private Button bta34;
    @FXML private Button bta35;
    @FXML private Button bta36;
    @FXML private Button bta37;
   
    @FXML private Button bta40;
    @FXML private Button bta41;
    @FXML private Button bta42;
    @FXML private Button bta43;
    @FXML private Button bta44;
    @FXML private Button bta45;
    @FXML private Button bta46;
    @FXML private Button bta47;
   
    @FXML private Button bta50;
    @FXML private Button bta51;
    @FXML private Button bta52;
    @FXML private Button bta53;
    @FXML private Button bta54;
    @FXML private Button bta55;
    @FXML private Button bta56;
    @FXML private Button bta57;
   
    @FXML private Button bta60;
    @FXML private Button bta61;
    @FXML private Button bta62;
    @FXML private Button bta63;
    @FXML private Button bta64;
    @FXML private Button bta65;
    @FXML private Button bta66;
    @FXML private Button bta67;
   
    @FXML private Button bta70;
    @FXML private Button bta71;
    @FXML private Button bta72;
    @FXML private Button bta73;
    @FXML private Button bta74;
    @FXML private Button bta75;
    @FXML private Button bta76;
    @FXML private Button bta77;
    
    @FXML private Button bta80;
    @FXML private Button bta81;
    @FXML private Button bta82;
    @FXML private Button bta83;
    @FXML private Button bta84;
    @FXML private Button bta85;
    @FXML private Button bta86;
    @FXML private Button bta87;
  
    @FXML private Button bta90;
    @FXML private Button bta91;
    @FXML private Button bta92;
    @FXML private Button bta93;
    @FXML private Button bta94;
    @FXML private Button bta95;
    @FXML private Button bta96;
    @FXML private Button bta97;
   
    @FXML private Button bta100;
    @FXML private Button bta101;
    @FXML private Button bta102;
    @FXML private Button bta103;
    @FXML private Button bta104;
    @FXML private Button bta105;
    @FXML private Button bta106;
    @FXML private Button bta107;

    private final ToggleGroup btnsShips = new ToggleGroup();
    private Battleship battle = new Battleship();
    private Ship shp;
    private boolean finishTurn;

    final static int PORT = 5001;
    private String host;
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private PrintWriter out;
    
    
    public void initData(Battleship btshp){
        battle = btshp;
        lblPlayer.setText("Player " + battle.getPlyN());
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnPatBt.setToggleGroup(btnsShips);
        btnDest.setToggleGroup(btnsShips);
        btnAir.setToggleGroup(btnsShips);

        btnPatBt.setDisable(true);
        btnDest.setDisable(true);
        btnAir.setDisable(true);
        attackBoard.setDisable(true);
        lblAttack.setVisible(false);
        lblResult.setVisible(false);
        btnNext.setText("Start");

        try {
            host = InetAddress.getLocalHost().getHostAddress();
            socket = new Socket(host, PORT);
            outputStream = socket.getOutputStream();
            inputStream  = socket.getInputStream();
            out = new PrintWriter(outputStream, true);
        } catch (UnknownHostException e) {
            System.err.println("Error: Unknown host " + host);
        } catch (IOException e) {
            System.err.println("Error: I/O error with server " + host);
        }
    }
    
    @FXML
    private void selectShip(ActionEvent event){
        if (finishTurn == false){
            if (btnPatBt.isSelected()== true){
                shp = new Patrol_Boat();
            } else if (btnDest.isSelected() == true){
                shp = new Destroyer();
            } else if(btnAir.isSelected() == true){
                shp = new Aircraft_Carrier();
            }
            attackBoard.setDisable(false);
        }
    }
    
    @FXML
    private void selectSpot(ActionEvent event) throws IOException {
        finishTurn = true;
        if (shp instanceof Destroyer){
            if (battle.getPlyN() == 1){
                battle.getPly1().getShip(Player.Kind.Destroyer).setDisabled(true);
                battle.getPly1().getShip(Player.Kind.Destroyer).setTurnDisabled(battle.getTurn());
            } else {
                battle.getPly2().getShip(Player.Kind.Destroyer).setDisabled(true);
                battle.getPly2().getShip(Player.Kind.Destroyer).setTurnDisabled(battle.getTurn());
            }
        } if (shp instanceof Aircraft_Carrier){
            if (battle.getPlyN() == 1){
                battle.getPly1().getShip(Player.Kind.Aircraft_Carrier).setDisabled(true);
                battle.getPly1().getShip(Player.Kind.Aircraft_Carrier).setTurnDisabled(battle.getTurn());
            } else {
                battle.getPly2().getShip(Player.Kind.Aircraft_Carrier).setDisabled(true);
                battle.getPly2().getShip(Player.Kind.Aircraft_Carrier).setTurnDisabled(battle.getTurn());
            }
        }
        Button btn =  (Button) event.getSource();
        btn.setStyle("-fx-background-color:#000000");
        
        String id = btn.getId();
        char result = attack(id);
        attackDone(result);
    }
    
    @FXML
    private void playAgain(ActionEvent event) throws IOException{
        if (btnNext.getText().equals("Start")) {
                try {
                    host = InetAddress.getLocalHost().getHostAddress();
                    socket = new Socket(host, 5000);
    
                    if (socket != null && !socket.isClosed() && socket.isConnected()) {
                        System.out.println("Socket is connected!");
                        // continue with initialization
                    } else {
                        System.out.println("Socket is closed or not connected!");
                    }
                    outputStream = socket.getOutputStream();
                    inputStream  = socket.getInputStream();
                    out = new PrintWriter(outputStream, true);
                } catch (UnknownHostException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                newAttack();

                btnNext.setText("Play Again");
                btnNext.setVisible(false);
                
                if (battle.getPlyN() == 2){ 
                    btnPatBt.setDisable(true);
                    btnDest.setDisable(true);
                    btnAir.setDisable(true);
                    attackBoard.setDisable(true);
                    lblResult.setVisible(false);
                    lblAttack.setVisible(true);
                    waitingAttack();  
                }
        
    
        } else {

            Stage gameStage = (Stage) btnNext.getScene().getWindow();
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(getClass().getResource("PlaceShips.fxml"));
            Parent root = loader.load();
            Scene newGame = new Scene(root);
            PlaceShipsController controller = loader.getController();
            controller.initData(battle.getPlyN());
            gameStage.setScene(newGame);

        }
    }

    
    private char attack(String id) throws IOException {
        char result;
        int row, column = Character.getNumericValue(id.charAt(id.length()-1));
        
        if (id.length() == 5){
            row = Character.getNumericValue(id.charAt(3));
        } else {
            row = Integer.parseInt(id.substring(3,5));
        }

        if (battle.getPlyN() == 1){
            result = battle.attack(battle.getPly1(), battle.getPly2(), shp, row, column);
        } else{
            result = battle.attack(battle.getPly2(), battle.getPly1(), shp, row, column);
        }
        
        return result;
    }

    private void newAttack() {
        btnPatBt.setDisable(false);
        btnDest.setDisable(false);
        btnAir.setDisable(false);
        attackBoard.setDisable(false);
        lblResult.setText("Your turn!");
        lblResult.setVisible(true);
        lblAttack.setVisible(false);

        
        if (battle.getPlyN() == 1){
            battle.setTurn();
            if (battle.getPly1().getShip(Player.Kind.Destroyer).isDisabled()== true){
                btnDest.setDisable(true);
                lblDestCD.setText("1");
                if (battle.getPly1().getShip(Player.Kind.Destroyer).getTurnDisabled() + 2 == battle.getTurn()){
                    battle.getPly1().getShip(Player.Kind.Destroyer).setDisabled(false);
                    btnDest.setDisable(false);
                    lblDestCD.setText("0");
                }
            } else {
                lblDestCD.setText("0");
            }
            if (battle.getPly1().getShip(Player.Kind.Aircraft_Carrier).isDisabled()== true){
                btnAir.setDisable(true);
                if (battle.getTurn() ==  battle.getPly1().getShip(Player.Kind.Aircraft_Carrier).getTurnDisabled() + 2){
                    lblAirCD.setText("1");
                } else {
                    lblAirCD.setText("2");
                }
                if (battle.getPly1().getShip(Player.Kind.Aircraft_Carrier).getTurnDisabled() + 3 == battle.getTurn()){
                    battle.getPly1().getShip(Player.Kind.Aircraft_Carrier).setDisabled(false);
                    btnAir.setDisable(false);
                    lblAirCD.setText("0");
                }
            } else {
                lblAirCD.setText("0");
            }
            
            lblPatBt.setText(Integer.toString(battle.getPly1().getShip(Player.Kind.Patrol_Boat).getLife()));
            lblDest.setText(Integer.toString(battle.getPly1().getShip(Player.Kind.Destroyer).getLife()));
            lblAir.setText(Integer.toString(battle.getPly1().getShip(Player.Kind.Aircraft_Carrier).getLife()));
        } else {

            if (battle.getPly2().getShip(Player.Kind.Destroyer).isDisabled()== true){
                btnDest.setDisable(true);
                lblDestCD.setText("1");
                if (battle.getPly2().getShip(Player.Kind.Destroyer).getTurnDisabled() + 2 == battle.getTurn()){
                    battle.getPly2().getShip(Player.Kind.Destroyer).setDisabled(false);
                    btnDest.setDisable(false);
                    lblDestCD.setText("0");
                }
            } else {
                lblDestCD.setText("0");
            }
            if (battle.getPly2().getShip(Player.Kind.Aircraft_Carrier).isDisabled()== true){
                btnAir.setDisable(true);
                if (battle.getTurn() ==  battle.getPly2().getShip(Player.Kind.Aircraft_Carrier).getTurnDisabled() + 2){
                    lblAirCD.setText("1");
                    
                } else {
                    lblAirCD.setText("2");
                }
                if (battle.getPly2().getShip(Player.Kind.Aircraft_Carrier).getTurnDisabled() + 3 == battle.getTurn()){
                    battle.getPly2().getShip(Player.Kind.Aircraft_Carrier).setDisabled(false);
                    btnAir.setDisable(false);
                    lblAirCD.setText("0");
                }
            } else {
                lblAirCD.setText("0");
            }
            
            lblPatBt.setText(Integer.toString(battle.getPly2().getShip(Player.Kind.Patrol_Boat).getLife()));
            lblDest.setText(Integer.toString(battle.getPly2().getShip(Player.Kind.Destroyer).getLife()));
            lblAir.setText(Integer.toString(battle.getPly2().getShip(Player.Kind.Aircraft_Carrier).getLife()));
        }
        
        if (Integer.parseInt(lblPatBt.getText()) <= 0){
            btnPatBt.setDisable(true);
        }
        if (Integer.parseInt(lblDest.getText()) <= 0){
            btnDest.setDisable(true);
        }
        if (Integer.parseInt(lblAir.getText()) <= 0){
            btnAir.setDisable(true);
        }
        
        if (btnPatBt.isDisable() == true && btnDest.isDisable() == true && btnAir.isDisable() == true){
            attackBoard.setDisable(true);
            btnNext.setDisable(false); // Check
            lblResult.setText("You can't attack right now.");
        }
            
        finishTurn = false;
        fillBoard(false);
        fillBoard(true);
    }
    
    private void fillBoard(boolean boardShips){
        Button actualSpot;
        
        for (int i = 0; i < 11; i++){
            for (int j = 0; j < 8; j++){
                actualSpot = getSpotCoordinate(i, j, boardShips);
                actualSpot.setStyle("-fx-background-color: " + getColor(i, j, boardShips));
                if ((getColor(i, j, boardShips).equals("#A9A9A9") || getColor(i, j, boardShips).equals("#cc0000")) && boardShips == false){
                    actualSpot.setDisable(true);
                }
            }
        }
    }
    
    private Button getSpotCoordinate(int row, int column, boolean boardShips){
        Button actualSpot;
        if (boardShips == true){
            actualSpot = (Button)lblPlayer.getParent().lookup("#bta"+ String.valueOf(row) + String.valueOf(column));
        } else {
            actualSpot = (Button)lblPlayer.getParent().lookup("#btn"+ String.valueOf(row) + String.valueOf(column));
        }
        return actualSpot;
    }
    
    private String getColor(int row, int column, boolean boardShips){
        String color;
        char symbol;
        
        if (battle.getPlyN() == 1){
            symbol = battle.getPly1().getBoard(boardShips).getPlaces(row, column);
        } else {
            symbol = battle.getPly2().getBoard(boardShips).getPlaces(row, column);
        }
        
        switch(symbol){
            case 'X':
                color = "#cc0000";
                break;
            case 'P':
                color = "#ff9900";
                break;
            case 'S':
                color = "#00cc99";
                break;
            case 'H':
                color = "#A9A9A9";
                break;
            default:
                color = "#99bbff";
                break;
        }
        
        return color;
    }
    
    private void attackDone(int result) throws IOException{
        btnPatBt.setDisable(true);
        btnDest.setDisable(true);
        btnAir.setDisable(true);
        attackBoard.setDisable(true);
        
        switch(result){
            case 'X':
                lblResult.setText("Ship destroyed!");
                break;
            case 'x':
                lblResult.setText("Spot destroyed!");
                break;             
            case 'P':
                lblResult.setText("Spot damaged!");
                break;    
            case 'H':
                lblResult.setText("You missed!");
                break;               
        }

        if (battle.playerWon() != 0){
            String message = "1";
            out.println(message);
            out.flush();
            // response = (char) inputStream.read();
            System.out.println("Player won " + battle.playerWon());
            gameOver(battle.playerWon());
            
        } else {
            lblAttack.setVisible(true);
            try {

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                if (battle.getPlyN() == 1){
                    oos.writeObject(battle.getPly2());
                } else {
                    oos.writeObject(battle.getPly1());
                }
                oos.flush();
                byte[] playerBytes = baos.toByteArray();

                outputStream.write(playerBytes);
                System.out.println("Player sent");
            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            waitingAttack();
        }
    }
    
    private void gameOver(int winner) throws IOException{
        socket.close();
        out.close();
        outputStream.close();
        inputStream.close();

        btnNext.setVisible(true);
        lblResult.setText("Player " + winner + " is the winner");
        lblResult.setVisible(true);
        lblAttack.setVisible(false);

        System.out.println("Game finish ");
    }

    private void waitingAttack() {
        Thread waitThread = new Thread(() -> {System.out.println("Waiting...");
            try {
                // Waiting for response from server
                byte[] buffer = new byte[3500];
                int bytes_read;
                bytes_read = inputStream.read(buffer);

                if (bytes_read > 5) {
                    System.out.println("Player received len " + bytes_read);
                    byte[] received_data = Arrays.copyOfRange(buffer, 0, bytes_read);
                    ObjectInputStream object_stream = new ObjectInputStream(new ByteArrayInputStream(received_data));
                    Player player = (Player) object_stream.readObject();
                    if (battle.getPlyN() == 1){
                        battle.setPly1(player);
                    } else {
                        battle.setPly2(player);
                    }

                    Platform.runLater(() -> {
                        newAttack();
                    });
                } else {

                    int winner = Character.getNumericValue((int) buffer[0]);
                    System.out.println("Player received len " + bytes_read);
                    System.out.println("Player received won " +  winner);
                    Platform.runLater(() -> {
                        try {
                            gameOver(winner);
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    });
                }
            } catch (ClassNotFoundException ex) {
                System.err.println("Error: Class not found ");
            } catch (IOException e) {
                System.err.println("Error: I/O error with server ");
            }
        });
        waitThread.start();
    }

}
