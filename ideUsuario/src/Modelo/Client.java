/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Client {
    protected String client_id;
    protected String phone;
    protected String client_name;
    protected String client_occupation;
    protected String client_address;
    protected ArrayList<String> accounts;

//arreglo de cuentas;

    public Client(String client_id, String phone, String client_name, String client_occupation, String client_address, ArrayList<String> accounts) {
        this.client_id = client_id;
        this.phone = phone;
        this.client_name = client_name;
        this.client_occupation = client_occupation;
        this.client_address = client_address;
        this.accounts = accounts;
    }


    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_occupation() {
        return client_occupation;
    }

    public void setClient_occupation(String client_occupation) {
        this.client_occupation = client_occupation;
    }

    public String getClient_address() {
        return client_address;
    }

    public void setClient_address(String client_address) {
        this.client_address = client_address;
    }

    public ArrayList<String> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<String> accounts) {
        this.accounts = accounts;
    }

 

    
    
    
}
