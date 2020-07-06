
package mobilidade.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import mobilidade.domain.model.PontoTaxi;
import java.time.OffsetDateTime;

/**
 *
 * @author marcos
 * @since 05/07/2020
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PontoTaxiResponse {

    private Long taxiId;
    private String nome;
    private String lat;
    private String lng;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataAlteracao;
    private OffsetDateTime dataHora;

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Long getTaxiId() {
        return taxiId;
    }

    public void setTaxiId(Long taxiId) {
        this.taxiId = taxiId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

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
    
    public static PontoTaxiResponse castFromDomain(PontoTaxi pontoTaxi) {
        
        PontoTaxiResponse response = new PontoTaxiResponse();
        response.setTaxiId(pontoTaxi.getTaxiId());
        response.setNome(pontoTaxi.getNome());
        response.setLat(pontoTaxi.getLat());
        response.setLng(pontoTaxi.getLng());
        response.setDataCadastro(pontoTaxi.getDataCadastro());
        response.setDataAlteracao(pontoTaxi.getDataAlteracao());
        response.setDataHora(OffsetDateTime.now());
        
        return response;
    }
    
}
