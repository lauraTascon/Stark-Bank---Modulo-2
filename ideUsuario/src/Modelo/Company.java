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
public class Company extends Client {
    
    private String company_nit;
    private String company_name;
    private String cluster;
    private String company_address;

    public Company(String company_nit, String company_name, String cluster, String company_address, String client_id, String phone, String client_name, String client_occupation, String client_address, ArrayList<String> accounts) {
        super(client_id, phone, client_name, client_occupation, client_address, accounts);
        this.company_nit = company_nit;
        this.company_name = company_name;
        this.cluster = cluster;
        this.company_address = company_address;
    }



    public String getCompany_nit() {
        return company_nit;
    }

    public void setCompany_nit(String company_nit) {
        this.company_nit = company_nit;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }
    
  
 
    
    
    
}
