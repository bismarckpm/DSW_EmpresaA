package ucab.dsw.servicio;

public class SendMailSSL{
    public void enviar(String correo_entrada, String codigo_entrada) {
        //from,password,to,subject,message
        Mailer.send("alejandroandrade1981@gmail.com","aleX3422.",correo_entrada,"Código de recuperación",codigo_entrada);
        //change from, password and to
    }
}
