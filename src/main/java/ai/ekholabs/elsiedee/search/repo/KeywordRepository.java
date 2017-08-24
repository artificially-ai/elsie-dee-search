package ai.ekholabs.elsiedee.search.repo;

import java.util.List;

import ai.ekholabs.elsiedee.search.model.AssetKeyword;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends ElasticsearchRepository<AssetKeyword, String> {

  List<AssetKeyword> findByAssetTitle(final String assetTitle);
}
