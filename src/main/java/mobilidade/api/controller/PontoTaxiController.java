
package mobilidade.api.controller;

import mobilidade.api.model.PontoTaxiInput;
import mobilidade.api.model.PontoTaxiListResponse;
import mobilidade.api.model.PontoTaxiResponse;
import mobilidade.domain.model.PontoTaxi;
import mobilidade.domain.service.PontoTaxiService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marcos
 * @since 05/07/2020
 */
@RestController
@RequestMapping("/taxi")
public class PontoTaxiController {

    
    @Autowired
    private PontoTaxiService pontoTaxiService;

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna todos os pontos de taxi cadastrados"),
        @ApiResponse(code = 404, message = "Nenhum ponto de taxi encontrado"),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listar() {
        List<PontoTaxi> list = pontoTaxiService.listar();
        PontoTaxiListResponse response = PontoTaxiListResponse.castFromDomain(list);
        return ResponseEntity.ok(response);
    }
    
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna o ponto de taxi pelo taxi_id"),
        @ApiResponse(code = 404, message = "Nenhum ponto de taxi encontrado"),
    })
    @GetMapping(path = "/{taxi_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity buscar(@PathVariable Long taxi_id){
        PontoTaxi pontoTaxi = pontoTaxiService.buscar(taxi_id);
        PontoTaxiResponse response = PontoTaxiResponse.castFromDomain(pontoTaxi);
        return ResponseEntity.ok(response);
    }
    
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Ponto de taxi cadastrado ou alterado corretamente"),
        @ApiResponse(code = 409, message = "O ponto de taxi já está cadastrado e não sofreu nenhuma alteração"),
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, 
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PontoTaxiResponse gravar(@Valid @RequestBody PontoTaxiInput input) {
        PontoTaxi pontoTaxi = PontoTaxiInput.castToDomain(input);
        pontoTaxi = pontoTaxiService.gravar(pontoTaxi);
        return PontoTaxiResponse.castFromDomain(pontoTaxi);
    }
}
