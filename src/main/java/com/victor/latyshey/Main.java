package com.victor.latyshey;

import com.victor.latyshey.beans.book.Author;
import com.victor.latyshey.beans.book.Book;
import com.victor.latyshey.beans.book.Genre;
import com.victor.latyshey.beans.book.Publishing;
import com.victor.latyshey.beans.builder.BookBuilder;
import com.victor.latyshey.beans.user.Role;
import com.victor.latyshey.beans.user.User;
import com.victor.latyshey.connection.ConnectionPool;
import com.victor.latyshey.connection.PoolException;
import com.victor.latyshey.dao.AuthorDao;
import com.victor.latyshey.dao.BookDAO;
import com.victor.latyshey.dao.UserDAO;
import com.victor.latyshey.dao.exception.DaoException;
import com.victor.latyshey.dao.impl.AuthorDaoImpl;
import com.victor.latyshey.dao.impl.BookDAOImpl;
import com.victor.latyshey.dao.impl.UserDaoImpl;
import com.victor.latyshey.service.BookService;
import com.victor.latyshey.service.UserService;
import com.victor.latyshey.service.exception.ServiceException;
import com.victor.latyshey.service.impl.BookServiceImpl;
import com.victor.latyshey.service.impl.UserServiceImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

  public static void main(String[] args) {
    Logger logger = LogManager.getLogger();

    Locale defaultLocale = Locale.getDefault();
    System.out.println(defaultLocale.getDisplayCountry(Locale.CHINA));

//    BookDAO bookDAO = new BookDAOImpl();
//    BookService service = new BookServiceImpl();
////    System.out.println("1256".hashCode());
////    User user = new User("ViclayCreateTest", "0129915");
////    user.setRole(new Role("EMPLOYEE_USER", 3));
////    user.setId(10);
////    AuthorDao authorDao = new AuthorDaoImpl();
//
//    try {
//      List<Author> authors = new ArrayList<>();
//      authors.add(new Author("Жан Амери"));
//      authors.add(new Author("Густав", "Германия"));
//      BookBuilder builder = new BookBuilder();
//      builder.setId(198565);
//      builder.setTitle("testTitlePr");
//      builder.setGenre(new Genre(2, "Художественная"));
//      builder.setPublishing(new Publishing(105, "Москва"));
//      builder.setYear(1917);
//      builder.setPrice(16.25f);
//      builder.setAuthors(authors);
//      service.createBook(builder.getResult());
//
//
//    } catch (ServiceException e) {
//      throw new RuntimeException(e);
//    }
  }

}

