package pl.shonsu.GithubReader.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class Config {
    
    private static String BASE_URL = "https://api.github.com";

    @Bean
    WebClient configure(){
        return WebClient.builder()
        .baseUrl(BASE_URL)
        .defaultHeader( MediaType.APPLICATION_JSON_VALUE) 
        .build();
    }

}
