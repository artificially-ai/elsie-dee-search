package ai.ekholabs.elsiedee.search.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import ai.ekholabs.elsiedee.search.model.Acknowledge;
import ai.ekholabs.elsiedee.search.model.Asset;
import ai.ekholabs.elsiedee.search.model.AssetKeyword;
import ai.ekholabs.elsiedee.search.service.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static java.util.Arrays.asList;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

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

  @PostMapping(value = "/createAsset", consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
  public Asset createAsset(@RequestParam(value = "title") final String title,  final @RequestParam(value = "subtitles") MultipartFile subtitles)
      throws IOException {

    final StringBuilder builder = new StringBuilder();
    try (BufferedReader buffer = new BufferedReader(new InputStreamReader(subtitles.getInputStream()))) {
      final String subtitlesText = buffer.lines().collect(Collectors.joining("\n"));
      builder.append(subtitlesText);
    }

    final Asset asset = new Asset(title, builder.toString());
    return elasticSearchService.createAsset(asset);
  }

  @PostMapping(value = "/assets", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
  public List<Asset> assets(@RequestBody final AssetKeyword[] keywords) {
    return elasticSearchService.findByKeywords(asList(keywords));
  }
}
