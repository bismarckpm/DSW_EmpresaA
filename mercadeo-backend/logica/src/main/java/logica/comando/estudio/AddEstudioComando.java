package logica.comando.estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.EstudioMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddEstudioComando extends BaseComando {

    public EstudioDto estudioDto;

    public AddEstudioComando(EstudioDto estudioDto) {
        this.estudioDto = estudioDto;
    }

    @Override
    public void execute() {

        try {
            DaoEstudio dao = Fabrica.crear(DaoEstudio.class);
            Estudio estudio = EstudioMapper.mapDtoToEntityInsert(this.estudioDto);
            Estudio resul = dao.insert( estudio );
            this.estudioDto=EstudioMapper.mapEntityToDto(resul);

        } catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Estudio añadido")
                .add("estudio_id",this.estudioDto.getId()).build();

        return data;
    }

}
