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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 */
public class InstructionsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private Button btnPlay;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    @FXML
    private void play(ActionEvent event) throws IOException{
        Stage instructionsScreen = (Stage) btnPlay.getScene().getWindow();
        instructionsScreen.hide();
        Stage gameStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(InstructionsController.this.getClass().getResource("PlaceShips.fxml"));
        }catch (IOException ex) {
            Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene sceneShipsPlayer1 = new Scene(root);
        gameStage.setTitle("Battleship");
        gameStage.getIcons().add(new javafx.scene.image.Image("Img/Visual_assets/Icon.png"));
        // gameStage.setResizable(false);
        gameStage.setScene(sceneShipsPlayer1);
        gameStage.show();
    }
}
