package ai.ekholabs.elsiedee.search.config;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "ai.ekholabs.elsiedee.search.repo")
public class ElasticSearchConfiguration {

  @Value("${elsie-dee.elasticsearch.host}")
  private String esHost;

  @Value("${elsie-dee.elasticsearch.port}")
  private int esPort;

  @Value("${elsie-dee.elasticsearch.cluster-name}")
  private String esClusterName;

  @Bean
  public Client client() throws Exception {

    Settings esSettings = Settings.settingsBuilder()
        .put("cluster.name", esClusterName)
        .build();

    return TransportClient.builder()
        .settings(esSettings)
        .build()
        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(esHost), esPort));
  }

  @Bean
  public ElasticsearchOperations elasticsearchTemplate() throws Exception {
    return new ElasticsearchTemplate(client());
  }
}
