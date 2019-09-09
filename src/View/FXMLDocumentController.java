/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.CustomerService;
import Model.DBConnect;
import View.FXMLController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Lenovo
 */
public class FXMLDocumentController implements Initializable {
    
    
    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private Label errorMessage;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TOD
    } 
    
    // login and go to waitingCustomers scene
    @FXML
    public void changeScene(ActionEvent event) throws IOException, SQLException{
        // check the validation of username and password 
        if(checkLogin()){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FXML.fxml"));
            Parent  waitingView = (Parent)loader.load();
            Scene scene = new Scene(waitingView);
           
            // pass information to waitingCustomers scene
            FXMLController controller = loader.getController();
            controller.initData(getCustomerService());
          
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
        // invalid username or password
        else{
            this.errorMessage.setText("Wrong username or password");
        }
        
    }
    
    /*
        check if there is a customerService with this username and password
    */
    public boolean checkLogin() throws SQLException{
        String username = this.usernameField.getText();
        String password = this.passwordField.getText();
        DBConnect db = DBConnect.getInstance();
        // check the database 
        return db.check(username, password);
    }
    
    /*
      return customerService object with the same username and customerServiceID
    */
    private CustomerService getCustomerService() throws SQLException{
        String username = this.usernameField.getText();
        String password = this.passwordField.getText();
        DBConnect db = DBConnect.getInstance();
        int customerService_id = db.getCustomerServiceID(username, password);
        return new CustomerService(username,customerService_id);
    }
    
    /**
     * forgot password
     */
    @FXML
    public void forgotPassword(ActionEvent event) throws IOException, SQLException{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ForgotPassword.fxml"));
            Parent  waitingView = (Parent)loader.load();
            Scene scene = new Scene(waitingView);
           
          
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
    }
    
    
}
