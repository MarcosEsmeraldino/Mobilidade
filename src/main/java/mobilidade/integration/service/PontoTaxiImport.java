package mobilidade.integration.service;

import mobilidade.domain.model.PontoTaxi;
import mobilidade.domain.service.PontoTaxiService;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.time.OffsetDateTime;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author marcos
 * @since 06/07/2020
 */
@Component
public class PontoTaxiImport {
    
    @Autowired
    private PontoTaxiService service;

    @PostConstruct
    public void init() {

        try {
            
            Resource resource = new ClassPathResource("/static/carga_taxi.txt");
            String path = resource.getURL().getPath();

            FileInputStream stream = new FileInputStream(path);
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(reader);
            String linha = br.readLine();
            while (linha != null) {

                linha = br.readLine();
                String[] split = linha.split("#");

                PontoTaxi pontoTaxi = new PontoTaxi();
                pontoTaxi.setNome(split[0]);
                pontoTaxi.setLat(split[1]);
                pontoTaxi.setLng(split[2]);

                OffsetDateTime date;
                try {
                    date = OffsetDateTime.parse(split[3]);
                } catch (Exception e) {
                    date = OffsetDateTime.now();
                }
                pontoTaxi.setDataCadastro(date);
                
                service.gravar(pontoTaxi);

            }
        } catch(Exception e) {
            // do nothing
        }
    }
}
