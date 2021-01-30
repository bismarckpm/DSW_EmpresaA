package logica.comando.nivel_economico;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoNivel_economico;
import ucab.dsw.dtos.Nivel_economicoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Nivel_economico;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.Nivel_economicoMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditNivel_economicoComando extends BaseComando {

    public long _id;
    public Nivel_economicoDto nivel_economicoDto;

    public EditNivel_economicoComando(long _id, Nivel_economicoDto nivel_economicoDto) {
        this._id = _id;
        this.nivel_economicoDto = nivel_economicoDto;
    }

    @Override
    public void execute() {
        try{
            DaoNivel_economico dao = Fabrica.crear(DaoNivel_economico.class);
            Nivel_economico nivel_economico= Nivel_economicoMapper.mapDtoToEntityUpdate(_id,nivel_economicoDto);
            Nivel_economico resul = dao.update(nivel_economico);
            this.nivel_economicoDto=Nivel_economicoMapper.mapEntityToDto(resul);
        }
        catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Nivel_economico actualizado")
                .add("nivel_economico_nombre",this.nivel_economicoDto.getNivel()).build();

        return data;
    }

}
