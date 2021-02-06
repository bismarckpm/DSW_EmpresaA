package ucab.dsw.dtos;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class PoblacionDto extends DtoBase{

    private String estado;

    private UsuarioDto usuario;

    private EstudioDto estudio;

    public PoblacionDto()
    {
    }

    public PoblacionDto( long id ) throws Exception
    {
        super( id );
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }

    public EstudioDto getEstudio() {
        return estudio;
    }

    public void setEstudio(EstudioDto estudio) {
        this.estudio = estudio;
    }
}
