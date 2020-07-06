
package mobilidade.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import mobilidade.domain.model.Onibus;
import java.time.OffsetDateTime;

/**
 *
 * @author marcos
 * @since 04/07/2020
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OnibusResponse {

    private Long onibusId;
    private String codigo;
    private String nome;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataAlteracao;
    private OffsetDateTime dataHora;

    public Long getOnibusId() {
        return onibusId;
    }

    public void setOnibusId(Long onibusId) {
        this.onibusId = onibusId;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
    }
    
    public static OnibusResponse castFromDomain(Onibus onibus) {
        
        OnibusResponse response = new OnibusResponse();
        response.setOnibusId(onibus.getOnibusId());
        response.setNome(onibus.getNome());
        response.setCodigo(onibus.getCodigo());
        response.setDataAlteracao(onibus.getDataAlteracao());
        response.setDataCadastro(onibus.getDataCadastro());
        response.setDataHora(OffsetDateTime.now());
        
        return response;
    }
}
