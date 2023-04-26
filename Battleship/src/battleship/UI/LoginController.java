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

    final static int AUTHPORT = 5000;
    final static String HOST = "10.7.24.222";
    BufferedReader userInput;
    Socket socket;
    OutputStream outputStream;
    InputStream inputStream;
    PrintWriter out;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblError.setVisible(false);
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
    private void login(ActionEvent event) throws IOException{
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
            lblError.setTextFill(Color.GREENYELLOW);

            // Wait for the other player to authenticate
            Thread waitThread = new Thread(() -> {
                try {
                    char response1 = (char) inputStream.read();
                    
                    Platform.runLater(() -> {
                        Stage loginScreen = (Stage) txtUser.getScene().getWindow();
                        loginScreen.hide();
                        Stage gameStage = new Stage();
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(LoginController.this.getClass().getResource("Instructions.fxml"));
                        }catch (IOException ex) {
                            Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        Scene sceneShipsPlayer1 = new Scene(root);
                        gameStage.setTitle("Battleship");
                        gameStage.getIcons().add(new javafx.scene.image.Image("Img/Visual_assets/Icon.png"));
                        // gameStage.setResizable(false);
                        gameStage.setScene(sceneShipsPlayer1);
                        gameStage.show();
                    });
                } catch (IOException e) {
                    System.err.println("Error: I/O error with server " + HOST);
                }
            });
            waitThread.start();
        } else if ( response == '0') {
            lblError.setVisible(true);
            lblError.setText("User or passsword incorrect, try again");
        } else {
            lblError.setVisible(true);
            lblError.setText("Server not available in this moment");
        }
    }
}
