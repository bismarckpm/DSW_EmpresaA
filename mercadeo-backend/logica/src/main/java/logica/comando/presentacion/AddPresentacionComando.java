package logica.comando.presentacion;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPresentacion;
import ucab.dsw.dtos.PresentacionDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Presentacion;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.PresentacionMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddPresentacionComando extends BaseComando {

    public PresentacionDto presentacionDto;

    public AddPresentacionComando(PresentacionDto presentacionDto) {
        this.presentacionDto = presentacionDto;
    }

    @Override
    public void execute() {

        try {
            DaoPresentacion dao = Fabrica.crear(DaoPresentacion.class);
            Presentacion presentacion = PresentacionMapper.mapDtoToEntityInsert(this.presentacionDto);
            Presentacion resul = dao.insert( presentacion );
            this.presentacionDto=PresentacionMapper.mapEntityToDto(resul);

        } catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Presentacion añadida")
                .add("presentacion_id",this.presentacionDto.getId()).build();

        return data;
    }

}
