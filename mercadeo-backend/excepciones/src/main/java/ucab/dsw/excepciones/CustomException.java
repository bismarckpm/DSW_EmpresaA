package ucab.dsw.excepciones;

public class CustomException extends Exception{

    private String mensaje;
    private String codigo;

    public CustomException()
    {
        super();
    }

    public CustomException(Exception e)
    {
        super(e);
    }

    public CustomException(String mensaje, Exception e )
    {
        super(mensaje, e);
    }

    public CustomException(String mensaje)
    {
        super(mensaje);
    }

    public CustomException(String codigo, String mensaje)
    {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
