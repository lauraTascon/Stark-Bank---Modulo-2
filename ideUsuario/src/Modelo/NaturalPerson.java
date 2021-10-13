/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class NaturalPerson extends Client{

    public NaturalPerson(String client_id, String phone, String client_name, String client_occupation, String client_address, ArrayList<String> accounts) {
        super(client_id, phone, client_name, client_occupation, client_address, accounts);
    }

 

    @Override
    public String toString() {
        return "PersonaNatural{" + "accounts=" + accounts  +  "documentoIdentidad=" + client_id + ", telefono=" + phone + ", nombrePersona=" + client_name + ", ocupacion=" + client_occupation + ", direccion=" + client_address +'}';
    }

   

 

    
    
    
}
