package ai.ekholabs.elsiedee.search.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "keys", type = "asset_keyword")
public class AssetKeyword {

  @Id
  private String id;

  private String assetTitle;

  @Field(type = FieldType.Nested)
  public List<Keyword> keywords;

  AssetKeyword() {
  }

  public AssetKeyword(final String assetTitle, final List<Keyword> keywords) {
    this.assetTitle = assetTitle;
    this.keywords = keywords;
  }

  public String getId() {
    return id;
  }

  public String getAssetTitle() {
    return assetTitle;
  }

  public List<Keyword> getKeywords() {
    return keywords;
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
    if (assetTitle != null ? !assetTitle.equals(that.assetTitle) : that.assetTitle != null) {
      return false;
    }
    return keywords != null ? keywords.equals(that.keywords) : that.keywords == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (assetTitle != null ? assetTitle.hashCode() : 0);
    result = 31 * result + (keywords != null ? keywords.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "AssetKeyword{" +
        "id='" + id + '\'' +
        ", assetTitle='" + assetTitle + '\'' +
        ", keywords=" + keywords +
        '}';
  }
}
