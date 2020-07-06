
package mobilidade.integration.service;

import mobilidade.domain.exception.RecursoNaoEncontrado;
import mobilidade.integration.model.Itinerario;
import mobilidade.integration.model.Onibus;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author marcos
 * @since 04/07/2020
 */
@Service
public class DataPoaLinhaService {
    
    private final String URL_BASE = "http://www.poatransporte.com.br/php/facades/process.php";
    
    public List<Onibus> buscarOnibusPorNome(String nome) {
        
        String url = URL_BASE + "?a=nc&t=o&p=" + nome;

        RestTemplate restTemplate = getRestTemplateCustom();

        ResponseEntity<Onibus[]> response;
        try {
            response = restTemplate.getForEntity(url, Onibus[].class);
        } catch (RestClientException ex) {
            throw new RecursoNaoEncontrado("linhas nao encontradas");
        }
        
        return Arrays.asList(response.getBody());
    }
    
    public Itinerario buscarItinerarioPorOnibus(String idLinha) {
        
        String url = URL_BASE + "?a=il&p=" + idLinha;

        RestTemplate restTemplate = getRestTemplateCustom();

        ResponseEntity<Map> response;
        try {
            response = restTemplate.getForEntity(url, Map.class);
        } catch (RestClientException ex) {
            throw new RecursoNaoEncontrado("itinerario nao encontrada");
        }

        Map responseMap = response.getBody();

        String idlinha = responseMap.get("idlinha").toString();
        String nome = responseMap.get("nome").toString();
        String codigo = responseMap.get("codigo").toString();
        
        Integer i = 1;
        boolean hasNext = true;
        Map<Integer, Itinerario.Ponto> pontos = new HashMap();
        
        do {
            
            Map mapPonto = ((Map) responseMap.get(i.toString()));
            if(mapPonto == null)
                hasNext = false;
            else {
                String lat = mapPonto.get("lat").toString();
                String lng = mapPonto.get("lng").toString();
                
                Itinerario.Ponto ponto = new Itinerario.Ponto();
                ponto.setLat(lat);
                ponto.setLng(lng);
                pontos.put(i, ponto);
                i++;
            }
            
        }while(hasNext);
        
        
        Itinerario itinerario = new Itinerario();
        itinerario.setIdLinha(Long.parseLong(idlinha));
        itinerario.setNome(nome);
        itinerario.setCodigo(codigo);
        itinerario.setPontos(pontos);
        
        return itinerario;
    }
    
    private RestTemplate getRestTemplateCustom() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_HTML));

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Arrays.asList(converter));
        
        return restTemplate;
    }
}
