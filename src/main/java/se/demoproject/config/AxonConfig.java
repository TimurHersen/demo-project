package se.demoproject.config;

import org.axonframework.config.ConfigurerModule;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Bean(name = "axonServerConfigurerModule")
    public ConfigurerModule axonServerConfigurerModule() {
        return configurer -> configurer.eventProcessing(EventProcessingConfigurer::usingTrackingEventProcessors);
    }
}
