package ucab.dsw.dtos;

public class TelefonoDto extends DtoBase{

    private String estado;

    private String numero;

    private Dato_usuarioDto datoUsuarioDto;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Dato_usuarioDto getDatoUsuarioDto() {
        return datoUsuarioDto;
    }

    public void setDatoUsuarioDto(Dato_usuarioDto datoUsuarioDto) {
        this.datoUsuarioDto = datoUsuarioDto;
    }

    public TelefonoDto()
    {
    }

    public TelefonoDto( long id ) throws Exception
    {
        super( id );
    }
}
