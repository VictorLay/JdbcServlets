package com.victor.latyshey.beans;

import java.util.Arrays;

public class Book {

  private int isbn;
  private String title;
  private String genre;
  private String publishing;
  private int year;
  private String bookBinding;
  private String bookDescription;
  private float price;
  private String[] authors;

  public Book() {
  }

  public Book(int isbn, String title, String genre, String publishing, int year,
      String bookBinding, String bookDescription, float price) {
    this.isbn = isbn;
    this.title = title;
    this.genre = genre;
    this.publishing = publishing;
    this.year = year;
    this.bookBinding = bookBinding;
    this.bookDescription = bookDescription;
    this.price = price;
  }

  public String[] getAuthors() {
    return authors;
  }

  public void setAuthors(String[] authors) {
    this.authors = authors;
  }

  public int getIsbn() {
    return isbn;
  }

  public void setIsbn(int isbn) {
    this.isbn = isbn;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getPublishing() {
    return publishing;
  }

  public void setPublishing(String publishing) {
    this.publishing = publishing;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public String getBookBinding() {
    return bookBinding;
  }

  public void setBookBinding(String bookBinding) {
    this.bookBinding = bookBinding;
  }

  public String getBookDescription() {
    return bookDescription;
  }

  public void setBookDescription(String bookDescription) {
    this.bookDescription = bookDescription;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "Book{" +
        "isbn=" + isbn +
        ", title='" + title + '\'' +
        ", genre='" + genre + '\'' +
        ", publishing='" + publishing + '\'' +
        ", year=" + year +
        ", bookBinding='" + bookBinding + '\'' +
        ", bookDescription='" + bookDescription + '\'' +
        ", price=" + price +
        ", authors=" + Arrays.toString(authors) +
        '}';
  }
}
