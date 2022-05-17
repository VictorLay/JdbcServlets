package com.victor.latyshey.beans.book;

import com.victor.latyshey.beans.Entity;
import java.util.Objects;

public class Genre extends Entity {

  private String genreName;

  public Genre(Integer id, String genreName) {
    super(id);
    this.genreName = genreName;
  }

  public String getGenreName() {
    return genreName;
  }

  public void setGenreName(String genreName) {
    this.genreName = genreName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Genre genre = (Genre) o;
    return Objects.equals(this.genreName, genre.genreName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), genreName);
  }
}
