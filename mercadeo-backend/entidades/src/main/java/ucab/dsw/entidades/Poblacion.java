package ucab.dsw.entidades;

import javax.persistence.*;

@Entity
@Table( name = "poblacion" )
public class Poblacion extends EntidadBase{

    @Column( name = "estado" )
    private String _estado;

    @ManyToOne
    @JoinColumn( name = "fk_usuario" )
    private Usuario _usuario;

    @ManyToOne
    @JoinColumn( name = "fk_estudio" )
    private Estudio _estudio;

    public Poblacion( long id )
    {
        super( id );
    }

    public Poblacion( )
    {

    }

    @Override
    public String get_estado() {
        return _estado;
    }

    @Override
    public void set_estado(String _estado) {
        this._estado = _estado;
    }

    public Usuario get_usuario() {
        return _usuario;
    }

    public void set_usuario(Usuario _usuario) {
        this._usuario = _usuario;
    }

    public Estudio get_estudio() {
        return _estudio;
    }

    public void set_estudio(Estudio _estudio) {
        this._estudio = _estudio;
    }
}
