/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.*;
import java.io.*;

/**
 * FXML Controller class
 *
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private TextField txtUser;
    @FXML private PasswordField txtPass;
    @FXML private Button btnLogin;
    @FXML private Label lblError;

    private final static int AUTHPORT = 5001;
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private PrintWriter out;
    private boolean firstTry;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblError.setVisible(false);
        txtPass.setText("pass1");
        txtUser.setText("user1");
        firstTry = true;
    }    
    
    @FXML
    private void login(ActionEvent event) throws IOException{
        FXMLLoader loader= new FXMLLoader();
        
        String host = InetAddress.getLocalHost().getHostAddress();
        
        if (firstTry) {
            try {
                socket = new Socket(host, AUTHPORT);
                outputStream = socket.getOutputStream();
                out = new PrintWriter(outputStream, true);
                inputStream = socket.getInputStream();
            } catch (UnknownHostException e) {
                System.err.println("Error: Unknown host " + host);
            } catch (IOException e) {
                System.err.println("Error: I/O error with server " + host);
            }
        }
        

        String username = txtUser.getText();
        String password = txtPass.getText();
        String message  = username + ":" + password;

        char response = '2';
        

        out.println(message);
        out.flush();
        response = (char) inputStream.read();

        
        if ( response == '1' ) {
            btnLogin.setDisable(true);
            txtUser.setDisable(true);
            txtPass.setDisable(true);

            lblError.setVisible(true);
            lblError.setText("Waiting for other player to start...");
            lblError.setTextFill(Color.valueOf("#ff9900"));

            // Wait for the other player to authenticate
            Thread waitThread = new Thread(() -> {
                try {
                    int playerN =  Character.getNumericValue((int) inputStream.read());
                    
                    
                    Platform.runLater(() -> {
                        Stage loginScreen = (Stage) txtUser.getScene().getWindow();
                        loginScreen.hide();

                        loader.setLocation(getClass().getResource("Instructions.fxml"));
                            
                        Stage gameStage = new Stage();
                        Parent root = null;
                        try {
                            root = loader.load();
                            InstructionsController controller = loader.getController();
                            controller.initData(playerN);
                            
                            Scene sceneInstructions = new Scene(root);
                            gameStage.setTitle("Battleship");
                            gameStage.getIcons().add(new javafx.scene.image.Image("Img/Visual_assets/Icon.png"));
                            gameStage.setScene(sceneInstructions);
                            gameStage.show();
                        }catch (IOException ex) {
                            Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                } catch (IOException e) {
                    System.err.println("Error: I/O error with server " + host);
                }
            });
            waitThread.start();
        } else if ( response == '0') {
            firstTry = false;
            lblError.setVisible(true);
            lblError.setText("User or passsword incorrect, try again");
        } else {
            lblError.setVisible(true);
            lblError.setText("Server not available in this moment");
        }
    }
}
