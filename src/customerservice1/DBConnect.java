/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customerservice1;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Lenovo
 */
public class DBConnect {
    
    private Connection con ;
    private Statement st ;
    private ResultSet rs;
    private static DBConnect DBConnectInstance ;
    // sigleton design pattern to have one instance of class
    private DBConnect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/closet", "root", "");
            st = con.createStatement();
            
        }catch(Exception e){
            System.out.println("Done");
        }
    }
    
    // Ensure a class only has one instance, and provide a global point of access to it.
    public static DBConnect getInstance(){
        if(DBConnectInstance == null ){
            DBConnectInstance = new DBConnect();
            return DBConnectInstance;
        }else{
            return DBConnectInstance;
        }
    }
    
    /*
        get the customers which they are waiting for response (don't have a custoemrService_id)
    */
    public ObservableList<Customer> getWaitingCustomers() throws SQLException{
       ObservableList<Customer>  customers = FXCollections.observableArrayList();
        String query =  "SELECT customer.customer_id , customer.Full_name ,customer.user_name ,"+
                        "customer.email , customer.address , phones.phone , gender.gender_name " +
                        " , chats.chat_id , chats.customerService_id FROM chats JOIN customer JOIN phones JOIN gender " +
                        "ON chats.customer_id = customer.customer_id AND customer.phone_id=phones"+
                        ".phone_id AND customer.gender_id = gender.gender_id "+
                        "where chats.customerService_id=1" ;
        rs = st.executeQuery(query);
        while(rs.next()){
           String fullname = rs.getString("Full_name");
           String username = rs.getString("user_name");
           String email = rs.getString("email");
           String gender = rs.getString("gender_name");
           int customer_id= rs.getInt("customer_id");
           String phone = rs.getString("phone");
           String address = rs.getString("address");
           int chat_id = rs.getInt("chat_id");
           int customerService_id = rs.getInt("customerService_id");
           System.out.println(chat_id);
           customers.add(Customer.getCustomer(fullname, username, phone, address,"", email, customer_id, gender , chat_id , customerService_id));
        }
        return customers;
    }
    
    /*
        check if there a custoerService with these username and password
    */
    public boolean check(String username , String password) throws SQLException {
        rs = getResultSet(username, password);
        if(rs.last())return true;
        else return false;
    }
    
    /*
       get customerService_id given his username and password
    */
    public int getCustomerServiceID(String username , String password) throws SQLException{
        rs = getResultSet(username, password);
        rs.first();
        return rs.getInt("customerService_id");
    }
    
    /*
         get customerService object given customerService_id
    */
    public CustomerService getCustomerService(int customerServiceID) throws SQLException{
        String query = "select * from customer_service where customerService_id = ?" ;
            System.out.println("Done...." + customerServiceID);
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1,customerServiceID );
        rs = ps.executeQuery();
        rs.first();
        return new CustomerService(rs.getString("username") , customerServiceID);
    }
    
    /*
        get a result set contain all information about given customerService username
        and password
    */
    private ResultSet getResultSet(String username , String password) throws SQLException {
        String query = "select * from customer_service where username = ? and password = ?" ;
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet resultSet=ps.executeQuery();
        return resultSet ;
    }
    
    /*
        assign customerService to a new chat
    */
    public void assignCustomerService(int chat_id , int customerService_id) throws SQLException{
        String query = "UPDATE chats SET customerService_id = ? WHERE chat_id = ? " ;
        PreparedStatement ps = con.prepareCall(query);
        ps.setInt(2, chat_id);
        ps.setInt(1, customerService_id);
        ps.executeUpdate();
    }
    
    /*
          send a message into a chat
    */
    public void sendMessage(String message , int chat_id) throws SQLException{
        String query = "INSERT INTO messages (content, type, chat_id) VALUES (?, '0', ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(2, chat_id);
        ps.setString(1, message);
        ps.execute();
    }
    
    /*
          get all the chat given chat_id sorted by date
    */
    public ArrayList<String> getChat(int chat_id) throws SQLException{
        ArrayList<String> chat = new ArrayList<String>();
        String query = "Select content from messages where chat_id = ? " ;
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, chat_id);
        rs = ps.executeQuery();
        while(rs.next()){
            chat.add(rs.getString("content"));
        }
        return chat;
    }
    
    
}
