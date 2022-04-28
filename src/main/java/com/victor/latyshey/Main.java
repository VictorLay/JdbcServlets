package com.victor.latyshey;

import com.victor.latyshey.beans.Book;
import com.victor.latyshey.controller.CommandProvider;
import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.dao.BookDAO;
import com.victor.latyshey.dao.factory.DaoFactory;
import com.victor.latyshey.service.EmployeeService;
import com.victor.latyshey.service.UserService;
import com.victor.latyshey.service.impl.EmployeeServiceImpl;
import com.victor.latyshey.service.impl.UserServiceImpl;
import util.ResourceManager;

public class Main {

  private final static String URL = "jdbc:mysql://localhost:3300/mydbtest";
  private final static String USERNAME = "root";
  private final static String PASSWORD = "root";

  public static void main(String[] args) {

//    System.out.println(ResourceManager.getProperty("page.home"));

    System.out.println("main");
    Tets test = new Tets(181);
    System.out.println(test.a);

//    DaoFactory daoFactory = DaoFactory.getInstance();
//    BookDAO bookDAO = daoFactory.getBookDAO();
//    for (Book book: bookDAO.readAllBooks()) {
//      System.out.println(book);
//    }

//    Book book = new Book((int) (19129915*Math.random()), "Бульба", "Художественная литература", "Питер", 2008,
//        "твёрдый", "Картошку садят", 10.99f);
//    String[] authors = {"Николай Гоголь","Жерар Депардье"};
//    book.setAuthors(authors);

//    BookDAO bookDAO = new BookImpl();
////    bookDAO.createBook(book);
//
////    Book book = bookDAO.readBookByIsbn(10695596);
//
//    for(String author : book.getAuthors()){
//      System.out.println(author);
//    }
//    EmployeeService serviceE = new EmployeeServiceImpl();
//    UserService userService = new UserServiceImpl();
//    Book book = new Book();
//    book.setIsbn(18129915);
//    book.setAuthors(new String[]{"Стругацкий Е.У.", "Стругацкий А.У."});
//    book.setBookBinding("мягкий");
//    book.setBookDescription("Башня кипятит мозги выродкам!");
//    book.setGenre("Классическая литература");
//    book.setPrice(22.15f);
//    book.setPublishing("Питер");
//    book.setTitle("Башня");
//    book.setYear(2002);
//
////    service.addBookToStorage(book);
////    service.deleteBookFromStorage(10695596);
////    serviceE.addBookToStorage(book);
////    service.updateBookInStorage(book);
//    for(Book bookS : userService.showAllBooks()){
//      System.out.println(bookS);
//    }
////    for (Book b : bookDAO.readBooksByTitle("%Бульба%")) {
////      System.out.println(b);
////    }
//
//
//

  }
}
