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

    private int playerN;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    public void initData(int ply) throws IOException{
        playerN = ply;
    }
    
    @FXML
    private void play(ActionEvent event) throws IOException{
        FXMLLoader loader= new FXMLLoader();
        Stage instructionsScreen = (Stage) btnPlay.getScene().getWindow();
        instructionsScreen.hide();

        loader.setLocation(getClass().getResource("PlaceShips.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            PlaceShipsController controller = loader.getController();
            controller.initData(playerN);
            
            Stage gameStage = new Stage();
            Scene sceneShipsPlayer = new Scene(root);
            gameStage.setTitle("Battleship");
            gameStage.getIcons().add(new javafx.scene.image.Image("Img/Visual_assets/Icon.png"));
            gameStage.setScene(sceneShipsPlayer);
            gameStage.show();
        }catch (IOException ex) {
            Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
