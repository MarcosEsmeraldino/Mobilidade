
package mobilidade.integration.model;

import java.util.Map;

/**
 *
 * @author marcos
 * @since 04/07/2020
 */
public class Itinerario {
    
    private Long idLinha;
    private String nome;
    private String codigo;
    private Map<Integer, Ponto> pontos;

    public Long getIdLinha() {
        return idLinha;
    }

    public void setIdLinha(Long idLinha) {
        this.idLinha = idLinha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Map<Integer, Ponto> getPontos() {
        return pontos;
    }

    public void setPontos(Map<Integer, Ponto> pontos) {
        this.pontos = pontos;
    }
    
    public static class Ponto {
        
        private String lat;
        private String lng;

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

        @Override
        public String toString() {
            return "ItinerarioChild{" + "lat=" + lat + ", lng=" + lng + '}';
        }
    }
}
