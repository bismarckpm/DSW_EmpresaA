package logica.comando.datoUsuario;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.dtos.Dato_usuarioDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Dato_usuario;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.DatoUsuarioMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditDato_usuarioComando extends BaseComando {

    public long _id;
    public Dato_usuarioDto dato_usuarioDto;

    public EditDato_usuarioComando(long _id, Dato_usuarioDto dato_usuarioDto) {
        this._id = _id;
        this.dato_usuarioDto = dato_usuarioDto;
    }

    @Override
    public void execute() {
        try{
            DaoDato_usuario dao = Fabrica.crear(DaoDato_usuario.class);
            Dato_usuario dato_usuario= DatoUsuarioMapper.mapDtoToEntityUpdate(_id,dato_usuarioDto);
            Dato_usuario resul = dao.update(dato_usuario);
            this.dato_usuarioDto=DatoUsuarioMapper.mapEntityToDto(resul);
        }
        catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Dato_usuario actualizado")
                .add("dato_usuario_cedula",this.dato_usuarioDto.getCedula()).build();

        return data;
    }

}