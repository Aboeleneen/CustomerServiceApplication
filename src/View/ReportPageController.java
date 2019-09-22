/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.CustomerService;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ReportPageController implements Initializable {

     private CustomerService currentCustomerService;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    // pass infornation from login scene
    public void initData(CustomerService currentCustomerService){
        this.currentCustomerService=currentCustomerService;
    }
    
    // return to home page
    @FXML
    public void back(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("HomePage.fxml"));
            Parent  waitingView = (Parent)loader.load();
            Scene scene = new Scene(waitingView);
           
            // pass information to waitingCustomers scene
            HomePageController controller = loader.getController();
            controller.initData(currentCustomerService);
          
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
    }
    
}
