
package mobilidade.api.controller;

import mobilidade.api.model.ItinerarioInput;
import mobilidade.api.model.ItinerarioResponse;
import mobilidade.domain.model.Onibus;
import mobilidade.domain.model.Parada;
import mobilidade.domain.service.ItinerarioService;
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
@RequestMapping("/onibus/{onibus_id}/itinerario")
public class ItinerarioController {
    
    @Autowired
    private ItinerarioService itinerarioService;

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna o itinerário do ônibus"),
        @ApiResponse(code = 404, message = "Nenhum ônibus ou itinerário encontrado"),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity buscar(@PathVariable Long onibus_id) {
        Onibus onibus = itinerarioService.buscar(onibus_id);
        ItinerarioResponse response = ItinerarioResponse.castFromDomain(onibus);
        return ResponseEntity.ok(response);
    }
    
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Itinerário cadastrado ou alterado corretamente"),
        @ApiResponse(code = 404, message = "Nenhum ônibus encontrado"),
        @ApiResponse(code = 409, message = "O itinerário já está cadastrado e não sofreu nenhuma alteração"),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ItinerarioResponse gravar(@PathVariable Long onibus_id, 
            @RequestBody @Valid ItinerarioInput input) {
        
        List<Parada> paradas = ItinerarioInput.castToDomain(input);
        Onibus onibus = itinerarioService.gravar(onibus_id, paradas);
        ItinerarioResponse response = ItinerarioResponse.castFromDomain(onibus);
        
        return response;
    }
    
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Itinerário apagado corretamente"),
        @ApiResponse(code = 404, message = "Nenhum ônibus ou itinerário encontrado"),
    })
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar(@PathVariable Long onibus_id) {
        itinerarioService.apagar(onibus_id);
    }
}
