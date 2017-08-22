package ai.ekholabs.elsiedee.search.model;

public class Acknowledge {

  private Boolean acknowedged;

  public Acknowledge() {
  }

  public Acknowledge(final Boolean acknowedged) {
    this.acknowedged = acknowedged;
  }

  public Boolean getAcknowedged() {
    return acknowedged;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final Acknowledge that = (Acknowledge) o;

    return acknowedged != null ? acknowedged.equals(that.acknowedged) : that.acknowedged == null;
  }

  @Override
  public int hashCode() {
    return acknowedged != null ? acknowedged.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "Acknowledge{" +
        "acknowedged=" + acknowedged +
        '}';
  }
}
