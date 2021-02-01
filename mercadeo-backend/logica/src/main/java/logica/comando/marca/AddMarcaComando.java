package logica.comando.marca;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.MarcaMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddMarcaComando extends BaseComando {

    public MarcaDto marcaDto;

    public AddMarcaComando(MarcaDto marcaDto) {
        this.marcaDto = marcaDto;
    }

    @Override
    public void execute() {

        try {
            DaoMarca dao = Fabrica.crear(DaoMarca.class);
            Marca marca = MarcaMapper.mapDtoToEntityInsert(this.marcaDto);
            Marca resul = dao.insert( marca );
            this.marcaDto=MarcaMapper.mapEntityToDto(resul);

        } catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Marca añadida")
                .add("marca_id",this.marcaDto.getId()).build();

        return data;
    }

}
