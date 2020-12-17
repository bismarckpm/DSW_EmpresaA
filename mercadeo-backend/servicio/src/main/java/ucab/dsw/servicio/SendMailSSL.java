package ucab.dsw.servicio;

public class SendMailSSL{
    public void enviar(String correo_entrada, String codigo_entrada) {
        //from,password,to,subject,message
        Mailer.send("alejandroandrade1981@gmail.com","aleX3422.",correo_entrada,"Mercadeoucab. Código de recuperación","El código de recuperación para su cuenta de Mercadeoucab es: " + codigo_entrada);
        //change from, password and to
    }
}
