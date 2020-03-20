/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author Felix
 * clase / controlador que extraer los datos necesarios del correo que enviar los datos de facturacion 
 * su contraseña y el correo del receptor, la persona encargada de realizar las facturas
 */
public class ControladorEmail {

    
    private String EmailRemitente;
    private String EmailDestinatario;
    private String password;

    
    private File fichero = new File("src/configuraciones/DatosEmail.json");

    public ControladorEmail() {
        try {
            
            JSONParser parser = new JSONParser();
            
            Object obj = parser.parse(new FileReader(fichero.getAbsoluteFile()));
            
            JSONObject jsonobject = (JSONObject) obj;
            //datos del correo y que envia y su contraseña secreta
            EmailRemitente = (String)jsonobject.get("REMITENTE");
            password = (String) jsonobject.get("PASSWORD");
            //datos del correo que recibe el correo
            EmailDestinatario = (String) jsonobject.get("DESTINATARIO");
            
         
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControladorEmail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControladorEmail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ControladorEmail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (java.lang.NullPointerException e) {
            Logger.getLogger(ControladorEmail.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public String getEmailRemitente() {
        return EmailRemitente;
    }

    public void setEmailRemitente(String EmailRemitente) {
        this.EmailRemitente = EmailRemitente;
    }

    public String getEmailDestinatario() {
        return EmailDestinatario;
    }

    public void setEmailDestinatario(String EmailDestinatario) {
        this.EmailDestinatario = EmailDestinatario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public File getFichero() {
        return fichero;
    }

    public void setFichero(File fichero) {
        this.fichero = fichero;
    }



}
