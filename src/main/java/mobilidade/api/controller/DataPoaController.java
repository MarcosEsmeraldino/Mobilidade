
package mobilidade.api.controller;

import mobilidade.integration.model.Itinerario;
import mobilidade.integration.model.Onibus;
import mobilidade.integration.service.DataPoaLinhaService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marcos
 * @since 04/07/2020
 */
@RestController
@RequestMapping("/datapoa")
public class DataPoaController {
    
    @Autowired
    private DataPoaLinhaService dataPoaLinhaService;

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna todos os ônibus com o nome buscado"),
        @ApiResponse(code = 404, message = "Nenhum ônibus encontrado"),
    })
    @GetMapping(path = "/onibus/search/nome/{nome}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity filtrarLinhasPorNome(@PathVariable String nome) {
        List<Onibus> list = dataPoaLinhaService.buscarOnibusPorNome(nome);
        return ResponseEntity.ok(list);
    }
    
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna o itinerário do ônibus"),
        @ApiResponse(code = 404, message = "Nenhum ônibus ou itinerário encontrado"),
    })
    @GetMapping(path = "/onibus/{id}/itinerario",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity buscarItinerarioPorOnibus(@PathVariable Long id) {
        Itinerario itinerario = dataPoaLinhaService.buscarItinerarioPorOnibus(id.toString());
        return ResponseEntity.ok(itinerario);
    }
}
