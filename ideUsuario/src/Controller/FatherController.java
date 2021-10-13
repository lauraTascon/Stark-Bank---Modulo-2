/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Vista.Main;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class FatherController {

    protected FatherController() {
       
    }

    
    protected void abrirVentanaYCerrar(ActionEvent event, String fxmlDocument) {
        Stage stage = new Stage();
        Parent root = null;
        Button btn = (Button) event.getSource();//obteber el id del botton de registro para saber que escena es y asi cerrarla cuando el registro sea exitoso

        try {
            root = FXMLLoader.load(Main.class.getResource(fxmlDocument + ".fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            Stage primaryStage = (Stage) btn.getScene().getWindow();
            primaryStage.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
     public void formatTextNumber(TextField text) {//solo numeros en el campo
        text.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                text.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

    }

    public void formatText(TextField text) {//permite escribir en el campo solo letras
        text.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                text.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
    }
    
    public Alert mensaje(String title,String mensa,String cont){
        Alert mensaje = new Alert(Alert.AlertType.INFORMATION);//crear ventana de mensaje para el resgistro exitoso
        mensaje.setTitle(title);
        mensaje.setHeaderText(mensa);
        mensaje.setContentText(cont);
       
        return mensaje; 

   }
    
   
    
        
    
}
