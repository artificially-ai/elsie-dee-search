package ai.ekholabs.elsiedee.search.repo;

import java.util.List;

import ai.ekholabs.elsiedee.search.model.AssetDetails;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends ElasticsearchRepository<AssetDetails, String> {

  List<AssetDetails> findByAssetTitle(final String assetTitle);
}
