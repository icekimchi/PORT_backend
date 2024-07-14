package com.HP028.chatbot.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(WebClient.Builder webClientBuilder) {
        String llmServerUrl = "http://localhost:8000";
        return webClientBuilder.baseUrl(llmServerUrl).build();
    }
}
