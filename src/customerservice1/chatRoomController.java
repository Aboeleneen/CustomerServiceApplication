/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customerservice1;

import customerservice1.FXMLController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class chatRoomController implements Initializable {

    private Customer selectedCustomer;
    private CustomerService currentCustomerService;
    @FXML
    private Label fullnameLabel;
    @FXML
    private Label username;
    @FXML
    private Label customer_id;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label address;
    @FXML
    private TextArea message;
    @FXML
    private ListView chat ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    // get Customer Information from waitingCustomer scene
    @FXML
    public void initData(Customer customer){
        this.selectedCustomer = customer ;
        this.fullnameLabel.setText(selectedCustomer.getFullname());
        this.username.setText(selectedCustomer.getUsername());
        this.phone.setText(selectedCustomer.getPhone());
        this.email.setText(selectedCustomer.getEmail());
        this.address.setText(selectedCustomer.getAddress());
        this.customer_id.setText(Integer.toString(selectedCustomer.getCustomer_id()));
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
    
    /*
         send a new message
    */
    @FXML
    public void sendAmessage(ActionEvent event) throws SQLException{
        DBConnect db = DBConnect.getInstance();
        db.sendMessage(message.getText(), selectedCustomer.getChat_id());
        message.setText("");
        updateChat();
    }
    
    // update chat after each message
    private void updateChat() throws SQLException{
        DBConnect db = DBConnect.getInstance();
        ArrayList<String> messages = db.getChat(selectedCustomer.getChat_id());
        chat.getItems().clear();
        for(String mess :messages){
            chat.getItems().add(mess);
        }
        
    }
    
}
