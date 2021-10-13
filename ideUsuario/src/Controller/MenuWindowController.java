/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Vista.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;



public class MenuWindowController extends FatherController implements Initializable  {

   
 
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    public void abrirVentanaRegistro(ActionEvent event) {
      abrirVentanaYCerrar(event,"/Vista/UserValidationWindow");
        

    }

    @FXML
    public void abrirVentanaConsultaSuscripcion(ActionEvent event) {
      abrirVentanaYCerrar(event,"/Vista/QuerySubscription");  
    } 
}
