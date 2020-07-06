
package mobilidade.api.model;

import mobilidade.domain.model.Onibus;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author marcos
 * @since 04/07/2020
 */
public class OnibusInput {

    @NotNull
    private Long id;
    
    @NotBlank
    @Size(max = 255)
    private String codigo;
    
    @NotBlank
    @Size(max = 255)
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    
    public static Onibus castToDomain(OnibusInput input) {
        
        Onibus onibus = new Onibus();
        onibus.setIntegrationId(input.getId());
        onibus.setCodigo(input.getCodigo());
        onibus.setNome(input.getNome());
        
        return onibus;
    }

}
