
package mobilidade.domain.model;

import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.lang.Nullable;

/**
 *
 * @author marcos
 * @since 05/07/2020
 */
@Entity
public class PontoTaxi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taxi_id")
    private Long taxiId;
    
    @NotBlank
    @Size(max = 255)
    private String nome;

    @NotBlank
    @Column(name = "latitude")
    private String lat;

    @NotBlank
    @Column(name = "longitude")
    private String lng;
    
    @NotNull
    private OffsetDateTime dataCadastro;
    
    @Nullable
    private OffsetDateTime dataAlteracao;

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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.taxiId);
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
        final PontoTaxi other = (PontoTaxi) obj;
        if (!Objects.equals(this.taxiId, other.taxiId)) {
            return false;
        }
        return true;
    }
}
