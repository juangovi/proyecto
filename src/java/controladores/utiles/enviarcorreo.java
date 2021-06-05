/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.utiles;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author juana
 */
public class enviarcorreo {

    public enviarcorreo() {
    }
    
    public void sendcorreo(String destinatario, String asunto, String cuerpo){
        String remitente = "vaquerosjd@gmail.com";
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", "Lacontra1");    //La clave de la cuenta
        props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        
          try {
        message.setFrom(new InternetAddress(remitente));
        message.addRecipients(Message.RecipientType.TO, destinatario);  //Se podrían añadir varios de la misma manera
        message.setSubject(asunto);
        message.setContent(cuerpo,"text/html");
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", remitente, "Lacontra1");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
    catch (MessagingException me) {
        me.printStackTrace();   //Si se produce un error
    }
    }
    public void enviarverificacion(String usuario,String token){
        String asunto="verifique su cuenta";
        String link="http://localhost:8084/confirmarcorreo.jsp?tk="+token;
        String cuarpo="<h1>BIENVENIDO A VAQUEROSJD</h1><p>entre en el siguente enlace para verificar su cuenta</p><a href='"+link+"'>verificar cuenta</a>";
        sendcorreo(usuario, asunto, cuarpo);
    }
    
}
