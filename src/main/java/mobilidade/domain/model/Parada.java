
package mobilidade.domain.model;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.lang.Nullable;

/**
 *
 * @author marcos
 * @since 04/07/2020
 */
@Entity
public class Parada implements Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parada_id")
    private Long paradaId;
    
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

    @ManyToOne
    @JoinColumn(name="onibus_id", nullable=false)
    private Onibus onibus;

    @NotNull
    private Integer ordem;

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

    public Onibus getOnibus() {
        return onibus;
    }

    public void setOnibus(Onibus onibus) {
        this.onibus = onibus;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.lat);
        hash = 53 * hash + Objects.hashCode(this.lng);
        hash = 53 * hash + Objects.hashCode(this.onibus);
        hash = 53 * hash + Objects.hashCode(this.ordem);
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
        final Parada other = (Parada) obj;
        if (!Objects.equals(this.lat, other.lat)) {
            return false;
        }
        if (!Objects.equals(this.lng, other.lng)) {
            return false;
        }
        if (!Objects.equals(this.onibus, other.onibus)) {
            return false;
        }
        if (!Objects.equals(this.ordem, other.ordem)) {
            return false;
        }
        return true;
    }
    
    public static boolean equals(List<Parada> paradas1, List<Parada> paradas2){
        
        int size1 = paradas1.size();
        int size2 = paradas2.size();
        
        if(size1 != size2)
            return false;
        
        Collections.sort(paradas1);
        Collections.sort(paradas2);
        
        for(int i=0; i<size1; i++) {
            
            Parada p1 = paradas1.get(i);
            Parada p2 = paradas2.get(i);
            
            if(!p1.getLat().equals(p2.getLat()))
                return false;
            
            if(!p1.getLng().equals(p2.getLng()))
                return false;
            
            if(!p1.getOrdem().equals(p2.getOrdem()))
                return false;
            
        }
        
        return true;
    }

    @Override
    public int compareTo(Object o) {
        return ((Parada) o).getOrdem().compareTo(this.ordem);
    }

}
