/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Customer;
import Model.CustomerService;
import Model.DBConnect;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class FeedBackController implements Initializable {

    // controller
    private Customer selectedCustomer ;
    // scene builder 
    @FXML private Label customerID;
    @FXML private Label customerServiceID;
    @FXML private TextArea customerProblem;
    @FXML private TextArea feedback;
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        customerID.setText("DoneYaMahmoud");
    }    
    
    // get Customer Information from chatRoom scene
    public void initData(Customer customer) throws SQLException{
        this.selectedCustomer = customer ;
        this.customerID.setText(Integer.toString(selectedCustomer.getCustomer_id()));
        this.customerServiceID.setText(Integer.toString(selectedCustomer.getCustomerService_id()));
    }
    
    // terminate the chat and return to waitingCustomers scene
    @FXML
    private void changeScene(ActionEvent event) throws IOException, SQLException {
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
    
     /*
      get customerService object given customerService_id
    */
    private CustomerService getCustomerService() throws SQLException{
        DBConnect db = DBConnect.getInstance();
        return db.getCustomerService(selectedCustomer.getCustomerService_id());
    }
    
    
}
