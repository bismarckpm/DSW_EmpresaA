package ucab.dsw.servicio;

//import org.apache.commons.codec.digest.DigestUtils;
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
//import javax.mail.*;
//import javax.mail.internet.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/mailer" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Mailer{
/*    public static void send(String from,String password,String to,String sub,String msg){
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
*/
    @POST
    @Path("/enviarCodigo/{correo_entrada}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public UsuarioDto generarCodigoRecuperacion(@PathParam("correo_entrada") String correo_entrada){
        UsuarioDto resultado = new UsuarioDto();
        try {
            System.out.println(correo_entrada);
            DaoUsuario dao = new DaoUsuario();
            List<Usuario> usuario = dao.conCorreoUsuario(correo_entrada);
            int random_int = (int) (Math.random() * (99999 - 10000 + 1) + 10000);
            String codigoRec = Integer.toString(random_int);
            for (Usuario usuarioAux : usuario) {
                usuarioAux.set_codigoRecuperacion(codigoRec);
                Usuario resul = dao.update(usuarioAux);
                resultado.setId(resul.get_id());
                resultado.setId(usuarioAux.get_id());
                resultado.setCorreo(usuarioAux.get_correo());
                resultado.setNombreUsuario(usuarioAux.get_nombreUsuario());
                resultado.setEstado(usuarioAux.get_estado());
                ucab.dsw.servicio.SendMailSSL servicio = new ucab.dsw.servicio.SendMailSSL();
                servicio.enviar(usuarioAux.get_correo(), codigoRec);
                return  resultado;
            }
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  null;
    }

    @PUT
    @Path( "/validarCodigo" )
    public UsuarioDto validarCodigo(UsuarioDto usuarioDto) {
        UsuarioDto resultado = new UsuarioDto();
        Usuario usuario = new Usuario();
        usuario.set_codigoRecuperacion(usuarioDto.getCodigoRecuperacion());
        usuario.set_correo(usuarioDto.getCorreo());
        try {
            DaoUsuario dao = new DaoUsuario();
            List<Usuario> usuariox = dao.validarCodigo(usuario);
            for (Usuario usuarioAux : usuariox) {
                usuarioAux.set_codigoRecuperacion(null);
                Usuario resul = dao.update(usuarioAux);
                resultado.setId(resul.get_id());
                resultado.setId(usuarioAux.get_id());
                resultado.setCorreo(usuarioAux.get_correo());
                resultado.setNombreUsuario(usuarioAux.get_nombreUsuario());
                resultado.setEstado(usuarioAux.get_estado());
                return resultado;
            }
        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return null;
    }

   /* @PUT
    @Path( "/cambiarPasswordCodigo" )
    public UsuarioDto cambiarPassWordCodigo(UsuarioDto usuarioDto) {
        UsuarioDto resultado = new UsuarioDto();
        Usuario usuario = new Usuario();
        usuario.set_password(DigestUtils.md5Hex(usuarioDto.getPassword()));
        try {
            DaoUsuario dao = new DaoUsuario();
            Usuario usuarioAux = dao.find(usuarioDto.getId(), Usuario.class);
            usuarioAux.set_password(DigestUtils.md5Hex(usuarioDto.getPassword()));
            Usuario resul = dao.update(usuarioAux);
            resultado.setId(resul.get_id());
            resultado.setId(usuarioAux.get_id());
            resultado.setCorreo(usuarioAux.get_correo());
            resultado.setNombreUsuario(usuarioAux.get_nombreUsuario());
            resultado.setEstado(usuarioAux.get_estado());
            return resultado;
        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return null;
    }*/



}