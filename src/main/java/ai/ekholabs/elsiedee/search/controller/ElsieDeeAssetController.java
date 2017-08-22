package ai.ekholabs.elsiedee.search.controller;

import java.util.List;

import ai.ekholabs.elsiedee.search.model.Acknowledge;
import ai.ekholabs.elsiedee.search.model.Asset;
import ai.ekholabs.elsiedee.search.model.AssetKeyword;
import ai.ekholabs.elsiedee.search.service.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Arrays.asList;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
public class ElsieDeeAssetController {

  private final ElasticSearchService elasticSearchService;

  @Autowired
  public ElsieDeeAssetController(final ElasticSearchService elasticSearchService) {
    this.elasticSearchService = elasticSearchService;
  }

  @GetMapping(value = "/createAssetIndex", produces = APPLICATION_JSON_UTF8_VALUE)
  public Acknowledge createAssetIndex() {
    return elasticSearchService.createIndex(Asset.class);
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
