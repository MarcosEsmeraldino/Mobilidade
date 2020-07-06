
package mobilidade.domain.model;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.lang.Nullable;

/**
 *
 * @author marcos
 * @since 04/07/2020
 */
@Entity
public class Onibus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "onibus_id")
    private Long onibusId;
    
    @NotNull
    @Column(name = "integration_id")
    private Long integrationId;

    @NotBlank
    @Size(max = 255)
    private String codigo;

    @NotBlank
    @Size(max = 255)
    private String nome;
    
    @OneToMany(mappedBy = "onibus")
    private List<Parada> paradas;
    
    @NotNull
    private OffsetDateTime dataCadastro;
    
    @Nullable
    private OffsetDateTime dataAlteracao;

    public Long getOnibusId() {
        return onibusId;
    }

    public void setOnibusId(Long onibusId) {
        this.onibusId = onibusId;
    }

    public Long getIntegrationId() {
        return integrationId;
    }

    public void setIntegrationId(Long integrationId) {
        this.integrationId = integrationId;
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

    public List<Parada> getParadas() {
        return paradas;
    }

    public void setParadas(List<Parada> paradas) {
        this.paradas = paradas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.onibusId);
        hash = 53 * hash + Objects.hashCode(this.integrationId);
        hash = 53 * hash + Objects.hashCode(this.codigo);
        hash = 53 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Onibus other = (Onibus) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.onibusId, other.onibusId)) {
            return false;
        }
        if (!Objects.equals(this.integrationId, other.integrationId)) {
            return false;
        }
        return true;
    }

}
