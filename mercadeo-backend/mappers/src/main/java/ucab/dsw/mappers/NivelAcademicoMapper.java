package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoNivel_academico;
import ucab.dsw.dtos.Nivel_academicoDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Nivel_academico;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;

public class NivelAcademicoMapper {

    public static Nivel_academico mapDtoToEntityInsert(Nivel_academicoDto nivel_academicoDto ) throws CustomException
    {
        Nivel_academico nivel_academico = new Nivel_academico();
        if (nivel_academicoDto.getNivel() == null || nivel_academicoDto.getNivel().equals(""))
            throw new CustomException("001", "El nombre del nivel académico no puede ser nulo ni vacío");
        if(nivel_academicoDto.getNivel().length() > 45)
            throw new CustomException("002", "El nombre del nivel académico excede el máximo permitido");
        nivel_academico.set_nivel( nivel_academicoDto.getNivel() );
        nivel_academico.set_estado( nivel_academicoDto.getEstado() );

        return nivel_academico;
    }

    public static Nivel_academico mapDtoToEntityUpdate(long _id,Nivel_academicoDto nivel_academicoDto ) throws CustomException
    {
        DaoNivel_academico daoNivel_academico=new DaoNivel_academico();

        Nivel_academico nivel_academico = daoNivel_academico.find(_id,Nivel_academico.class);
        if (nivel_academico == null)
            throw new CustomException("003","El nivel académico no existe");
        if(nivel_academicoDto.getNivel().length() > 45)
            throw new CustomException("002","El nombre del nivel académico excede el máximo permitido");
        nivel_academico.set_nivel( nivel_academicoDto.getNivel() );
        nivel_academico.set_estado( nivel_academicoDto.getEstado() );

        return nivel_academico;
    }

    public static Nivel_academicoDto mapEntityToDto(  Nivel_academico nivel_academico ) throws CustomException {
        Nivel_academicoDto nivel_academicoDto = new Nivel_academicoDto();
        if (nivel_academico == null)
            throw new CustomException("004", "El nivel académico recibido es nulo");
        if (nivel_academico.get_id() == 0 || nivel_academico.get_nivel()==""){
            throw new CustomException("001", "Existen atributos inválidos en el nivel académico");
        }
        nivel_academicoDto.setId(nivel_academico.get_id());
        nivel_academico.set_nivel( nivel_academicoDto.getNivel() );
        nivel_academico.set_estado( nivel_academicoDto.getEstado() );
        return nivel_academicoDto;
    }
}
