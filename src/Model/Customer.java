/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static java.time.temporal.TemporalQueries.localDate;

/**
 *
 * @author Lenovo
 */
public class Customer {
    
    private String fullname;
    private String username;
    private String address;
    private String birthday;
    private String email;
    private String phone;
    private String gender;
    private int customer_id;
    private int chat_id;
    private int customerService_id;
    public String getFullname() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    public int getCustomerService_id() {
        return customerService_id;
    }

    public void setCustomerService_id(int customerService_id) {
        this.customerService_id = customerService_id;
    }
    
    
    /*
      * create a new Customer object
    */
    public static Customer getCustomer(String fullname ,String username , String phone , String address ,String birthday ,String email , int customer_id , String gender , int chat_id , int customerService_id){
        Customer customer = new Customer();
        customer.setFullname(fullname);
        customer.setUsername(username);
        customer.setBirthday(birthday);
        customer.setEmail(email);
        customer.setGender(gender);
        customer.setCustomer_id(customer_id);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setChat_id(chat_id);
        return customer ;
    }
    
    
    
}
