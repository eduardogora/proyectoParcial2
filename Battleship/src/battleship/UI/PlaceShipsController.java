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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;

import java.net.*;
import java.io.*;

/**
 * FXML Controller class
 *
 */
public class PlaceShipsController implements Initializable {
    
    @FXML private ToggleButton btnPatBt;
    @FXML private ToggleButton btnDest;
    @FXML private ToggleButton btnAir;
    @FXML private Button btnNext;
    
    @FXML private Label lblPlayer;
    @FXML private Label lblPatBt;
    @FXML private Label lblDest;
    @FXML private Label lblAir;
    
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
    
    private final ToggleGroup btnsShips = new ToggleGroup();
    private Battleship battle = new Battleship();
    private Ship shp;
    private boolean spotSelected;
    private ArrayList<String> btnIds;
    private Button spot;
    private int positions[][], coordinates[][];
    
    final static int AUTHPORT = 5000;
    final static String HOST = "10.7.24.222";
    private static final String SerializationUtils = null;
    BufferedReader userInput;
    Socket socket;
    OutputStream outputStream;
    InputStream inputStream;
    PrintWriter out;

    public void initData(Battleship btshp, String ply){
        battle = btshp;
        lblPlayer.setText(ply);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        spotSelected = false;
        btnIds = new ArrayList<>();
        coordinates = new int[2][2];
                
        btnPatBt.setToggleGroup(btnsShips);
        btnDest.setToggleGroup(btnsShips);
        btnAir.setToggleGroup(btnsShips);
        btnPatBt.setSelected(true);
        
        shp = new Patrol_Boat();
        
        btnNext.setDisable(true);

        userInput = new BufferedReader(new InputStreamReader(System.in));
        try {
            socket = new Socket(HOST, AUTHPORT);
            outputStream = socket.getOutputStream();
            out = new PrintWriter(outputStream, true);
            inputStream = socket.getInputStream();
        } catch (UnknownHostException e) {
            System.err.println("Error: Unknown host " + HOST);
        } catch (IOException e) {
            System.err.println("Error: I/O error with server " + HOST);
        }
    }   
    
    @FXML
    private void selectShip(ActionEvent event){
        if (btnPatBt.isSelected()== true){
            shp = new Patrol_Boat();
        } else if (btnDest.isSelected() == true){
            shp = new Destroyer();
        } else if(btnAir.isSelected() == true){
            shp = new Aircraft_Carrier();
        }
    }
      
