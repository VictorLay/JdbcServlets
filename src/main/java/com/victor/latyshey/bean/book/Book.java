package com.victor.latyshey.bean.book;

import com.victor.latyshey.bean.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book extends Entity {

  private String title;
  private Genre genre;
  private Publishing publishing;
  private int year;
  private float price;
  private List<Author> authors;

  public Book(Integer id, String title, Genre genre, Publishing publishing, int year, float price){
    super(id);
    this.title = title;
    this.genre = genre;
    this.publishing = publishing;
    this.year = year;
    this.price = price;
    this.authors = new ArrayList<>();
  }

  public Book(Integer id, String title, Genre genre, Publishing publishing, int year, float price,
      List<Author> authors) {
    super(id);
    this.title = title;
    this.genre = genre;
    this.publishing = publishing;
    this.year = year;
    this.price = price;
    this.authors = authors;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Genre getGenre() {
    return genre;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  public Publishing getPublishing() {
    return publishing;
  }

  public void setPublishing(Publishing publishing) {
    this.publishing = publishing;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public List<Author> getAuthors() {
    return authors;
  }

  public void setAuthors(List<Author> authors) {
    this.authors = authors;
  }

  @Override
  public String toString() {
    return "Book{" +
        "title='" + title + '\'' +
        ", genre=" + genre.getGenreName() +
        ", publishing=" + publishing.getPublishingName() +
        ", year=" + year +
        ", price=" + price +
        ", authors=" + authors +
        '}';
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
    Book book = (Book) o;
    return year == book.year && Float.compare(book.price, price) == 0
        && Objects.equals(title, book.title) && Objects.equals(genre, book.genre)
        && Objects.equals(publishing, book.publishing) && Objects.equals(authors,
        book.authors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), title, genre, publishing, year, price, authors);
  }
}
