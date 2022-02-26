package com.polyfrontiere.service.declaration;

import com.polyfrontiere.api.source.declarations.DeclarationsSourceAPI;
import com.polyfrontiere.api.source.declarations.impl.BasicDeclarationsSourceAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {
    @Bean
    public DeclarationsSourceAPI getDeclarationApi(){
        return new BasicDeclarationsSourceAPI() ;
    }
}
