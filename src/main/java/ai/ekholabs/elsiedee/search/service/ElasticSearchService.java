package ai.ekholabs.elsiedee.search.service;

import java.util.List;

import ai.ekholabs.elsiedee.search.model.Asset;
import ai.ekholabs.elsiedee.search.model.AssetKeyword;
import ai.ekholabs.elsiedee.search.repo.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@Service
public class ElasticSearchService {

  private final ElasticsearchOperations elasticsearchTemplate;
  private final AssetRepository assetRepository;

  @Autowired
  public ElasticSearchService(final ElasticsearchOperations elasticsearchTemplate,
                              final AssetRepository assetRepository) {
    this.elasticsearchTemplate = elasticsearchTemplate;
    this.assetRepository = assetRepository;
  }

  public Asset findByTitle(final String title) {
    return assetRepository.findByTitle(title);
  }

  public List<Asset> findByKeywords(final List<AssetKeyword> keywords) {
    SearchQuery searchQuery = new NativeSearchQueryBuilder()
        .withQuery(matchQuery("asset.subtitles", "elasticsearch data"))
        .build();

    return elasticsearchTemplate.queryForList(searchQuery, Asset.class);
  }
}
