/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Modelo.JsonCompanyDAO;
import Modelo.JsonNaturalPersonDAO;
import Vista.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;


/**
 * FXML Controller class
 *
 * @author user
 */
public class UserValidationWindowController extends FatherController implements Initializable {
    
    private ToggleGroup tipoIdToggleGroup;
    @FXML
    private TextField identificacionValidacionTF;
    @FXML
    private RadioButton empresaRB;
    @FXML
    private RadioButton personaNaturalRB;
    @FXML
    private Button validarBT;
    private boolean campolleno;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       formatTextNumber(identificacionValidacionTF);//formato numerico desde el inicio para evitar errores
       campolleno = true; //para saber cuando el campo no esta vacio 
       tipoIdToggleGroup = new ToggleGroup();
       this.personaNaturalRB.setToggleGroup(tipoIdToggleGroup);
       this.empresaRB.setToggleGroup(tipoIdToggleGroup);
       personaNaturalRB.setSelected(true);
        
    }   
    
    @FXML
    private void toggleCamposTexto(ActionEvent event) {//dependiendo del radio button presioando se activan los campos el formulario
        //saber a que textField debe ir la direccion   
        emptyText(identificacionValidacionTF);
        if (this.tipoIdToggleGroup.getSelectedToggle().equals(this.personaNaturalRB)&& campolleno == true) {
            validarExistenciaPersona(event);
        }
        if (this.tipoIdToggleGroup.getSelectedToggle().equals(this.empresaRB)&& campolleno == true) {
            validarExistenciaEmpresa(event);
        }
        
       
    }
    public void validarExistenciaEmpresa(ActionEvent event){
      
        emptyText(identificacionValidacionTF);
        JsonCompanyDAO validaEmpresa = new JsonCompanyDAO();//crear un objeto de JsonCompanyDAO para hacer la consulta dentro de la base de datos
        if (validaEmpresa.consultar(identificacionValidacionTF.getText()) == false ) {
            mensaje("Alerta","El cliente no esta registrado","El cliente no ha sido resgitrado\ndesea hace un registro nuevo?").showAndWait().ifPresent(response -> {//siempre y cuando el boton del mensaje de registro exitoso sea presionado
                if (response == ButtonType.OK) {
                    abrirVentanaYCerrar(event, "/Vista/RegistrationWindow");//cambiar de ventana
                }
            });
        }

        if (validaEmpresa.consultar(identificacionValidacionTF.getText()) == true ) {
            mensaje("Alerta", "El cliente ya se encuentra registrado","El cliente ya ha sido registrado,\n con anterioridad").showAndWait().ifPresent(response -> {//siempre y cuando el boton del mensaje de registro exitoso sea presionado
                if (response == ButtonType.OK) {
                   identificacionValidacionTF.setText("");  ;//limpiar campo
                   identificacionValidacionTF.requestFocus();//enfocar el cursor en el campo de texto
                }
            });
        }

    }
    
    public void validarExistenciaPersona(ActionEvent event){
      
        emptyText(identificacionValidacionTF);
        JsonNaturalPersonDAO validaPersona = new JsonNaturalPersonDAO();//crear un objeto de JsonNaturalPersonDAO para hacer la consulta dentro de la base de datos
        if (validaPersona.consultar(identificacionValidacionTF.getText()) == false && campolleno == true) {
            mensaje("Alerta", "El cliente no esta registrado","El cliente no ha sido resgitrado\ndesea hace un registro nuevo?").showAndWait().ifPresent(response -> {//siempre y cuando el boton del mensaje  sea presionado
                if (response == ButtonType.OK) {
                    System.out.println("si entre");
                    abrirVentanaYCerrar(event, "/Vista/RegistrationWindow");//cambiar de ventana
                }
            });
        }

        if (validaPersona.consultar(identificacionValidacionTF.getText()) == true && campolleno == true) {
            mensaje("Alerta", "El cliente ya se encuentra registrado","El cliente ya ha sido registrado,\n con anterioridad").showAndWait().ifPresent(response -> {//siempre y cuando el boton del mensaje  sea presionado
                if (response == ButtonType.OK) {
                  identificacionValidacionTF.setText("");  ;//limpiar campo
                  identificacionValidacionTF.requestFocus();//enfocar el cursor en el campo de texto
                }
            });
        }

    }

   

    @Override
    public void formatTextNumber(TextField text) {//solo numeros en el campo
        text.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d-*")) {
                text.setText(newValue.replaceAll("[^\\d-]", ""));
            }
        });

    }

    public void emptyText(TextField text) {//campo vacio y formulario false
        try {
            if (text == null || text.getText().isEmpty()) {
                throw new RuntimeException("Subclasses of Valueables cannot take in an empty String or null value for the \"name\" constructor");
            }
            campolleno = true;
        } catch (RuntimeException exc) {//controla que no este vacio el campo
            System.out.println("error");
           mensaje("error ", "error,diligencie el campo" ,"el campo id/nit  esta vacio").show();
           campolleno = false;
        }

    }
   
    
     @FXML
    public void abrirMenuPrincipal(ActionEvent event) {//volver a la ventana principal
         mensaje("Alerta", "Cancelar consulta","Esta seguro que quiere volver al menu principal").showAndWait().ifPresent(response -> {//siempre y cuando el boton del mensaje  sea presionado
                if (response == ButtonType.OK) {
                  abrirVentanaYCerrar(event,"/Vista/MenuWindow"); 
                }
            });
       
    }
           
}
