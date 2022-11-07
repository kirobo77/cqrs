/**************************************************************************************
 * ICIS version 1.0
 *
 * Copyright ⓒ 2022 kt/ktds corp. All rights reserved.
 *
 * This is a proprietary software of kt corp, and you may not use this file except in compliance
 * with license agreement with kt corp. Any redistribution or use of this software, with or without
 * modification shall be strictly prohibited without prior written approval of kt corp, and the
 * copyright notice above does not evidence any actual or intended publication of such software.
 *************************************************************************************/

package io.dddbyexamples.cqrs.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
  
  @Bean
  public GroupedOpenApi openApi() {
    String[] paths = {"/**"};
    return GroupedOpenApi.builder().group("api-group").pathsToMatch(paths).build();
  }
  
  @Bean
  public OpenAPI springShopOpenAPI() {
      return new OpenAPI()
              .info(new Info().title("")
              .description("")
              .version("")
              .license(new License().name("").url("")))
              .externalDocs(new ExternalDocumentation()
              .description("")
              .url(""));
  }  

}
