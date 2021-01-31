package logica.comando.datoUsuario;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.dtos.Dato_usuarioDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Dato_usuario;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.DatoUsuarioMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddDatoUsuarioComando extends BaseComando {

    public Dato_usuarioDto datoUsuarioDto;

    public AddDatoUsuarioComando(Dato_usuarioDto datoUsuarioDto) {
        this.datoUsuarioDto = datoUsuarioDto;
    }

    @Override
    public void execute() {

        try {
            DaoDato_usuario dao = Fabrica.crear(DaoDato_usuario.class);
            Dato_usuario datoUsuario = DatoUsuarioMapper.mapDtoToEntityInsert(this.datoUsuarioDto);
            Dato_usuario resul = dao.insert( datoUsuario );
            this.datoUsuarioDto=DatoUsuarioMapper.mapEntityToDto(resul);

        } catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Dato_usuario añadido")
                .add("dato_usuario_id",this.datoUsuarioDto.getId()).build();

        return data;
    }

}
