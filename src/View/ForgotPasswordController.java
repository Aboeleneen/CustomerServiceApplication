/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ForgotPasswordController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private BorderPane borderPane;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        borderPane.setCenter(GlyphsDude.createIcon(FontAwesomeIcons.LOCK, "180px"));
        
        
    }     
    
}