    @FXML
    private void placeShip(ActionEvent event) throws IOException{
        Button btn =  (Button) event.getSource();
        String id = btn.getId();
        int i;
        if (spotSelected == false && !(shp ==null)){
            btn.setStyle("-fx-background-color:#cc0000");
            spotSelected = true;
            btnPatBt.setDisable(true);
            btnDest.setDisable(true);
            btnAir.setDisable(true);
        
            int row, column = Character.getNumericValue(id.charAt(id.length()-1));
        
            if (id.length() == 5){
                row = Character.getNumericValue(id.charAt(3));
            } else {
                row = Integer.parseInt(id.substring(3,5));
            }
            
            coordinates[0][0] = column;
            coordinates[0][1] = row;
            
            if (lblPlayer.getText().equals("Player 1")){
                positions = battle.endPositions(column, row, battle.getPly1(), shp);
            } else {
                positions = battle.endPositions(column, row, battle.getPly2(), shp);
            }
        
            for(i = 0; i < 4; i++){
                if (positions[i][0] != -1){
                    btnIds.add("#btn" + String.valueOf(positions[i][1]) + String.valueOf(positions[i][0]));
                }
            }
        
            for(i = 0; i < btnIds.size(); i++){
                if (i == 0){
                    spot = btn;
                }
                btn = (Button)btn.getParent().lookup(btnIds.get(i));
                btn.setStyle("-fx-background-color:#ff9900");
            }
            
        } else if (spotSelected == true && btnIds.indexOf("#" + id) != -1){
            int row, column = Character.getNumericValue(id.charAt(id.length()-1));
            Button actualSpot;
            
            if (id.length() == 5){
                row = Character.getNumericValue(id.charAt(3));
            } else {
                row = Integer.parseInt(id.substring(3,5));
            }
            
            coordinates[1][0] = column;
            coordinates[1][1] = row;
            
            if (lblPlayer.getText().equals("Player 1")){
                battle.getPly1().setLocation(coordinates, shp);
            } else {
                battle.getPly2().setLocation(coordinates, shp);
            }
            
            boolean vertical = true, right = true, down = true;
            
            if (coordinates[0][0] == coordinates[1][0]){
                
                if (coordinates[0][1] > coordinates[1][1]){
                    down = false;
                }
            } else {
                vertical = false;
                if (coordinates[0][0] > coordinates[1][0]){
                    right = false;
                }
            }
            
            if (shp instanceof Patrol_Boat){
                if (vertical == true){
                    spot.setStyle("-fx-background-color: #00cc99");
                    btn.setStyle("-fx-background-color: #00cc99");
                } else {
                    spot.setStyle("-fx-background-color:#00cc99");
                    btn.setStyle("-fx-background-color:#00cc99");
                }
                enableBtnsShips(btnPatBt, lblPatBt);
            } else if (shp instanceof Destroyer){
                if (vertical == true){
                    spot.setStyle("-fx-background-color: #00cc99");
                    btn.setStyle("-fx-background-color: #00cc99");
                    if (down == true){
                        actualSpot = (Button)btn.getParent().lookup("#btn"+ String.valueOf(row-1) + String.valueOf(column));
                    } else {
                        actualSpot = (Button)btn.getParent().lookup("#btn"+ String.valueOf(row+1) + String.valueOf(column));
                    }
                    actualSpot.setStyle("-fx-background-color: #00cc99");
                } else {
                    spot.setStyle("-fx-background-color: #00cc99");
                    btn.setStyle("-fx-background-color: #00cc99");
                    if (right == true){
                        actualSpot = (Button)btn.getParent().lookup("#btn"+ String.valueOf(row) + String.valueOf(column-1));
                    } else {
                        actualSpot = (Button)btn.getParent().lookup("#btn"+ String.valueOf(row) + String.valueOf(column+1));
                    }
                    actualSpot.setStyle("-fx-background-color: #00cc99");
                }
                actualSpot.setDisable(true);
                enableBtnsShips(btnDest, lblDest);
            } else {
                if (vertical == true){
                    spot.setStyle("-fx-background-color: #00cc99");
                    btn.setStyle("-fx-background-color: #00cc99");
                    if (down == true){
                        actualSpot = (Button)btn.getParent().lookup("#btn"+ String.valueOf(row-1) + String.valueOf(column));
                        disableSpot(actualSpot);
                        actualSpot = (Button)btn.getParent().lookup("#btn"+ String.valueOf(row-2) + String.valueOf(column));
                        disableSpot(actualSpot);
                        actualSpot = (Button)btn.getParent().lookup("#btn"+ String.valueOf(row-3) + String.valueOf(column));
                        disableSpot(actualSpot);
                    } else {
                        actualSpot = (Button)btn.getParent().lookup("#btn"+ String.valueOf(row+1) + String.valueOf(column));
                        disableSpot(actualSpot);
                        actualSpot = (Button)btn.getParent().lookup("#btn"+ String.valueOf(row+2) + String.valueOf(column));
                        disableSpot(actualSpot);
                        actualSpot = (Button)btn.getParent().lookup("#btn"+ String.valueOf(row+3) + String.valueOf(column));
                        disableSpot(actualSpot);
                    }
                } else {
                    spot.setStyle("-fx-background-color:#00cc99");
                    btn.setStyle("-fx-background-color:#00cc99");
                    if (right == true){
                        actualSpot = (Button)btn.getParent().lookup("#btn"+ String.valueOf(row) + String.valueOf(column-1));
                        disableSpot(actualSpot);
                        actualSpot = (Button)btn.getParent().lookup("#btn"+ String.valueOf(row) + String.valueOf(column-2));
                        disableSpot(actualSpot);
                        actualSpot = (Button)btn.getParent().lookup("#btn"+ String.valueOf(row) + String.valueOf(column-3));
                        disableSpot(actualSpot);
                    } else {
                        actualSpot = (Button)btn.getParent().lookup("#btn"+ String.valueOf(row) + String.valueOf(column+1));
                        disableSpot(actualSpot);
                        actualSpot = (Button)btn.getParent().lookup("#btn"+ String.valueOf(row) + String.valueOf(column+2));
                        disableSpot(actualSpot);
                        actualSpot = (Button)btn.getParent().lookup("#btn"+ String.valueOf(row) + String.valueOf(column+3));
                        disableSpot(actualSpot);
                    }
                }
                enableBtnsShips(btnAir, lblAir);
            }
            
            spot.setDisable(true);
            btn.setDisable(true);
            
            
            for(i = 0; i < btnIds.size(); i++){
                if (!btnIds.get(i).equals("#" + id)){
                    btn = (Button)btn.getParent().lookup(btnIds.get(i));
                    btn.setStyle("-fx-background-color: #99bbff");
                }
            }
            
            btnIds.clear();
            spotSelected = false;
            shp = null;
            
        } else if (spotSelected == true  && !(shp ==null)) {
            btn.setStyle("-fx-background-color:#cc0000");
            spot.setStyle("-fx-background-color: #99bbff");
            
            Button btnTrans;
            for(i = 0; i < btnIds.size(); i++){
                if (!btnIds.get(i).equals("#" + id)){
                    btnTrans = (Button)btn.getParent().lookup(btnIds.get(i));
                    btnTrans.setStyle("-fx-background-color: #99bbff");
                }
            }
            
            btnIds.clear();
            spotSelected = true;
            btnPatBt.setDisable(true);
            btnDest.setDisable(true);
            btnAir.setDisable(true);
        
            int row, column = Character.getNumericValue(id.charAt(id.length()-1));
        
            if (id.length() == 5){
                row = Character.getNumericValue(id.charAt(3));
            } else {
                row = Integer.parseInt(id.substring(3,5));
            }
            
            coordinates[0][0] = column;
            coordinates[0][1] = row;
            
            if (lblPlayer.getText().equals("Player 1")){
                positions = battle.endPositions(column, row, battle.getPly1(), shp);
            } else {
                positions = battle.endPositions(column, row, battle.getPly2(), shp);
            }
        
            for(i = 0; i < 4; i++){
                if (positions[i][0] != -1){
                    btnIds.add("#btn" + String.valueOf(positions[i][1]) + String.valueOf(positions[i][0]));
                }
            }
        
            for(i = 0; i < btnIds.size(); i++){
                if (i == 0){
                    spot = btn;
                }
                btn = (Button)btn.getParent().lookup(btnIds.get(i));
                btn.setStyle("-fx-background-color:#ff9900");
            }
        }
    }
    
