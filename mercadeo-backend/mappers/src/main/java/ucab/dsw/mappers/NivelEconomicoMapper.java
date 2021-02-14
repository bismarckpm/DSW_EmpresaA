package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoNivel_economico;
import ucab.dsw.dtos.Nivel_economicoDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Nivel_economico;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;

public class NivelEconomicoMapper {

    public static Nivel_economico mapDtoToEntityInsert(Nivel_economicoDto nivel_economicoDto )throws CustomException
    {
        Nivel_economico nivel_economico = new Nivel_economico();
        if (nivel_economicoDto.getNivel() == null || nivel_economicoDto.getNivel().equals(""))
            throw new CustomException("001", "El nombre del nivel económico no puede ser nulo ni vacío");
        if(nivel_economicoDto.getNivel().length() > 45)
            throw new CustomException("002", "El nombre del nivel económico excede el máximo permitido");
        nivel_economico.set_nivel( nivel_economicoDto.getNivel() );
        nivel_economico.set_estado( nivel_economicoDto.getEstado() );

        return nivel_economico;
    }

    public static Nivel_economico mapDtoToEntityUpdate(long _id,Nivel_economicoDto nivel_economicoDto )throws CustomException
    {
        DaoNivel_economico daoNivel_economico=new DaoNivel_economico();
        if (nivel_economicoDto.getNivel() == null || nivel_economicoDto.getNivel().equals(""))
            throw new CustomException("001", "El nombre del nivel económico no puede ser nulo ni vacío");
        if(nivel_economicoDto.getNivel().length() > 45)
            throw new CustomException("002", "El nombre del nivel económico excede el máximo permitido");
        Nivel_economico nivel_economico = daoNivel_economico.find(_id,Nivel_economico.class);

        nivel_economico.set_nivel( nivel_economicoDto.getNivel() );
        nivel_economico.set_estado( nivel_economicoDto.getEstado() );

        return nivel_economico;
    }

    public static Nivel_economicoDto mapEntityToDto(  Nivel_economico nivel_economico ) throws CustomException{
        Nivel_economicoDto nivel_economicoDto = new Nivel_economicoDto();
        if (nivel_economico == null)
            throw new CustomException("004", "El nivel económico recibido es nulo");
        if (nivel_economico.get_id() == 0 || nivel_economico.get_nivel()==""){
            throw new CustomException("001", "Existen atributos inválidos en el nivel económico");
        }
        nivel_economicoDto.setId(nivel_economico.get_id());
        nivel_economicoDto.setNivel( nivel_economico.get_nivel() );
        nivel_economicoDto.setEstado( nivel_economico.get_estado() );
        return nivel_economicoDto;
    }
}
