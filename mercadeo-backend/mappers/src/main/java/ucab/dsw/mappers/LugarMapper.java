package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.dtos.LugarDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Lugar;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;

public class LugarMapper {

    public static Lugar mapDtoToEntityInsert(LugarDto lugarDto ) throws CustomException
    {
        Lugar lugar = new Lugar();
        if (lugarDto.getNombre() == null || lugarDto.getNombre().equals(""))
            throw new CustomException("001", "El nombre del lugar no puede ser nulo ni vacío");
        if (lugarDto.getTipo() == null || lugarDto.getTipo().equals(""))
            throw new CustomException("001", "El tipo de lugar lugar no puede ser nulo ni vacío");
        if (lugarDto.getCategoriaSocioEconomica() == null || lugarDto.getCategoriaSocioEconomica().equals(""))
            throw new CustomException("001", "La categoría socio económica del lugar no puede ser nulo ni vacío");
        DaoLugar daoLugar=new DaoLugar();
        lugar.set_nombre( lugarDto.getNombre() );
        lugar.set_tipo( lugarDto.getTipo() );
        lugar.set_categoriaSocioEconomica( lugarDto.getCategoriaSocioEconomica() );
        lugar.set_estado( lugarDto.getEstado() );
        Lugar lugar2 = daoLugar.find(lugar.get_lugar().get_id(),Lugar.class);
        lugar.set_lugar( lugar2 );

        return lugar;
    }

    public static Lugar mapDtoToEntityUpdate(long _id,LugarDto lugarDto ) throws CustomException
    {
        DaoLugar daoLugar=new DaoLugar();

        Lugar lugar = daoLugar.find(_id,Lugar.class);
        if (lugar == null)
            throw new CustomException("003","El lugar no existe");
        if (lugarDto.getNombre() == null || lugarDto.getNombre().equals(""))
            throw new CustomException("001", "El nombre del lugar no puede ser nulo ni vacío");
        if (lugarDto.getTipo() == null || lugarDto.getTipo().equals(""))
            throw new CustomException("001", "El tipo de lugar lugar no puede ser nulo ni vacío");
        if (lugarDto.getCategoriaSocioEconomica() == null || lugarDto.getCategoriaSocioEconomica().equals(""))
            throw new CustomException("001", "La categoría socio económica del lugar no puede ser nulo ni vacío");
        lugar.set_nombre( lugarDto.getNombre() );
        lugar.set_tipo( lugarDto.getTipo() );
        lugar.set_categoriaSocioEconomica( lugarDto.getCategoriaSocioEconomica() );
        lugar.set_estado( lugarDto.getEstado() );
        Lugar lugar2 = daoLugar.find(lugar.get_lugar().get_id(),Lugar.class);
        lugar.set_lugar( lugar2 );

        return lugar;
    }

    public static LugarDto mapEntityToDto(  Lugar lugar ) throws CustomException {
        LugarDto lugarDto = new LugarDto();

        if (lugarDto == null)
            throw new CustomException("004", "El lugar recibido es nulo");
        DaoLugar dao = new DaoLugar();
        if (lugar.get_id() == 0 || lugar.get_nombre()=="" || lugar.get_tipo()=="" || lugar.get_categoriaSocioEconomica()==""){
            throw new CustomException("001", "Existen atributos inválidos en la categoría");
        }
        lugarDto.setId(lugar.get_id());
        lugarDto.setNombre( lugar.get_nombre() );
        lugarDto.setTipo( lugar.get_tipo() );
        lugarDto.setCategoriaSocioEconomica( lugar.get_categoriaSocioEconomica() );
        lugarDto.setEstado( lugar.get_estado() );
        lugarDto.setLugarDto( LugarMapper.mapEntityToDto(dao.find(lugar.get_lugar().get_id(),Lugar.class)) );
        return lugarDto;
    }
    
}
