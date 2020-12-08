package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;

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
