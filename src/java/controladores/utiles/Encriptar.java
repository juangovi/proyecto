/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.utiles;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author juana
 */
public class Encriptar {

    public Encriptar() {
    }
    
    public String encriptacion(Object encrip){
        String textoencriptado = null;
         try {
            String text=encrip.toString();
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            //Add password bytes to digest
            md.update(text.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex formatasdasd
            
            textoencriptado = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
         return textoencriptado;
    }
 
}