    @FXML
    private void nextPlayer(ActionEvent event) throws IOException{
        Stage gameStage = (Stage) btnNext.getScene().getWindow();
        FXMLLoader loader= new FXMLLoader();
        
        if (lblPlayer.getText().equals("Player 1")){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(battle.getPly1());
            oos.flush();
            byte[] playerBytes = baos.toByteArray();

            System.out.println("Player sent");

            try {
                outputStream.write(playerBytes);

                byte[] buffer = new byte[65535];
                InputStream in = socket.getInputStream();

                
                int bytes_read;
                bytes_read = in.read(buffer);
                
                //byte_stream.write(buffer, 0, bytes_read);
                
                System.out.println("Player received len " + bytes_read);
                
                
                //byte[] received_data = byte_stream.toByteArray();
                byte[] received_data = Arrays.copyOfRange(buffer, 0, bytes_read);
                
                
                ObjectInputStream object_stream = new ObjectInputStream(new ByteArrayInputStream(received_data));
                Player player = (Player) object_stream.readObject();

                /*
                byte[] buffer = new byte[1024];
                InputStream in = socket.getInputStream();
                int bytes_read = in.read(buffer);
                byte[] received_data = new byte[bytes_read];
                System.arraycopy(buffer, 0, received_data, 0, bytes_read);

                System.out.println("Player received");
                ByteArrayInputStream byte_stream = new ByteArrayInputStream(received_data);
                ObjectInputStream object_stream = new ObjectInputStream(byte_stream);
                Player player = (Player) object_stream.readObject();*/

                

                //byte[] buffer = new byte[1024];
                //ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                
                //int bytes_read = in.read(buffer);
                //byte[] received_data = new byte[bytes_read];
                //System.arraycopy(buffer, 0, received_data, 0, bytes_read);


                /*ByteArrayInputStream byteStream = new ByteArrayInputStream(received_data);
                ObjectInputStream objectStream = new ObjectInputStream(byteStream);

                // Deserialize the object from the byte stream
                Player player = (Player) objectStream.readObject();*/
                //Player player = (Player) SerializationUtils.deserialize(received_data); // objectStream.readObject();

                //Player player = (Player) in.readObject();
                System.out.println("Player received2");


                /*InputStream inputStream = socket.getInputStream();
                char response1 = (char) inputStream.read();
                Player player = (Player) in.readObject();
                System.out.println("Player received" + response1);
                */
                // battle.setPly2(player); // Assumes Player is the class being sent
                // Do something with player object
                // in.close();

                socket.close();
            } catch (ClassNotFoundException ex) {
                System.err.println("Error: Class not found ");
            } catch (IOException e) {
                System.err.println("Error: I/O error with server " + HOST);
            }


            /*
            loader.setLocation(getClass().getResource("PlaceShips.fxml"));
            Parent root = loader.load();
            Scene sceneShipsPlayer2 = new Scene(root);
            PlaceShipsController controller = loader.getController();
            controller.initData(battle, "Player 2");
            gameStage.setScene(sceneShipsPlayer2);
            */
        } else {
            loader.setLocation(getClass().getResource("Attack.fxml"));
            Parent root = loader.load();
            Scene gameScene = new Scene(root);
            AttackController controller = loader.getController();
            controller.initData(battle, "Player 1");
            gameStage.setScene(gameScene);
        }

        System.out.println("Wait");
        
    }
    
    private void enableBtnsShips(ToggleButton btn, Label lbl){
        lbl.setText("x0");
        btn.setSelected(false);
        enableBtn(btnPatBt, lblPatBt);
        enableBtn(btnDest, lblDest);
        enableBtn(btnAir, lblAir);
        if (lblPatBt.getText().equals("x0") && lblDest.getText().equals("x0") && lblAir.getText().equals("x0")){
            btnNext.setDisable(false);
        }
    }
    
    private void enableBtn(ToggleButton btn, Label lbl){
        if (!lbl.getText().equals("x0")){
            btn.setDisable(false);
        }
    }
    
    private void disableSpot(Button btn){
        btn.setStyle("-fx-background-color: #00cc99");
        btn.setDisable(true);
    }
    
}
