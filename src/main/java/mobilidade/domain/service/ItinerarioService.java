
package mobilidade.domain.service;

import mobilidade.domain.exception.RecursoJaExiste;
import mobilidade.domain.exception.RecursoNaoEncontrado;
import mobilidade.domain.model.Onibus;
import mobilidade.domain.model.Parada;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marcos
 * @since 04/07/2020
 */
@Service
public class ItinerarioService {

    @Autowired
    private OnibusService onibusService;

    @Autowired
    private ParadaService paradaService;
    
    public List<Onibus> listar() {
        List<Parada> paradas = paradaService.listar();
        List<Onibus> itinerarios = paradas.stream()
                .map(Parada::getOnibus)
                .distinct()
                .collect(Collectors.toList());
        
        return itinerarios;
    }
    
    public Onibus buscar(Long onibusId) {
        Onibus onibus = onibusService.buscar(onibusId);
        List<Parada> paradas = paradaService.listarPorOnibus(onibusId);
        
        if(paradas.isEmpty())
            throw new RecursoNaoEncontrado("itinerario nao encontrado");
        
        onibus.setParadas(paradas);
        return onibus;
    }
    
    public Onibus gravar(Long onibusId, List<Parada> paradas) {

        Onibus onibus = onibusService.buscar(onibusId);
        
        if(paradas == null || paradas.isEmpty())
            throw new RecursoNaoEncontrado("itinerario nao encontrado");
        
        List<Parada> paradasGravadas = paradaService.listarPorOnibus(onibusId);
        
        if(!paradasGravadas.isEmpty()) {
            if(Parada.equals(paradas, paradasGravadas))
                throw new RecursoJaExiste("itinerario ja cadastrado");

            paradaService.apagar(paradasGravadas);
        }
        
        paradas.stream().forEach(p -> p.setOnibus(onibus));
        onibus.setParadas(paradas);
        paradas = paradaService.gravar(paradas);
        
        return onibus;
    }
    
    public Onibus apagar(Long onibusId) {
        
        Onibus onibus = buscar(onibusId);
        paradaService.apagar(onibus.getParadas());
        onibus.setParadas(null);
        
        return onibus;
    }
    
}
