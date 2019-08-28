/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.CustomerService;
import Model.Customer;
import Model.DBConnect;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class FXMLController implements Initializable {

          /**
     * Initializes the controller class.
     */
    
    private CustomerService currentCustomerService;
    
   
    // config the table 
    @FXML private TableView<Customer> tableView ;
    @FXML private TableColumn<Customer,Integer> chat_id;
    @FXML private TableColumn<Customer,String> username; 
    @FXML private TableColumn<Customer,String> fullname;
    
    // start chat button
    @FXML private Button startChatButton ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // config the table properties
        chat_id .setCellValueFactory(new PropertyValueFactory<>("chat_id"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        fullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        try {
            // pass the data for thr table
            tableView.setItems(getWaitingCustomers());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // disable startChat button untill select a customer 
        this.disableButton(startChatButton);
        
    }  
    
    
    // logout and return to login scene
     @FXML
    public void changeScene(ActionEvent event) throws IOException{
        Parent waitingView = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(waitingView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    // start chat with the selected customer in the table view
    @FXML
    public void StartChat(ActionEvent event) throws SQLException{
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("chatRoom.fxml"));
       
        try {
                Parent  waitingView = (Parent)loader.load();
                Scene scene = new Scene(waitingView);
                // pass information to chatRoom scene
               Customer selectedCustomer = prepare(tableView.getSelectionModel().getSelectedItem());
               chatRoomController controller = loader.getController();
               controller.initData(selectedCustomer);
                // create new chat
                createChat(selectedCustomer);
                
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
               
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
       
    }
    
    // enable startChat button after select a customer from the table
    @FXML
    public void enableStartChatButton(){
        this.enableButton(startChatButton);
    }
    
    // disable startChat button untill select a customer from the table
    @FXML
    public void disableStartChatButton(){
        this.disableButton(startChatButton);
    }
    
    //get customers who need to response
    public ObservableList<Customer> getWaitingCustomers() throws SQLException{
       DBConnect connect = DBConnect.getInstance();
       return  connect.getWaitingCustomers();
    }
    
    // pass infornation from login scene
    public void initData(CustomerService currentCustomerService){
        this.currentCustomerService=currentCustomerService;
    }
    
    // assign current customerService to selected chat
    private void createChat(Customer customer) throws SQLException{
        int chat_id = customer.getChat_id();
        int customerService_id = currentCustomerService.getCustomerService_id();
        DBConnect db = DBConnect.getInstance();
        db.assignCustomerService(chat_id,customerService_id);
    }
    
    // prepare customer object to pass for chatRoom scene
    private Customer prepare(Customer selectedCustomer){
        selectedCustomer.setCustomerService_id(currentCustomerService.getCustomerService_id());
        return selectedCustomer;
    }
    
    // disable given button
    private void disableButton(Button button){
        button.setDisable(true);
    }
    
    // enable given button
    private void enableButton(Button button){
        button.setDisable(false);
    }
    
    // update waiting customers table view
    @FXML
    public void updateTable() throws SQLException{
        tableView.setItems(getWaitingCustomers());
    }
    
    
   
}
