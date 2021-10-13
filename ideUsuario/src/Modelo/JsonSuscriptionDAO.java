/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.Main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
public class JsonSuscriptionDAO {
    private JsonNaturalPersonDAO personaConsulta;
    private JsonCompanyDAO empresaConsulta;
    
    public JsonSuscriptionDAO() {
        personaConsulta = new JsonNaturalPersonDAO();
        empresaConsulta = new JsonCompanyDAO();
    }
    
    public JSONObject convertToObjectJson() throws IOException {//leer el jsonCuentasActivas, convertirlo a un objeto y luego pasarlo a la base de datos
        JSONObject jsonObject = null;
        try {
            File file = new File("src/recursos/JSON/cuentasActivas.json");
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

    public String consultarSuscripcionEmpresa(String nit) {//existencia especificamente de una empresa 
        try {
            JSONObject jsonObjectEmpresa = empresaConsulta.convertToObjectJson();//JSONObject de file de las empresaa
            JSONObject jsonCuentasActivas = convertToObjectJson();//obtine el JSONObject actual del file con las cuentas activas
            if (empresaConsulta.consultar(nit)) {
                 return consultaSuscripcion(jsonObjectEmpresa, jsonCuentasActivas, nit);
                
        }    
        } catch (IOException ex) {
            Logger.getLogger(JsonSuscriptionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
  
     public String consultarSuscripcionPersona(String id) {//existencia especificamente de una persona Natural

        try {
            JSONObject jsonObjectPersona = personaConsulta.convertToObjectJson();//JSONObject del file de las personas naturales
            JSONObject jsonCuentasActivas = convertToObjectJson();//obtine el JSONObject actual del file con las cuentas activas

            if (personaConsulta.consultar(id)) {
                return consultaSuscripcion(jsonObjectPersona, jsonCuentasActivas, id);
            }

        } catch (IOException ex) {
            Logger.getLogger(JsonSuscriptionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public String consultaSuscripcion(JSONObject jsonObjeto, JSONObject jsonCuentasActivas, String id) {//metodo para verificar la existencia de un cliente en el json de cuentas
        //abiertas y activas
        boolean clienteSuscrito = false;
        String mensaje = "";
        String cuentas = "";

        for (Object keyStr : jsonObjeto.keySet()) {//for of para moverse dentro del jsonCuentasActivas object con las keys de personas 
            String keyActual = (String) keyStr;//el key actual que actualiza el siclo
            if (id.equals(keyActual)) {//pregunta si el nit exite y si es asi entre 
                Object keyvalue = jsonObjeto.get(keyStr);//convierte el contenido de la key actual en un objeto
                JSONObject objectKey = (JSONObject) keyvalue;//castea el objeto con el contenido actual de la kay a un objeto JSON
                for (Object keySto : objectKey.keySet()) {//for para recorrer el objeto Json actual del cliente existente
                    keyActual = (String) keySto;//modifica la acterior key por las que estan dentro del objeto JSON del cliente existente
                    if (keyActual.equals("accounts")) {//pregunta si la key es igual a "cuentas" para poder posicionarnos en el array 
                        keyvalue = objectKey.get(keySto);//modifica el valor anterior del objeto con el valor del key actual
                        JSONArray cuentasCliente = (JSONArray) keyvalue;//castea el objeto con el contenido actual de la key a un Array de tipo JSON correspondiente 
                        //al array de cuentas del cliente
                        for (int n = 0; n < cuentasCliente.size(); n++) {//for para recorrer el array de cuentas del cliente
                            for (Object keySti : jsonCuentasActivas.keySet()) {//for of para moverse dentro del jsonCuentasActivas object que contiene las cuentas activas
                                keyvalue = jsonCuentasActivas.get(keySti);//creamos un objeto con el contenido del jsonCuentasActivas de cuentas activas
                                JSONArray cuentasActivas = (JSONArray) keyvalue;//castea el objeto con el contenido actual de la key a un Array de tipo JSON correspondiente
                                for (int i = 0; i < cuentasActivas.size(); i++) {//for para recorrer el array de cuentas activas
                                    if (cuentasCliente.get(n).equals(cuentasActivas.get(i))) {//compra que la cuenta del cliente este dentro del array d\
                                        //de cuentas activas
                                        cuentas += cuentasCliente.get(n) + "\n ";//crea una cadena que suma los strings de las cuentas activas
                                        clienteSuscrito = true;
                                        break;
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
        if (clienteSuscrito == true) {
            mensaje = ("\ntiene la/s cuenta/s\n " + cuentas + "activa/s, el cliente esta suscrito");
        } else {
            mensaje = ("el cliente no tiene cuentas activas\nel cliente no esta suscrito");
        }
        return mensaje;
    }
}



