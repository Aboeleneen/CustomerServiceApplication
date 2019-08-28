/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.CustomerService;
import Model.Customer;
import Model.DBConnect;
import Model.Order;
import View.FXMLController;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
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
    
    // configure orders table
    @FXML private TableView<Order> tableView;
    @FXML private TableColumn<Order,Integer> orderID_column;
    @FXML private TableColumn<Order,String> brandName_column;
    @FXML private TableColumn<Order,Double> price_column;
    @FXML private TableColumn<Order,Date> date_column;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // configure orders table
        orderID_column.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        brandName_column.setCellValueFactory(new PropertyValueFactory<>("brand_name"));
        price_column.setCellValueFactory(new PropertyValueFactory<>("total_price"));
        date_column.setCellValueFactory(new PropertyValueFactory<>("order_date"));
    }
    
    // get Customer Information from waitingCustomer scene
    public void initData(Customer customer) throws SQLException{
        this.selectedCustomer = customer ;
        this.fullnameLabel.setText(selectedCustomer.getFullname());
        this.username.setText(selectedCustomer.getUsername());
        this.phone.setText(selectedCustomer.getPhone());
        this.email.setText(selectedCustomer.getEmail());
        this.address.setText(selectedCustomer.getAddress());
        this.customer_id.setText(Integer.toString(selectedCustomer.getCustomer_id()));
        this.tableView.setItems(getOrders());
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
    
    // get Orders from database related to selected customer
    private ObservableList<Order> getOrders() throws SQLException{
        DBConnect db = DBConnect.getInstance();
        return db.getOrders(selectedCustomer.getCustomer_id());
    }
    
    // display order page contatin all information about selected order 
    @FXML
    public  void dislay(ActionEvent event) throws IOException, SQLException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("OrderPage.fxml"));
        Parent root = loader.load();
        
         // pass information to orderPage scene
        OrderPageController controller = loader.getController();
        controller.initData(this.tableView.getSelectionModel().getSelectedItem());
        
        Scene scene = new Scene(root);
        Stage orderWindow = new Stage();
        orderWindow.setScene(scene);
        orderWindow.show();
    }
    
    // end current chat and give a feedback about problem then go to waiting customers  scene 
    @FXML
    public void EndChat(ActionEvent event) throws IOException, SQLException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FeedBack.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        // pass information to FeedBack scene
        FeedBackController controller = loader.getController();
        controller.initData(selectedCustomer);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    
}
