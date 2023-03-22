/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author rfmrm
 */
public class ApplicationBS extends Application{
    public static void main(String[] args) {
        Application.launch(args);
        
    }
    @Override
    public void start(Stage splashScreen) throws Exception {
        Parent rootSS = FXMLLoader.load(getClass().getResource("Splash.fxml"));
        
        Scene sceneSS = new Scene(rootSS);
        
        splashScreen.setTitle("Battleship");
        splashScreen.getIcons().add(new Image("Img/Visual_assets/Icon.png"));
        splashScreen.setResizable(false);
        splashScreen.setScene(sceneSS);
        splashScreen.initStyle(StageStyle.TRANSPARENT);
        splashScreen.show();
    }
    
    
}
