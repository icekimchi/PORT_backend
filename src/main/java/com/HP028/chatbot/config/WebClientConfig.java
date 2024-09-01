package com.HP028.chatbot.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(WebClient.Builder webClientBuilder) {
        String llmServerUrl = "https://many-together-baboon.ngrok-free.app";
        return webClientBuilder.baseUrl(llmServerUrl).build();
    }
}
