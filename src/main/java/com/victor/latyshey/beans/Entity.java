package com.victor.latyshey.beans;

import java.util.Objects;

public abstract class Entity {

  private Integer id;

  public Entity() {
  }

  public Entity(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    Entity otherObj = (Entity) obj;
    return Objects.equals(id, otherObj.getId());
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : -1;
  }

}
