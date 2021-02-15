package logica.comando.usuario;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.entidades.Response.UsuarioResponse;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.CustomException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ObtenerUsuarioRolComando extends BaseComando {

    public List<UsuarioResponse> usuarioResponseList = new ArrayList<>();
    public long _id;

    public ObtenerUsuarioRolComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() throws CustomException{
        try{
            DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);
            List<Usuario> usuarioList = daoUsuario.findAll(Usuario.class);
            if(_id != 0){

                usuarioList.stream().filter(i-> (i.get_rol().get_id() == _id  && i.get_estado().equals("A")) ).
                        collect(Collectors.toList()).stream().forEach(i->{

                    usuarioResponseList.add(setterGetUsuario(i, i.get_id()));

                });
            }else {

                usuarioList.stream().filter(i->( i.get_estado().equals("A") )).collect(Collectors.toList()).forEach(i -> {
                    usuarioResponseList.add(setterGetUsuario(i, i.get_id()));
                });

            }

        }catch ( CustomException ex ) {
            throw ex;
        }catch ( Exception ex )
        {
            ex.printStackTrace();
        }
    }

    private UsuarioResponse setterGetUsuario(Usuario usuario, long id){

        UsuarioResponse usuarioResponse = new UsuarioResponse(id, usuario.get_nombreUsuario(), usuario.get_correo(),
                usuario.get_rol().get_id(), usuario.get_estado());

        return usuarioResponse;
    }


    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Usuario consultado");
        data.setObjeto(this.usuarioResponseList);
        return data;
    }
}
