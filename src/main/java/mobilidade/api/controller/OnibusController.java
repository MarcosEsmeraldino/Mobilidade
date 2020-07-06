
package mobilidade.api.controller;

import mobilidade.api.model.OnibusInput;
import mobilidade.api.model.OnibusListResponse;
import mobilidade.api.model.OnibusResponse;
import mobilidade.domain.model.Onibus;
import mobilidade.domain.service.OnibusService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
 * @since 04/07/2020
 */
@RestController
@RequestMapping("/onibus")
public class OnibusController {
    
    @Autowired
    private OnibusService onibusService;

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna todos os ônibus cadastrados"),
        @ApiResponse(code = 404, message = "Nenhum ônibus encontrado"),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listar() {
        List<Onibus> list = onibusService.listar();
        OnibusListResponse responseList = OnibusListResponse.castFromDomain(list);
        return ResponseEntity.ok(responseList);
    }
    
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna o ônibus pelo onibus_id"),
        @ApiResponse(code = 404, message = "Nenhum ônibus encontrado"),
    })
    @GetMapping(path = "/{onibus_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity buscar(@PathVariable Long onibus_id){
        Onibus onibus = onibusService.buscar(onibus_id);
        OnibusResponse response = OnibusResponse.castFromDomain(onibus);
        return ResponseEntity.ok(response);
    }
    
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Ônibus cadastrado ou alterado corretamente"),
        @ApiResponse(code = 409, message = "O ônibus já está cadastrado e não sofreu nenhuma alteração"),
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, 
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OnibusResponse gravar(@Valid @RequestBody OnibusInput input) {
        Onibus onibus = OnibusInput.castToDomain(input);
        onibus = onibusService.gravar(onibus);
        return OnibusResponse.castFromDomain(onibus);
    }
    
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Ônibus apagado corretamente"),
        @ApiResponse(code = 404, message = "Nenhum ônibus encontrado"),
    })
    @DeleteMapping("/{onibus_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar(@PathVariable Long onibus_id) {
        onibusService.apagar(onibus_id);
    }
    
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna todos os ônibus que possuem alguma parada dentro do ponto de referência"),
        @ApiResponse(code = 404, message = "Nenhum ônibus encontrado"),
    })
    @GetMapping(path = "/search/referencia/lat/{lat}/lon/{lng}/raio/{raio}", 
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listarPorReferencia(@PathVariable double lat,
            @PathVariable double lng, @PathVariable double raio) {
        
        List<Onibus> list = onibusService.listarPorReferencia(lat, lng, raio);
        OnibusListResponse responseList = OnibusListResponse.castFromDomain(list);
        return ResponseEntity.ok(responseList);
    }
    
}
