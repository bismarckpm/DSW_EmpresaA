package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;
import ucab.dsw.Response.TipoPregunta.*;

import java.util.List;

@Getter
@Setter
public class EncuestaResponse {
    private List<VerdaderoFalseResponse> verdaderoFalseResponseList;
    private List<AbiertaResponse> abiertaResponsesList;
    private List<CerradaResponse> cerradaResponsesList;
    private List<EscalaResponse> escalaResponseList;
    private List<MultipleResponse> multipleResponseList;
    private List<SimpleResponse> simpleResponseList;

    public EncuestaResponse(List<VerdaderoFalseResponse> verdaderoFalseResponseList, List<AbiertaResponse> abiertaResponsesList,
                            List<CerradaResponse> cerradaResponsesList, List<EscalaResponse> escalaResponseList,
                            List<MultipleResponse> multipleResponseList, List<SimpleResponse> simpleResponseList) {
        this.verdaderoFalseResponseList = verdaderoFalseResponseList;
        this.abiertaResponsesList = abiertaResponsesList;
        this.cerradaResponsesList = cerradaResponsesList;
        this.escalaResponseList = escalaResponseList;
        this.multipleResponseList = multipleResponseList;
        this.simpleResponseList = simpleResponseList;
    }
}
