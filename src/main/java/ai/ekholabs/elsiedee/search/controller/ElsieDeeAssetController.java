package ai.ekholabs.elsiedee.search.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import ai.ekholabs.elsiedee.search.model.Acknowledge;
import ai.ekholabs.elsiedee.search.model.Asset;
import ai.ekholabs.elsiedee.search.model.AssetKeyword;
import ai.ekholabs.elsiedee.search.model.Subtitles;
import ai.ekholabs.elsiedee.search.service.ElasticSearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
public class ElsieDeeAssetController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ElsieDeeAssetController.class);

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
  public Asset createAsset(final @RequestParam(value = "title") String title,
                           final @RequestParam(value = "subtitles") MultipartFile subtitlesFile) throws IOException {

    final StringBuilder builder = new StringBuilder();
    try (BufferedReader buffer = new BufferedReader(new InputStreamReader(subtitlesFile.getInputStream()))) {
      final String subtitlesText = buffer.lines().collect(Collectors.joining(" "));
      builder.append(subtitlesText);
    }

    final Subtitles subtitles = new ObjectMapper().readValue(builder.toString(), Subtitles.class);
    final Asset asset = new Asset(title, subtitles);

    return elasticSearchService.createAsset(asset);
  }

  @PostMapping(value = "/assets", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
  public List<Asset> assets(@RequestBody final AssetKeyword assetKeyword) {
    return elasticSearchService.findByKeywords(assetKeyword.getKeywords());
  }
}
