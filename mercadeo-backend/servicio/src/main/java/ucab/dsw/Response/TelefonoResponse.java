package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.POST;

@Getter
@Setter
public class TelefonoResponse {

  private String numero;
  private String estado;
  private long codigo;
  private long idDatoUsuario;

    public TelefonoResponse(String numero, String estado, long codigo, long idDatoUsuario) {
        this.numero = numero;
        this.estado = estado;
        this.codigo = codigo;
        this.idDatoUsuario = idDatoUsuario;
    }
}
