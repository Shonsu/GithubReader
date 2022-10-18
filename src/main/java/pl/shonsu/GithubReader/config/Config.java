package pl.shonsu.GithubReader.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import pl.shonsu.GithubReader.exceptions.GHNotFoundException;
import reactor.core.publisher.Mono;


@Configuration
public class Config {

    private static final String BASE_URL = "https://api.github.com";

    ExchangeFilterFunction errorResponseFilter = ExchangeFilterFunction
            .ofResponseProcessor(this::exchangeFilterResponseProcessor);

    @Bean
    WebClient configure() {
        return WebClient.builder()
                .filter(errorResponseFilter)
                .baseUrl(BASE_URL)
                .defaultHeader(MediaType.APPLICATION_JSON_VALUE)
                .build();
    }


    Mono<ClientResponse> exchangeFilterResponseProcessor(ClientResponse response) {
        HttpStatus status = response.statusCode();
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            return response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new RuntimeException(body)));
        }
        if (HttpStatus.BAD_REQUEST.equals(status)) {
            return response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new RuntimeException(body)));
        }
        if (HttpStatus.NOT_FOUND.equals(status)) {
            return response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new GHNotFoundException("Resources not found.")));
        }
        return Mono.just(response);
    }
}
