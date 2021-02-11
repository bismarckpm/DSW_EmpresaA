package ucab.dsw.servicio;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendMailSSL{

    private static Logger logger = LoggerFactory.getLogger(SendMailSSL.class);
    /**
     * Este método envía un correo electrónico, utilizado para la recuperación de contraseña
     *
     * @param  correo_entrada  correo del usuario que desea recuperar contraseña
     * @param  codigo_entrada  código de recuperación que será enviado al correo del usuario
     */
    public void enviar(String correo_entrada, String codigo_entrada) {
        //from,password,to,subject,message
        Mailer.send("alejandroandrade1981@gmail.com","aleX3422.",correo_entrada,"Mercadeoucab. Código de recuperación","El código de recuperación para su cuenta de Mercadeoucab es: " + codigo_entrada);
        //change from, password and to
    }
}