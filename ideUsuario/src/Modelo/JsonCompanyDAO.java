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
public class JsonCompanyDAO{
    
    public JsonCompanyDAO() {
            
    }
    
public JSONObject convertToObjectJson() throws IOException {//leer el json, convertirlo a un objeto y luego pasarlo a la base de datos
        JSONObject jsonObject = null;
        try {
            File file = new File("src/recursos/JSON/clientesEmpresa.json");
            try {
                Object obj;
                obj = new JSONParser().parse(new FileReader(file));//para castear 
                jsonObject = (JSONObject) obj;//castea el objteto a un JSONObject
            } catch (FileNotFoundException ex) {
                Logger.getLogger(JsonNaturalPersonDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonObject;
    }

    public JSONObject contruyeJson(Company empresa) {
        JSONArray accounts = new JSONArray();
        accounts.addAll(empresa.getAccounts());
        JSONObject personaObject = new JSONObject();
        personaObject.put("accounts", accounts);
        personaObject.put("cliet_id", empresa.getClient_id());
        personaObject.put("Phone", empresa.getPhone());
        personaObject.put("client_name", empresa.getClient_name());
        personaObject.put("client_occupation", empresa.getClient_occupation());
        personaObject.put("client_address", empresa.getClient_address());
        personaObject.put("cluster", empresa.getCluster());
      //  personaObject.put("client_address", empresa.getDireccionEmpresa());
        personaObject.put("company_name", empresa.getCompany_name());
        return personaObject;
    }

    public void insertar(Company empresa) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        File file = new File("src/recursos/JSON/clientesEmpresa.json");
        try {
            //
            JSONObject actualJson = convertToObjectJson();//obtiene el json object del file json actual
            JSONObject newJson = contruyeJson(empresa);//obtine el json object del nuevo registro
            
            actualJson.put(empresa.getCompany_nit(), newJson);//añade al json object del file el de el nuevo registro usando el id como key
            String jsonString = actualJson.toString(); //convierte actualJson a un string
          

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
  
  
    public boolean consultar(String nit) {
        boolean existeCliente = false;
        try {

            JSONObject json = convertToObjectJson();//obtine el JSONObject actual del file js
            for (Object keyStr : json.keySet()) {//for of para moverse dentro del json object con las keys
                String keyActual = (String) keyStr;
                if (nit.equals(keyActual)) {
                    //key del objeto
                  //  Object keyvalue = json.get(keyStr);
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
