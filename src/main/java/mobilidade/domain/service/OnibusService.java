
package mobilidade.domain.service;

import mobilidade.domain.exception.RecursoJaExiste;
import mobilidade.domain.exception.RecursoNaoEncontrado;
import mobilidade.domain.model.Onibus;
import mobilidade.domain.model.Parada;
import mobilidade.domain.repository.OnibusRepository;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marcos
 * @since 04/07/2020
 */
@Service
public class OnibusService {

    @Autowired
    private OnibusRepository onibusRepository;
    
    @Autowired
    private ItinerarioService itinerarioService;

    public List<Onibus> listar() {
        List<Onibus> list = onibusRepository.findAll();
        
        if(list.isEmpty())
            throw new RecursoNaoEncontrado("nenhum onibus encontrado");
        
        return list;
    }

    public List<Onibus> listarPorReferencia(double lat, double lng, double raio) {
        
        List<Onibus> itinerarios = itinerarioService.listar();
        
        if(itinerarios.isEmpty())
            throw new RecursoNaoEncontrado("nenhum onibus encontrado");

        itinerarios = itinerarios.stream()
                .filter(i -> {
                    Stream<Parada> filter = i.getParadas().stream()
                            .filter(p -> {
                                double a = Double.parseDouble(p.getLat());
                                double b = Double.parseDouble(p.getLng());
                                return (Math.sqrt(Math.pow(lat-a, 2) + Math.pow(lng-b, 2)) <= raio );
                            });
                    
                    return filter.count() > 0;
                })
                .collect(Collectors.toList());
        
        if(itinerarios.isEmpty())
            throw new RecursoNaoEncontrado("nenhum onibus encontrado");
        
        return itinerarios;
    }
    
    public Onibus buscar(Long onibusId) {
        Optional<Onibus> optional = onibusRepository.findById(onibusId);
        
        if(!optional.isPresent())
            throw new RecursoNaoEncontrado("onibus nao encontrado");

        return optional.get();
    }
    
    public Onibus gravar(Onibus onibus) {
        
        Optional<Onibus> optional = onibusRepository.findByintegrationId(onibus.getIntegrationId());
        
        if(optional.isPresent()) {
            Onibus o = optional.get();
            onibus.setOnibusId(o.getOnibusId());
            
            if(o.equals(onibus))
                throw new RecursoJaExiste("onibus ja cadastrado");
            
            onibus.setDataCadastro(o.getDataCadastro());
            onibus.setDataAlteracao(OffsetDateTime.now());
        }
        else {
            onibus.setOnibusId(null);
            onibus.setDataAlteracao(null);
            onibus.setDataCadastro(OffsetDateTime.now());
        }
        
        return onibusRepository.save(onibus);
    }
    
    public void apagar(Long onibusId) {
        
        Optional<Onibus> optional = onibusRepository.findById(onibusId);
        
        if(!optional.isPresent())
            throw new RecursoNaoEncontrado("onibus nao encontrado");
            
        onibusRepository.delete(optional.get());
    }

}
