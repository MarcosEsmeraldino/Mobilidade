
package mobilidade.domain.repository;

import mobilidade.domain.model.Onibus;
import mobilidade.domain.model.Parada;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author marcos
 * @since 04/07/2020
 */
public interface ParadaRepository extends JpaRepository<Parada, Long> {

    Optional<Parada> findByOnibusAndOrdemAndLatAndLng(Onibus onibus, Integer ordem, 
            String lat, String lng);
    
    List<Parada> findAllByOnibus(Onibus onibus);
    
}
