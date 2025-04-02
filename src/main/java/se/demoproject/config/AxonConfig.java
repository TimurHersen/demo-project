package se.demoproject.config;

import org.axonframework.config.ConfigurerModule;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventhandling.tokenstore.inmemory.InMemoryTokenStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Bean(name = "axonServerConfigurerModule")
    public ConfigurerModule axonServerConfigurerModule() {
        return configurer -> configurer.eventProcessing(EventProcessingConfigurer::usingTrackingEventProcessors);
    }

    /*For development environments only, not production*/
    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

}
