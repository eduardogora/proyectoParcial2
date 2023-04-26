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
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 */
public class SplashController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private ImageView backgroundSS;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        FadeTransition transition = new FadeTransition(Duration.millis(3000), backgroundSS);
        transition.setFromValue(0.8);
        transition.setToValue(0.8);
        transition.play();
        
        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage splashScreen = (Stage) backgroundSS.getScene().getWindow();
                splashScreen.hide();
                Stage gameStage = new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(SplashController.this.getClass().getResource("PlaceShips.fxml")); // Login
                }catch (IOException ex) {
                    Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, ex);
                }

                Scene sceneLogin = new Scene(root);
                gameStage.setTitle("Battleship");
                gameStage.getIcons().add(new javafx.scene.image.Image("Img/Visual_assets/Icon.png"));
                gameStage.setResizable(false);
                gameStage.setScene(sceneLogin);
                gameStage.show();
            }
        });
    }    
    
}
