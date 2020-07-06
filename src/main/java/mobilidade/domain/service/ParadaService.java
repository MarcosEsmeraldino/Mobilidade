
package mobilidade.domain.service;

import mobilidade.domain.exception.RecursoJaExiste;
import mobilidade.domain.exception.RecursoNaoEncontrado;
import mobilidade.domain.model.Onibus;
import mobilidade.domain.model.Parada;
import mobilidade.domain.repository.ParadaRepository;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marcos
 * @since 04/07/2020
 */
@Service
public class ParadaService {

    @Autowired
    private ParadaRepository paradaRepository;
    
    @Autowired
    private OnibusService onibusService;

    public List<Parada> listar() {
        return paradaRepository.findAll();
    }

    public List<Parada> listarPorOnibus(Long onibusId) {
        Onibus onibus = onibusService.buscar(onibusId);
        return paradaRepository.findAllByOnibus(onibus);
    }
    
    public Parada buscar(Long paradaId) {
        Optional<Parada> optional = paradaRepository.findById(paradaId);
        
        if(!optional.isPresent())
            throw new RecursoNaoEncontrado("parada nao encontrada");

        return optional.get();
    }
    
    public Parada gravar(Parada parada) {
        
        Optional<Parada> optional = paradaRepository
                .findByOnibusAndOrdemAndLatAndLng(
                        parada.getOnibus(),
                        parada.getOrdem(),
                        parada.getLat(), parada.getLng());
        
        if(optional.isPresent())
            throw new RecursoJaExiste("parada ja cadastrada");
        
        parada.setParadaId(null);
        parada.setDataAlteracao(null);
        parada.setDataCadastro(OffsetDateTime.now());

        return paradaRepository.save(parada);
    }
    
    public List<Parada> gravar(List<Parada> paradas) {
        return paradas.stream()
                .map(p -> { return gravar(p); })
                .collect(Collectors.toList());
    }
    
    public void apagar(Long paradaId) {
        Optional<Parada> optional = paradaRepository.findById(paradaId);
        
        if(!optional.isPresent())
            throw new RecursoNaoEncontrado("parada nao encontrada");
        
        paradaRepository.delete(optional.get());
    }
    
    public void apagar(List<Parada> paradas) {
        paradas.stream().forEach(p -> apagar(p.getParadaId()));
    }
}
