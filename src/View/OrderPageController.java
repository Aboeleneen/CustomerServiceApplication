/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.DBConnect;
import Model.Order;
import Model.Product;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class OrderPageController implements Initializable {

   
    @FXML private TableView<Product> tableView;
    @FXML private TableColumn<Product,Integer> product_id;
    @FXML private TableColumn<Product,String> product_name;
    @FXML private TableColumn<Product,Integer> size;
    @FXML private TableColumn<Product,String> color;
    @FXML private TableColumn<Product,String> category;
    @FXML private TableColumn<Product,Double> price;
    @FXML
    private Label orderID_label;
    @FXML
    private Label customerID_label;
    @FXML
    private Label totalPrice_label;
    @FXML
    private Label paymentType_label;
    
    private Order selectedOrder;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // configure the table
        product_id.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        product_name.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        size.setCellValueFactory(new PropertyValueFactory<>("size"));
        color.setCellValueFactory(new PropertyValueFactory<>("color"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        
    }    
    
    
      // get information from chatRoom scene to selected order
    @FXML
    public void initData(Order order) throws SQLException{
        this.selectedOrder = order;
        this.customerID_label.setText(Integer.toString(selectedOrder.getCustomer_id()));
        this.orderID_label.setText(Integer.toString(selectedOrder.getOrder_id()));
        this.paymentType_label.setText(this.getPaymentType());
        this.totalPrice_label.setText(Integer.toString(selectedOrder.getTotal_price()));
        this.tableView.setItems(selectedOrder.getProducts());
    }
    
    
    
    
    
    // get Payment type given paymentID
    public String  getPaymentType() throws SQLException{
        DBConnect db = DBConnect.getInstance();
        return db.getPaymentType(selectedOrder.getPayment_id());       
    }
    
}
