package com.victor.latyshey.bean.book;

import com.victor.latyshey.bean.Entity;
import java.util.Objects;

public class Publishing extends Entity {

  private String publishingName;

  public Publishing(Integer id) {
    super(id);
  }

  public Publishing(Integer id, String publishingName) {
    super(id);
    this.publishingName = publishingName;
  }

  public String getPublishingName() {
    return publishingName;
  }

  public void setPublishingName(String publishingName) {
    this.publishingName = publishingName;
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
    Publishing that = (Publishing) o;
    return Objects.equals(publishingName, that.publishingName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), publishingName);
  }

  @Override
  public String toString() {
    return publishingName;
  }
}
