/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;


import Modelo.NaturalPerson;
import Modelo.JsonNaturalPersonDAO;
import Modelo.JsonSuscriptionDAO;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.json.simple.JSONObject;

/**
 *
 * @author Usuario
 */
public class Main extends Application {
    
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
        
      /*  try{//ejectutar la ventana de registro
       Parent root = FXMLLoader.load(getClass().getResource("VentanaRegistroUser.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        }catch(IOException ex){
            System.out.println(ex);
        }*/
      
        try{//ejecutar la ventana de menu
        Parent root = FXMLLoader.load(getClass().getResource("MenuWindow.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        }catch(IOException ex){
            System.out.println(ex);
        }
        
       
        
        
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      launch(args);  
    }
    
     
    
    
}
