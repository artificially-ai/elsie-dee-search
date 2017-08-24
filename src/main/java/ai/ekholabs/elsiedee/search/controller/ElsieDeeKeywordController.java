package ai.ekholabs.elsiedee.search.controller;

import java.util.List;

import ai.ekholabs.elsiedee.search.model.Acknowledge;
import ai.ekholabs.elsiedee.search.model.AssetKeyword;
import ai.ekholabs.elsiedee.search.service.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
public class ElsieDeeKeywordController {

  private final ElasticSearchService elasticSearchService;

  @Autowired
  public ElsieDeeKeywordController(final ElasticSearchService elasticSearchService) {
    this.elasticSearchService = elasticSearchService;
  }

  @GetMapping(value = "/createKeywordIndex", produces = APPLICATION_JSON_UTF8_VALUE)
  public Acknowledge createKeywordIndex() {
    return elasticSearchService.createIndex(AssetKeyword.class);
  }

  @PostMapping(value = "/createKeyword", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
  public AssetKeyword createKeyword(@RequestBody final AssetKeyword assetKeyword) {
    return elasticSearchService.createKeyword(assetKeyword);
  }

  @PostMapping(value = "/findKeywords", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
  public List<AssetKeyword> getAssetKeyword(@RequestBody final AssetKeyword assetKeyword) {
    return elasticSearchService.findKeywords(assetKeyword);
  }

  @DeleteMapping("/keyword/{id}")
  public ResponseEntity deleteKeyword(final @PathVariable String id) {
    elasticSearchService.deleteKeyword(id);
    return ResponseEntity.status(202).build();
  }
}
