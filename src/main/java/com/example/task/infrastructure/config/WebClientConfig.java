package com.example.task.infrastructure.config;

import io.netty.channel.ChannelOption;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
class WebClientConfig {

    @Bean
    WebClient nbpServiceWebClient(NBPClientProperties nbpClientProperties) {
        HttpClient client = HttpClient.create()
                .responseTimeout(nbpClientProperties.getResponseTimeout())
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, (int) (nbpClientProperties.getConnectionTimeout().toMillis()));
        return WebClient.builder().baseUrl(nbpClientProperties.getUrl())
                .clientConnector(new ReactorClientHttpConnector(client))
                .build();
    }

}

@Setter
@Getter
@ConfigurationProperties(prefix = "client.nbp")
class NBPClientProperties {

    private String url;

    private Duration responseTimeout;

    private Duration connectionTimeout;

}