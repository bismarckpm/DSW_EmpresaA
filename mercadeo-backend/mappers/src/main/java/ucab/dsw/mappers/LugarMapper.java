package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.dtos.LugarDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Lugar;
import ucab.dsw.excepciones.PruebaExcepcion;

public class LugarMapper {

    public static Lugar mapDtoToEntityInsert(LugarDto lugarDto )
    {
        Lugar lugar = new Lugar();

        DaoLugar daoLugar=new DaoLugar();
        lugar.set_nombre( lugarDto.getNombre() );
        lugar.set_tipo( lugarDto.getTipo() );
        lugar.set_categoriaSocioEconomica( lugarDto.getCategoriaSocioEconomica() );
        lugar.set_estado( lugarDto.getEstado() );
        Lugar lugar2 = daoLugar.find(lugar.get_lugar().get_id(),Lugar.class);
        lugar.set_lugar( lugar2 );

        return lugar;
    }

    public static Lugar mapDtoToEntityUpdate(long _id,LugarDto lugarDto )
    {
        DaoLugar daoLugar=new DaoLugar();

        Lugar lugar = daoLugar.find(_id,Lugar.class);

        lugar.set_nombre( lugarDto.getNombre() );
        lugar.set_tipo( lugarDto.getTipo() );
        lugar.set_categoriaSocioEconomica( lugarDto.getCategoriaSocioEconomica() );
        lugar.set_estado( lugarDto.getEstado() );
        Lugar lugar2 = daoLugar.find(lugar.get_lugar().get_id(),Lugar.class);
        lugar.set_lugar( lugar2 );

        return lugar;
    }

    public static LugarDto mapEntityToDto(  Lugar lugar ) throws PruebaExcepcion {
        LugarDto lugarDto = new LugarDto();

        DaoLugar dao = new DaoLugar();
        lugarDto.setId(lugar.get_id());
        lugarDto.setNombre( lugar.get_nombre() );
        lugarDto.setTipo( lugar.get_tipo() );
        lugarDto.setCategoriaSocioEconomica( lugar.get_categoriaSocioEconomica() );
        lugarDto.setEstado( lugar.get_estado() );
        lugarDto.setLugarDto( LugarMapper.mapEntityToDto(dao.find(lugar.get_lugar().get_id(),Lugar.class)) );
        return lugarDto;
    }
    
}
