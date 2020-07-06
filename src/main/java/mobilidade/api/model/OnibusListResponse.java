
package mobilidade.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import mobilidade.domain.model.Onibus;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author marcos
 * @since 04/07/2020
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OnibusListResponse {

    private Integer total;
    private OffsetDateTime dataHora;
    private List<OnibusResponse> data;

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

    public List<OnibusResponse> getData() {
        return data;
    }

    public void setData(List<OnibusResponse> data) {
        this.data = data;
    }
    
    public static OnibusListResponse castFromDomain(List<Onibus> list) {
        
        List<OnibusResponse> data = null;
        
        if(!list.isEmpty())
            data = list.stream()
                    .map(o -> {
                        OnibusResponse or = OnibusResponse.castFromDomain(o);
                        or.setDataHora(null);
                        return or;
                    })
                    .collect(Collectors.toList());
        
        OnibusListResponse response = new OnibusListResponse();
        response.setDataHora(OffsetDateTime.now());
        response.setTotal(list.size());
        response.setData(data);
        
        return response;
        
    }
}
