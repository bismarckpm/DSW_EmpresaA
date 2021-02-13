package logica.comando.datoUsuario;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Dato_usuario;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.DatoUsuarioMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddDatoUsuarioComando extends BaseComando {

    public Dato_usuario datoUsuario;

    public AddDatoUsuarioComando(Dato_usuario datoUsuario) {
        this.datoUsuario = datoUsuario;
    }

    @Override
    public void execute() {

        try {
            DaoDato_usuario dao = Fabrica.crear(DaoDato_usuario.class);
            dao.insert( this.datoUsuario );
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Dato_usuario Añadido");
        data.setObjeto(this.datoUsuario);

        return data;
    }

}
