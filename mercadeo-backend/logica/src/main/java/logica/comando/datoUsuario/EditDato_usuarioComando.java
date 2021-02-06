package logica.comando.datoUsuario;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Dato_usuario;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.DatoUsuarioMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditDato_usuarioComando extends BaseComando {
    
    public Dato_usuario dato_usuario;

    public EditDato_usuarioComando(Dato_usuario dato_usuario) {
        this.dato_usuario = dato_usuario;
    }

    @Override
    public void execute() {
        try{
            DaoDato_usuario dao = Fabrica.crear(DaoDato_usuario.class);
            dao.update(this.dato_usuario);
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Dato_usuario actualizado");
        data.setObjeto(this.dato_usuario.get_id());

        return data;
    }

}