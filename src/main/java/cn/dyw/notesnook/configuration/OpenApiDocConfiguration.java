package cn.dyw.notesnook.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文档配置
 *
 * @author dyw770
 * @date 2024-11-26
 */
@Configuration
public class OpenApiDocConfiguration {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Notesnook Admin API")
                        .description("Notesnook Admin API")
                        .version("0.0.1"))
                .externalDocs(new ExternalDocumentation()
                        .description("Notesnook Admin API")
                        .url("https://github.com/dyw770"))
                .components(new Components()
                        .addSecuritySchemes("basicScheme",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("basic")
                        )
                )
                .addSecurityItem(new SecurityRequirement()
                        .addList("basicScheme"));
    }
}
