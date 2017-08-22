package ai.ekholabs.elsiedee.search.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "library", type = "asset")
public class Asset {

  @Id
  private String id;

  private String title;
  private String subtitles;

  Asset() {
  }

  public Asset(final String title, final String subtitles) {
    this.title = title;
    this.subtitles = subtitles;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getSubtitles() {
    return subtitles;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final Asset asset = (Asset) o;

    if (id != null ? !id.equals(asset.id) : asset.id != null) {
      return false;
    }
    if (title != null ? !title.equals(asset.title) : asset.title != null) {
      return false;
    }
    return subtitles != null ? subtitles.equals(asset.subtitles) : asset.subtitles == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (title != null ? title.hashCode() : 0);
    result = 31 * result + (subtitles != null ? subtitles.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Asset{" +
        "id='" + id + '\'' +
        ", title='" + title + '\'' +
        ", subtitles='" + subtitles + '\'' +
        '}';
  }
}