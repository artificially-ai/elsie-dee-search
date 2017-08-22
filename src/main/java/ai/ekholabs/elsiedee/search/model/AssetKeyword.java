package ai.ekholabs.elsiedee.search.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "keys", type = "asset_keyword")
public class AssetKeyword {

  @Id
  private String id;

  public String keyword;

  AssetKeyword() {
  }

  public AssetKeyword(final String keyword) {
    this.keyword = keyword;
  }

  public String getId() {
    return id;
  }

  public String getKeyword() {
    return keyword;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final AssetKeyword that = (AssetKeyword) o;

    if (id != null ? !id.equals(that.id) : that.id != null) {
      return false;
    }
    return keyword != null ? keyword.equals(that.keyword) : that.keyword == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (keyword != null ? keyword.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "AssetKeyword{" +
        "id='" + id + '\'' +
        ", keyword='" + keyword + '\'' +
        '}';
  }
}
