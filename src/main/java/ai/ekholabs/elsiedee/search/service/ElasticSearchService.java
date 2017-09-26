package ai.ekholabs.elsiedee.search.service;

import java.util.List;

import ai.ekholabs.elsiedee.search.model.Acknowledge;
import ai.ekholabs.elsiedee.search.model.Asset;
import ai.ekholabs.elsiedee.search.repo.AssetRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.joining;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.nestedQuery;

@Service
public class ElasticSearchService {

  private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ElasticSearchService.class);

  private final ElasticsearchOperations elasticsearchOperations;
  private final AssetRepository assetRepository;

  @Autowired
  public ElasticSearchService(final ElasticsearchOperations elasticsearchOperations,
                              final AssetRepository assetRepository) {
    this.elasticsearchOperations = elasticsearchOperations;
    this.assetRepository = assetRepository;
  }

  public Acknowledge createIndex(final Class indexClass) {
    return new Acknowledge(elasticsearchOperations.createIndex(indexClass));
  }

  public Asset createAsset(final Asset asset) {
    return assetRepository.save(asset);
  }

  public List<Asset> findByKeywords(final List<String> keywords) {
    final String collectedKeywords = keywords.stream()
        .collect(joining(" "));

    LOGGER.info("Collected keywords list: {}", collectedKeywords);

    final QueryBuilder builder = nestedQuery("subtitles", boolQuery()
        .should(matchQuery("subtitles.text", collectedKeywords)));

    final SearchQuery searchQuery = new NativeSearchQueryBuilder()
        .withQuery(builder)
        .build();

    return elasticsearchOperations.queryForList(searchQuery, Asset.class);
  }
}
