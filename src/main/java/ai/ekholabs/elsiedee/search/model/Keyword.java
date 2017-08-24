package ai.ekholabs.elsiedee.search.model;

public class Keyword {

  private String label;


  Keyword() {
  }

  public Keyword(final String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final Keyword keyword = (Keyword) o;

    return label != null ? label.equals(keyword.label) : keyword.label == null;
  }

  @Override
  public int hashCode() {
    return label != null ? label.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "Keyword{" +
        "label='" + label + '\'' +
        '}';
  }
}
