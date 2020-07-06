package mobilidade.domain.repository;

import mobilidade.domain.model.Onibus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author marcos
 * @since 04/07/2020
 */
public interface OnibusRepository extends JpaRepository<Onibus, Long> {
    
    Optional<Onibus> findByintegrationId(Long integrationId);
    
}
