package com.victor.latyshey.bean.book;

import com.victor.latyshey.bean.Entity;
import java.util.Objects;

public class Author extends Entity {

  private String name;
  private String country;

  public Author(Integer id, String name, String country) {
    super(id);
    this.name = name;
    this.country = country;
  }

  public Author(String name, String country) {
    this.name = name;
    this.country = country;
  }
  public Author(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getCountry() {
    return country;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCountry(String country) {
    this.country = country;
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
    Author author = (Author) o;
    return Objects.equals(this.name, author.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), name);
  }

  @Override
  public String toString() {
    return "Author " + name + " from " + country + ".";
  }
}
