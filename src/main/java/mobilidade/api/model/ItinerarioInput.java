
package mobilidade.api.model;

import mobilidade.domain.model.Parada;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author marcos
 * @since 04/07/2020
 */
public class ItinerarioInput {
    
    @Valid
    private List<ItinerarioInputPonto> pontos;

    public List<ItinerarioInputPonto> getPontos() {
        return pontos;
    }

    public void setPontos(List<ItinerarioInputPonto> pontos) {
        this.pontos = pontos;
    }
    
    public static class ItinerarioInputPonto {

        @Pattern(regexp = "^-*[0-9]+\\.*[0-9]*$")
        @NotNull
        private String lat;

        @Pattern(regexp = "^-*[0-9]+\\.*[0-9]*$")
        @NotNull
        private String lng;

        @NotNull
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
    }

    public static List<Parada> castToDomain(ItinerarioInput input) {
        return input.getPontos().stream()
                .map(i -> {
                    Parada parada = new Parada();
                    parada.setLat(i.getLat());
                    parada.setLng(i.getLng());
                    parada.setOrdem(i.getOrdem());
                    return parada;
                })
                .collect(Collectors.toList());
    }
}
