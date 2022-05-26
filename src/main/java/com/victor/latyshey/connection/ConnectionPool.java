package com.victor.latyshey.connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.ResourceManager;

public class ConnectionPool {

  private static AtomicReference<ConnectionPool> connectionPoolReference;
  private static final int INITIAL_CAPACITY;
  private static Lock lock;
  private BlockingQueue<Connection> freeConnections;
  private BlockingQueue<Connection> takenConnections;

  private static final Logger logger = LogManager.getLogger(ConnectionPool.class);

  static {
    connectionPoolReference = new AtomicReference<>();
    INITIAL_CAPACITY = 20;
    lock = new ReentrantLock();
  }

  {
    freeConnections = new ArrayBlockingQueue<>(INITIAL_CAPACITY);
    takenConnections = new ArrayBlockingQueue<>(INITIAL_CAPACITY);
  }

  private ConnectionPool() throws PoolException {

    try {
      lock.lock();
      if (connectionPoolReference.get() != null) {
        throw new UnsupportedOperationException();
      } else {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
      }
    } catch (SQLException e) {
      logger.log(Level.ERROR, "Can't register driver " + ResourceManager.getProperty("db.driver"));
      throw new PoolException(e + "Cant' register driver");
    } finally {
      lock.unlock();
    }
    init();
  }

  private void init() throws PoolException{
    ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    String connectionURL = resourceBundle.getString("db.url");
    String initialCapacityString = resourceBundle.getString("db.poolsize");
    Integer initialCapacity = Integer.valueOf(initialCapacityString);
    String login = resourceBundle.getString("db.user");
    String password = resourceBundle.getString("db.password");
    for (int i = 0; i < initialCapacity; i++) {
      try {
        Connection connection = new PoolConnection(DriverManager.getConnection(connectionURL, login, password));
        freeConnections.add(connection);
        logger.log(Level.INFO, "created connection with hash=" + connection.hashCode());
      } catch (SQLException e) {
//        logger.log(Level.ERROR, "Connection can't initialize", e);
        throw new PoolException("Connection can't initialize", e);
      }
    }
  }

  public static ConnectionPool getInstance() {
    if (connectionPoolReference.get() == null) {
      try {
        lock.lock();
        if (connectionPoolReference.get() == null) {
          connectionPoolReference.set(new ConnectionPool());
        }
      } catch (PoolException e) {
//        logger.log(Level.ERROR, "Can't get instance", e);
        throw new RuntimeException("Can't get instance", e);
      } finally {
        lock.unlock();
      }
    }
    return connectionPoolReference.get();
  }

  public Connection getConnection() throws PoolException {
    try {
      Connection connection = freeConnections.take();
      takenConnections.offer(connection);

      return connection;
    } catch (InterruptedException e) {
//      logger.log(Level.ERROR, "Can't get database", e);
      throw new PoolException("Can't get database", e);
    }
  }

  public void releaseConnection(Connection connection) {
    takenConnections.remove(connection);
    freeConnections.offer(connection);
  }

  public void destroy() {
    freeConnections.addAll(takenConnections);
    takenConnections.clear();
    int size = freeConnections.size();
    for (int i = 0; i < size; i++) {
      try {
        PoolConnection connection = (PoolConnection) freeConnections.take();
        logger.log(Level.INFO, "Real close connection with hash=" + connection.hashCode());
        connection.realClose();
      } catch (InterruptedException e) {
        logger.log(Level.ERROR, "Connection close exception", e);
      }
    }
    Enumeration<Driver> drivers = DriverManager.getDrivers();
    while (drivers.hasMoreElements()) {
      Driver driver = drivers.nextElement();
      try {
        DriverManager.deregisterDriver(driver);
//        logger.log(Level.INFO, String.format("Deregistering jdbc driver: %s", driver));
      } catch (SQLException e) {
//        logger.log(Level.ERROR, String.format("Error deregistering driver %s", driver), e);
      }
    }


  }

}
