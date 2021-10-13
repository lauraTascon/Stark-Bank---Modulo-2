/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.Main;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author user
 */
public class JsonNaturalPersonDAO {

    public JsonNaturalPersonDAO() {
        

    }

    public JSONObject convertToObjectJson() throws IOException {//leer el json, convertirlo a un objeto y luego pasarlo a la base de datos
        JSONObject jsonObject = null;
        try {
          
            File file = new File("src/recursos/JSON/clientes.json");
            try {
                Object obj;
                obj = new JSONParser().parse(new FileReader(file));//para castear 
                //System.out.println(obj);
                jsonObject = (JSONObject) obj;//castea el objteto a un JSONObject
            } catch (FileNotFoundException ex) {
                Logger.getLogger(JsonNaturalPersonDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonObject;
    }

    public JSONObject contruyeJson(NaturalPerson persona) {
        JSONArray accounts = new JSONArray();//
        accounts.addAll(persona.getAccounts());//{[]
        JSONObject personaObject = new JSONObject();
        personaObject.put("accounts", accounts);
        personaObject.put("Phone", persona.getPhone());
        personaObject.put("client_name", persona.getClient_name());
        personaObject.put("client_occupation", persona.getClient_occupation());
        personaObject.put("client_address", persona.getClient_address());
        
        return personaObject;
    }

    public void insertar(NaturalPerson persona) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        File file = new File("src/recursos/JSON/clientes.json");
        try {
            //
            JSONObject actualJson = convertToObjectJson();//obtiene el json object del file json actual
            JSONObject newJson = contruyeJson(persona);//obtine el json object del nuevo registro
            
            actualJson.put(persona.getClient_id(), newJson);//añade al json object del file el de el nuevo registro usando el id como key
            String jsonString = actualJson.toString(); //convierte actualJson a un string
          
            //gson
            JsonParser parser = new JsonParser();
            JsonObject json = parser.parse(jsonString).getAsJsonObject();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String prettyJson = gson.toJson(json);//le da formato lindo al print del json 
           
            fw = new FileWriter(file.getAbsoluteFile(), false);//aceptamos sobre escribir el archivo
            bw = new BufferedWriter(fw);
            bw.write(prettyJson);//escribimos el json actual  en el archivo de forma linda 
            System.out.println("agregado correctamente");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        try {
                        //Cierra instancias de FileWriter y BufferedWriter
            if (bw != null)
                bw.close();
            if (fw != null)
                fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
        
    }
  
  
    public boolean consultar(String id) {
        boolean existeCliente = false;
        try {

            JSONObject json = convertToObjectJson();//obtine el JSONObject actual del file js
            for (Object keyStr : json.keySet()) {//for of para moverse dentro del json object con las keys
                String keyActual = (String) keyStr;
                if (id.equals(keyActual)) {
                    //key del objeto
                    Object keyvalue = json.get(keyStr);
                    //Print key and value
                    //System.out.println("key: " + keyStr + " value: " + keyvalue);
                    existeCliente = true;
                    break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(JsonNaturalPersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existeCliente;
    }
}