
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
public class ItinerarioResponse {

    private Long onibusId;
    private OffsetDateTime dataHora;
    private List<Parada> paradas;

    public Long getOnibusId() {
        return onibusId;
    }

    public void setOnibusId(Long onibusId) {
        this.onibusId = onibusId;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public List<Parada> getParadas() {
        return paradas;
    }

    public void setParadas(List<Parada> paradas) {
        this.paradas = paradas;
    }
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Parada {
        
        private Long paradaId;
        private OffsetDateTime dataCadastro;
        private OffsetDateTime dataAlteracao;
        private String lat;
        private String lng;
        private Integer ordem;

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public Integer getOrdem() {
            return ordem;
        }

        public void setOrdem(Integer ordem) {
            this.ordem = ordem;
        }

        public Long getParadaId() {
            return paradaId;
        }

        public void setParadaId(Long paradaId) {
            this.paradaId = paradaId;
        }

        public OffsetDateTime getDataCadastro() {
            return dataCadastro;
        }

        public void setDataCadastro(OffsetDateTime dataCadastro) {
            this.dataCadastro = dataCadastro;
        }

        public OffsetDateTime getDataAlteracao() {
            return dataAlteracao;
        }

        public void setDataAlteracao(OffsetDateTime dataAlteracao) {
            this.dataAlteracao = dataAlteracao;
        }
        
    }
    
    public static ItinerarioResponse castFromDomain(Onibus onibus) {
        
        List<ItinerarioResponse.Parada> paradas = null;
        
        if(!onibus.getParadas().isEmpty())
            paradas = onibus.getParadas().stream()
                    .map(p -> {
                        ItinerarioResponse.Parada parada = new ItinerarioResponse.Parada();
                        parada.setOrdem(p.getOrdem());
                        parada.setLat(p.getLat());
                        parada.setLng(p.getLng());
                        parada.setDataCadastro(p.getDataCadastro());
                        parada.setDataAlteracao(p.getDataAlteracao());
                        parada.setParadaId(p.getParadaId());
                        return parada;
                    })
                    .collect(Collectors.toList());
        
        ItinerarioResponse response = new ItinerarioResponse();
        response.setOnibusId(onibus.getOnibusId());
        response.setDataHora(OffsetDateTime.now());
        response.setParadas(paradas);
        
        return response;
    }
}
