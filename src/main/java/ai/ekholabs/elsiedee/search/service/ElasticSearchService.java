package ai.ekholabs.elsiedee.search.service;

import java.util.List;

import ai.ekholabs.elsiedee.search.model.Asset;
import ai.ekholabs.elsiedee.search.model.AssetKeyword;
import ai.ekholabs.elsiedee.search.repo.AssetRepository;
import ai.ekholabs.elsiedee.search.repo.KeywordRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.joining;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@Service
public class ElasticSearchService {

  private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ElasticSearchService.class);

  private final ElasticsearchOperations elasticsearchOperations;
  private final AssetRepository assetRepository;
  private final KeywordRepository keywordRepository;

  @Autowired
  public ElasticSearchService(final ElasticsearchOperations elasticsearchOperations,
                              final AssetRepository assetRepository, final KeywordRepository keywordRepository) {
    this.elasticsearchOperations = elasticsearchOperations;
    this.assetRepository = assetRepository;
    this.keywordRepository = keywordRepository;
  }

  public void createIndex(final Class indexClass) {
    elasticsearchOperations.createIndex(indexClass);
  }

  public Asset createAsset(final Asset asset) {
    return assetRepository.save(asset);
  }

  public AssetKeyword createKeyword(final AssetKeyword assetKeyword) {
    return keywordRepository.save(assetKeyword);
  }

  public List<Asset> findByKeywords(final List<AssetKeyword> keywords) {
    final String collectedKeywords = keywords
        .stream()
        .map(assetKeyword -> assetKeyword.keyword)
        .collect(joining(" "));

    LOGGER.info("Collected keywords list: {}", collectedKeywords);

    final SearchQuery searchQuery = new NativeSearchQueryBuilder()
        .withQuery(matchAllQuery())
        .withFilter(boolQuery().should(matchQuery("subtitles", collectedKeywords)))
        .build();

    return elasticsearchOperations.queryForList(searchQuery, Asset.class);
  }
}
