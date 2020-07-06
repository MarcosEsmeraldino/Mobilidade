
package mobilidade.domain.repository;

import mobilidade.domain.model.PontoTaxi;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author marcos
 * @since 05/07/2020
 */
public interface PontoTaxiRepository extends JpaRepository<PontoTaxi, Long> {

    PontoTaxi findByNome(String nome);
    
}
