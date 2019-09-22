/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.CustomerService;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class HomePageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private CustomerService currentCustomerService;
    @FXML
    private BorderPane customerPane;
    @FXML
    private BorderPane brandPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         customerPane.setCenter(GlyphsDude.createIcon(FontAwesomeIcons.USER, "160px"));
         brandPane.setCenter(GlyphsDude.createIcon(FontAwesomeIcons.HOME, "160px"));
    }    
    
     // pass infornation from login scene
    public void initData(CustomerService currentCustomerService){
        this.currentCustomerService=currentCustomerService;
    }
    
    /**
     * waiting customers scene
     */
    @FXML
    public void checkWaitingCustomers(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FXML.fxml"));
            Parent  waitingView = (Parent)loader.load();
            Scene scene = new Scene(waitingView);
           
            // pass information to waitingCustomers scene
            FXMLController controller = loader.getController();
            controller.initData(currentCustomerService);
          
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
    }
    
    /**
     * show reports 
     */
    @FXML
    public void showReports(ActionEvent event) throws IOException{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ReportPage.fxml"));
            Parent  waitingView = (Parent)loader.load();
            Scene scene = new Scene(waitingView);
           
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

    }
}
