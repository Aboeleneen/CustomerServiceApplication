/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Lenovo
 */
public class CustomerService {
    
    private String username;
    private int customerService_id;
    
    public CustomerService(String username ,int customerService_id){
        this.username=username;
        this.customerService_id=customerService_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCustomerService_id() {
        return customerService_id;
    }

    public void setCustomerService_id(int customerService_id) {
        this.customerService_id = customerService_id;
    }
    
    
    
}
