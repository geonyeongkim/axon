package com.msa.instagram.clone.common.configuration;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by geonyeong.kim on 2019-12-31
 */
@Slf4j
@Configuration
public class EsConfig {

    @Value("${spring.data.es.clusterName}")
    private String clusterName;

    @Value("${spring.data.es.host}")
    private String esHost;

    @Value("${spring.data.es.port}")
    private int esPort;

    @Bean
    public Client client() throws UnknownHostException {
        final Settings elasticsearchSettings = Settings.builder()
                .put("cluster.name", clusterName).build();
        final TransportClient client = new PreBuiltTransportClient(elasticsearchSettings);
        client.addTransportAddress(new TransportAddress(InetAddress.getByName(esHost), esPort));
        return client;
    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() throws UnknownHostException {
        return new ElasticsearchTemplate(client());
    }
}
