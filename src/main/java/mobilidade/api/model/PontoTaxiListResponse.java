
package mobilidade.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import mobilidade.domain.model.PontoTaxi;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author marcos
 * @since 05/07/2020
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PontoTaxiListResponse {

    private Integer total;
    private OffsetDateTime dataHora;
    private List<PontoTaxiResponse> data;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public List<PontoTaxiResponse> getData() {
        return data;
    }

    public void setData(List<PontoTaxiResponse> data) {
        this.data = data;
    }
    
    public static PontoTaxiListResponse castFromDomain(List<PontoTaxi> list) {
        
        List<PontoTaxiResponse> data = list.stream()
                .map(pt -> {
                    PontoTaxiResponse pontoTaxiResponse = PontoTaxiResponse.castFromDomain(pt);
                    pontoTaxiResponse.setDataHora(null);
                    return pontoTaxiResponse;
                })
                .collect(Collectors.toList());
        
        PontoTaxiListResponse response = new PontoTaxiListResponse();
        response.setTotal(list.size());
        response.setDataHora(OffsetDateTime.now());
        response.setData(data);
        
        return response;
    }
}
