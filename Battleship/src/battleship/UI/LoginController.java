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
import javafx.stage.Stage;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblError.setVisible(false);
    }    
    
    @FXML
    private void login(ActionEvent event) throws IOException{
        if ( txtUser.getText().equals("User") && txtPass.getText().equals("User") ) {
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
        } else {
            lblError.setVisible(true);
        }
    }
}
