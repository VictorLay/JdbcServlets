package com.victor.latyshey.beans.builder;

import com.victor.latyshey.beans.book.Author;
import com.victor.latyshey.beans.book.Book;
import com.victor.latyshey.beans.book.Genre;
import com.victor.latyshey.beans.book.Publishing;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookBuilder {

  private Integer id;
  private String title;
  private Genre genre;
  private Publishing publishing;
  private int year;
  private float price;
  private List<Author> authors;

  public BookBuilder() {
    this.authors = new ArrayList<>();
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  public void setPublishing(Publishing publishing) {
    this.publishing = publishing;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public void setAuthors(List<Author> authors) {
    this.authors = new ArrayList<>(authors);
  }

  public void addAuthor(Author author){
    this.authors.add(author);
  }

  public Book getResult(){
    return new Book(id, title, genre, publishing, year, price, new ArrayList<>(authors));
  }

  public void reset(){
    this.id = null;
    this.title = null;
    this.genre = null;
    this.publishing = null;
    this.year = -1;
    this.price = -1f;
    this.authors.clear();

  }
}
