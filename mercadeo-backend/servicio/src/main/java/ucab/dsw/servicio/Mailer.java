package ucab.dsw.servicio;

import org.apache.commons.codec.digest.DigestUtils;
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
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path( "/mailer" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Mailer{

    private static Logger logger = LoggerFactory.getLogger(Mailer.class);
    /**
     * Este método envía un correo electrónico a una dirección de correo específica
     *
     * @param  from dirección de correo desde la cual será enviado el correo electrónico
     * @param  password contraseña del correo que enviará el mensaje
     * @param  to dirección de correo destino del mensaje
     * @param  sub título del correo electrónico a enviar
     * @param  msg contenido o cuerpo del correo electrónico a enviar
     * @return      la pregunta_encuestaDto con la que ese relacionan las respuestas agregadas
     */
    public static void send(String from,String password,String to,String sub,String msg){
        BasicConfigurator.configure();
        logger.debug("Entrando al método que envía un correo electrónico");
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

    /**
     * Este método genera el código de recuperación de contraseña de un usuario, se lo asigna y lo envía
     * a su correo electrónico
     *
     * @param  correo_entrada correo electrónico del usuario que recuperará su contraseña
     * @return      el usuarioDto que desea recuperar su contraseña
     */
    @POST
    @Path("/enviarCodigo/{correo_entrada}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public UsuarioDto generarCodigoRecuperacion(@PathParam("correo_entrada") String correo_entrada) throws Exception{
        BasicConfigurator.configure();
        logger.debug("Entrando al método que genera un código de recuperación de contraseña");
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
            throw new ucab.dsw.excepciones.CreateException( "Error generando el código de recuperación");
        }
        return  null;
    }

    /**
     * Este método valida que el código de recuperación ingresado por el usuario coincida con el
     * que se encuentra guardado en su registro de BD
     *
     * @param  usuarioDto usuario para el cual será validado el código de recuperación
     * @return      si la validación del código es correcta, retorna el usuarioDto y deja en null su campo de
     * código de recuperación para cuando vuelva a intentarlo
     */
    @PUT
    @Path( "/validarCodigo" )
    public UsuarioDto validarCodigo(UsuarioDto usuarioDto) throws Exception{
        BasicConfigurator.configure();
        logger.debug("Entrando al método que valida un código de recuperación de contraseña");
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
            throw new ucab.dsw.excepciones.CreateException( "Error validando el código de recuperación");
        }
        return null;
    }

    /**
     * Este método actualiza la contraseña de un usuario cuando ha pasado por el proceso de recuperación
     *
     * @param  usuarioDto usuario para el cual se actualizará su contraseña
     * @return      el usuarioDto cuya contraseña ha sido actualizada
     */
    @PUT
    @Path( "/cambiarPasswordCodigo" )
    public UsuarioDto cambiarPassWordCodigo(UsuarioDto usuarioDto) {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que actualiza la contraseña de un usuario al recuperarla");
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
            logger.debug("Saliendo del método que actualiza la contraseña de un usuario al recuperarla");
            return resultado;
        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return null;
    }



}