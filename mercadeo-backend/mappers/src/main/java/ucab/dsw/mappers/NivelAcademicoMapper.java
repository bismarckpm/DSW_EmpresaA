package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoNivel_academico;
import ucab.dsw.dtos.Nivel_academicoDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Nivel_academico;
import ucab.dsw.excepciones.PruebaExcepcion;

public class NivelAcademicoMapper {

    public static Nivel_academico mapDtoToEntityInsert(Nivel_academicoDto nivel_academicoDto )
    {
        Nivel_academico nivel_academico = new Nivel_academico();

        nivel_academico.set_nivel( nivel_academicoDto.getNivel() );
        nivel_academico.set_estado( nivel_academicoDto.getEstado() );

        return nivel_academico;
    }

    public static Nivel_academico mapDtoToEntityUpdate(long _id,Nivel_academicoDto nivel_academicoDto )
    {
        DaoNivel_academico daoNivel_academico=new DaoNivel_academico();

        Nivel_academico nivel_academico = daoNivel_academico.find(_id,Nivel_academico.class);

        nivel_academico.set_nivel( nivel_academicoDto.getNivel() );
        nivel_academico.set_estado( nivel_academicoDto.getEstado() );

        return nivel_academico;
    }

    public static Nivel_academicoDto mapEntityToDto(  Nivel_academico nivel_academico ) throws PruebaExcepcion {
        Nivel_academicoDto nivel_academicoDto = new Nivel_academicoDto();

        nivel_academicoDto.setId(nivel_academico.get_id());
        nivel_academico.set_nivel( nivel_academicoDto.getNivel() );
        nivel_academico.set_estado( nivel_academicoDto.getEstado() );
        return nivel_academicoDto;
    }
}
