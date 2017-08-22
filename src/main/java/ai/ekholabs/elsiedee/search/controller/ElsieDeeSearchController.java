package ai.ekholabs.elsiedee.search.controller;

import ai.ekholabs.elsiedee.search.model.Asset;
import ai.ekholabs.elsiedee.search.service.ElasticSearchService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
public class ElsieDeeSearchController {

  private final ElasticSearchService elasticSearchService;

  public ElsieDeeSearchController(final ElasticSearchService elasticSearchService) {
    this.elasticSearchService = elasticSearchService;
  }

  @PostMapping(value = "/{title}", produces = APPLICATION_JSON_UTF8_VALUE)
  public Asset asset(@PathVariable final String title) {
    return elasticSearchService.findByTitle(title);
  }
}
