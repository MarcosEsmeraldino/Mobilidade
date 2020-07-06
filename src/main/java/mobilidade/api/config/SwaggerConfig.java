package mobilidade.api.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Disponível em: /swagger-ui.html
 *
 * @author marcos
 * @since 05/07/2020
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("mobilidade.api.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessages())
                .globalResponseMessage(RequestMethod.POST, responseMessages())
                .globalResponseMessage(RequestMethod.PUT, responseMessages())
                .globalResponseMessage(RequestMethod.PATCH, responseMessages())
                .globalResponseMessage(RequestMethod.DELETE, responseMessages())
                .apiInfo(apiInfo());
    }

    private List<ResponseMessage> responseMessages() {
        return new ArrayList<ResponseMessage>() {
            {
                add(new ResponseMessageBuilder()
                        .code(400)
                        .message("Erro na requisição")
                        .build());
                add(new ResponseMessageBuilder()
                        .code(500)
                        .message("Erro interno")
                        .responseModel(new ModelRef("Error"))
                        .build());
            }
        };
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API Mobilidade")
                .description("REST API baseado no consumo da API http://datapoa.com.br/group/about/mobilidade")
                .build();
    }
}
