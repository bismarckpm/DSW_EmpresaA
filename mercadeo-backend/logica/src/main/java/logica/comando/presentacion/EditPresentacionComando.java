package logica.comando.presentacion;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPresentacion;
import ucab.dsw.dtos.PresentacionDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Presentacion;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.PresentacionMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditPresentacionComando extends BaseComando {

    public long _id;
    public PresentacionDto presentacionDto;

    public EditPresentacionComando(long _id, PresentacionDto presentacionDto) {
        this._id = _id;
        this.presentacionDto = presentacionDto;
    }

    @Override
    public void execute() {
        try{
            DaoPresentacion dao = Fabrica.crear(DaoPresentacion.class);
            Presentacion presentacion= PresentacionMapper.mapDtoToEntityUpdate(_id,presentacionDto);
            Presentacion resul = dao.update(presentacion);
            this.presentacionDto=PresentacionMapper.mapEntityToDto(resul);
        }
        catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Presentacion actualizada")
                .add("presentacion_nombre",this.presentacionDto.getTitulo()).build();

        return data;
    }

}
