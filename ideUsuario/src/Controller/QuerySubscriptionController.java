/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modelo.JsonCompanyDAO;
import Modelo.JsonNaturalPersonDAO;
import Modelo.JsonSuscriptionDAO;
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
 *
 * @author user
 */
public class QuerySubscriptionController extends FatherController implements Initializable {
    private ToggleGroup tipoIdToggleGroup;
    @FXML
    private TextField identificacionValidacionTF;
    @FXML
    private RadioButton empresaRB;
    @FXML
    private RadioButton personaNaturalRB;
    @FXML
    private Button suscripcionBT;
    private boolean campolleno;
    private JsonSuscriptionDAO consultaSuscripcion;
    private JsonCompanyDAO consultaExistenciaEmpresa;
    private JsonNaturalPersonDAO consultaExistenciaPersona;
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
        consultaSuscripcion = new JsonSuscriptionDAO();
        consultaExistenciaEmpresa = new JsonCompanyDAO();
        consultaExistenciaPersona = new JsonNaturalPersonDAO();

    }

    @FXML
    private void toggleCamposTexto(ActionEvent event) {//dependiendo del radio button presioando se realizan las operaciones

        String id = identificacionValidacionTF.getText();

        if (this.tipoIdToggleGroup.getSelectedToggle().equals(this.personaNaturalRB) && campolleno == true) {
            if (consultaExistenciaPersona.consultar(id)) {
                mensaje("Estado de suscricion", "el cliente id:" + id, consultaSuscripcion.consultarSuscripcionPersona(id)).showAndWait().ifPresent(response -> {//siempre y cuando el boton del mensaje  sea presionado
                    if (response == ButtonType.OK) {
                        identificacionValidacionTF.setText("");;//limpiar campo
                        identificacionValidacionTF.requestFocus();//enfocar el cursor en el campo de texto
                    }
                });
            }else {//si no esta pregunta si quiere registrarse
                mensaje("Alerta", "El cliente no esta registrado\npor lo tanto no tiene cuentas abiertas y activas", "El cliente no ha sido resgitrado\ndesea hace un registro nuevo?").showAndWait().ifPresent(response -> {//siempre y cuando el boton del mensaje  sea presionado
                    if (response == ButtonType.OK) {
                        abrirVentanaYCerrar(event, "/Vista/RegistrationWindow");//cambiar de ventana
                    }
                });
            }
        }

        if (this.tipoIdToggleGroup.getSelectedToggle().equals(this.empresaRB) && campolleno == true) {
            if (consultaExistenciaEmpresa.consultar(id)) {
                mensaje("Estado de suscricion", "el cliente nit:" + id, consultaSuscripcion.consultarSuscripcionEmpresa(id)).showAndWait().ifPresent(response -> {//siempre y cuando el boton del mensaje  sea presionado
                    if (response == ButtonType.OK) {
                        identificacionValidacionTF.setText("");;//limpiar campo
                        identificacionValidacionTF.requestFocus();//enfocar el cursor en el campo de texto
                    }
                });
            } else {//si no esta pregunta si quiere registrarse
                mensaje("Alerta", "El cliente no esta registrado\npor lo tanto no tiene cuentas abiertas y activas", "El cliente no ha sido resgitrado\ndesea hace un registro nuevo?").showAndWait().ifPresent(response -> {//siempre y cuando el boton del mensaje  sea presionado
                    if (response == ButtonType.OK) {
                        abrirVentanaYCerrar(event, "/Vista/RegistrationWindow");//cambiar de ventana
                    }
                });
            }

        }
    }

    @Override
    public void formatTextNumber(TextField text) {//solo numeros en el campo y el guion
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
            mensaje("error ", "error,diligencie el campo", "el campo id/nit  esta vacio").show();
            campolleno = false;
        }

    }

    @FXML
    public void abrirMenuPrincipal(ActionEvent event) {//volver al menu principal
        mensaje("Alerta", "Cancelar consulta", "Esta seguro que quiere volver al menu principal").showAndWait().ifPresent(response -> {//siempre y cuando el boton del mensaje  sea presionado
            if (response == ButtonType.OK) {
                abrirVentanaYCerrar(event, "/Vista/MenuWindow");
            }
        });
    }
}
