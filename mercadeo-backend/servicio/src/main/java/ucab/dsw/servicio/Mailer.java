package ucab.dsw.servicio;

import org.junit.Test;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.entidades.Usuario;
import java.util.Random;

import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
public class Mailer{
    public static void send(String from,String password,String to,String sub,String msg){
        //Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        //get Session
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from,password);
                    }
                });
        //compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);
            //send message
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {throw new RuntimeException(e);}

    }

    public UsuarioDto generarCodigoRecuperacion(String correo_entrada){
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        UsuarioDto resultado = new UsuarioDto();
        try {
            DaoUsuario dao = new DaoUsuario();
            List<Usuario> usuario = dao.conCorreoUsuario(correo_entrada);
            int random_int = (int) (Math.random() * (99999 - 10000 + 1) + 10000);
            String codigoRec = Integer.toString(random_int);
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            for (Usuario usuarioAux : usuario) {
                usuarioAux.set_codigoRecuperacion(codigoRec);
                Usuario resul = dao.update(usuarioAux);
                resultado.setId(resul.get_id());
                ucab.dsw.servicio.SendMailSSL servicio = new ucab.dsw.servicio.SendMailSSL();
                servicio.enviar(usuarioAux.get_correo(), codigoRec);
            }
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }
}
