package ai.ekholabs.elsiedee.search.repo;

import ai.ekholabs.elsiedee.search.model.Asset;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AssetRepository extends ElasticsearchRepository<Asset, String> {

  Asset findByTitle(final String title);
}
