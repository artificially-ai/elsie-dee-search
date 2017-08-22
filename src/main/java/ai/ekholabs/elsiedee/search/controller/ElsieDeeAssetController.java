package ai.ekholabs.elsiedee.search.controller;

import java.util.List;

import ai.ekholabs.elsiedee.search.model.Asset;
import ai.ekholabs.elsiedee.search.model.AssetKeyword;
import ai.ekholabs.elsiedee.search.service.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Arrays.asList;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
public class ElsieDeeSearchController {

  private final ElasticSearchService elasticSearchService;

  @Autowired
  public ElsieDeeSearchController(final ElasticSearchService elasticSearchService) {
    this.elasticSearchService = elasticSearchService;
  }

  @GetMapping("/createKeywordIndex")
  public ResponseEntity createKeywordIndex() {
    elasticSearchService.createIndex(AssetKeyword.class);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/createAssetIndex")
  public ResponseEntity createAssetIndex() {
    elasticSearchService.createIndex(Asset.class);
    return ResponseEntity.ok().build();
  }

  @PostMapping(value = "/createAsset", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
  public Asset createAsset(@RequestBody final Asset asset) {
    return elasticSearchService.createAsset(asset);
  }

  @PostMapping(value = "/assets", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
  public List<Asset> assets(@RequestBody final AssetKeyword[] keywords) {
    return elasticSearchService.findByKeywords(asList(keywords));
  }
}
