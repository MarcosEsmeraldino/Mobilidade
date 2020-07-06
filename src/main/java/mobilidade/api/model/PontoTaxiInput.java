
package mobilidade.api.model;

import mobilidade.domain.model.PontoTaxi;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author marcos
 * @since 05/07/2020
 */
public class PontoTaxiInput {

    @NotBlank
    @Size(max = 255)
    private String nome;

    @NotBlank
    @Pattern(regexp = "^-*[0-9]+\\.*[0-9]*$")
    private String lat;

    @NotBlank
    @Pattern(regexp = "^-*[0-9]+\\.*[0-9]*$")
    private String lng;

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

    public static PontoTaxi castToDomain(PontoTaxiInput input) {
        PontoTaxi pontoTaxi = new PontoTaxi();
        pontoTaxi.setNome(input.getNome());
        pontoTaxi.setLat(input.getLat());
        pontoTaxi.setLng(input.getLng());
        
        return pontoTaxi;
    }
}
