
package mobilidade.domain.service;

import mobilidade.domain.exception.RecursoJaExiste;
import mobilidade.domain.exception.RecursoNaoEncontrado;
import mobilidade.domain.model.PontoTaxi;
import mobilidade.domain.repository.PontoTaxiRepository;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marcos
 * @since 05/07/2020
 */
@Service
public class PontoTaxiService {

    @Autowired
    private PontoTaxiRepository pontoTaxiRepository;
    
    public List<PontoTaxi> listar() {
        List<PontoTaxi> list = pontoTaxiRepository.findAll();
        
        if(list.isEmpty())
            throw new RecursoNaoEncontrado("nenhum ponto de taxi encontrado");
        
        return list;
    }
    
    public PontoTaxi buscar(Long taxiId) {
        Optional<PontoTaxi> optional = pontoTaxiRepository.findById(taxiId);
        
        if(!optional.isPresent())
            throw new RecursoNaoEncontrado("nenhum ponto de taxi encontrado");

        return optional.get();
    }
    
    public PontoTaxi gravar(PontoTaxi pontoTaxi) {
        
        PontoTaxi encontrado = pontoTaxiRepository.findByNome(pontoTaxi.getNome());
        
        if(encontrado != null)
                throw new RecursoJaExiste("ponto de taxi ja cadastrado");
        
        pontoTaxi.setDataCadastro(OffsetDateTime.now());
        pontoTaxi.setDataAlteracao(null);
        pontoTaxi.setTaxiId(null);
        
        return pontoTaxiRepository.save(pontoTaxi);
    }
    
    public void apagar(Long taxiId) {
        Optional<PontoTaxi> optional = pontoTaxiRepository.findById(taxiId);
        
        if(!optional.isPresent())
            throw new RecursoNaoEncontrado("nenhum ponto de taxi encontrado");
        
        pontoTaxiRepository.delete(optional.get());
    }
}
