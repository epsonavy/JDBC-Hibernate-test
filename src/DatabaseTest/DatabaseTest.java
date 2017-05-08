package DatabaseTest;

import java.sql.*; 
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pei Lian Liu
 */
public class DatabaseTest {
  
  private static Connection connection;
  private static Statement command;
  private static ResultSet data;
  private static final String username = "root";
  private static final String password = "root";
  private static final String conn = "jdbc:mysql://localhost:3306/test";

  public static void main(String[] args) {
    try {
      connection = DriverManager.getConnection(conn, username, password);
      command = connection.createStatement();

      command.execute("CREATE TABLE product (" +
                      "ID int(15) NOT NULL," +
                      "Name varchar(35)," +
                      "Price int(20)," +
                      "Primary Key(ID));");
      
      command.execute("CREATE TABLE transaction (" +
                      "Invoice_ID int(15) NOT NULL," +
                      "Customer varchar(35)," +
                      "Qty int(20)," +
                      "Total_Price int(30)," +
                      "Primary Key(Invoice_ID));");

      for (int i = 1; i < 21;i++) {
        command.execute("INSERT INTO product VALUES (" + i +
                        ",'Apple',"+ Math.random() * 100 + ")" );
      }
      
      for (int i = 22; i < 61;i++) {
        command.execute("INSERT INTO product VALUES (" + i +
                        ",'Banana',"+ Math.random() * 400 + ")" );
      }
      
      for (int i = 62; i < 101;i++) {
        command.execute("INSERT INTO product VALUES (" + i +
                        ",'Pineapple',"+ Math.random() * 600 + ")" );
      }
      
      for (int i = 1; i < 21;i++) {
        command.execute("INSERT INTO transaction VALUES (" + i +
                        ",'Peter',"+ Math.random() * 20 + ","+ Math.random() * 200 + ")" );
      }
      
      for (int i = 22; i < 61;i++) {
        command.execute("INSERT INTO transaction VALUES (" + i +
                        ",'Kevin',"+ Math.random() * 40 + ","+ Math.random() * 400 + ")" );
      }
      
      for (int i = 62; i < 101;i++) {
        command.execute("INSERT INTO transaction VALUES (" + i +
                        ",'Andrew',"+ Math.random() * 60 + ","+ Math.random() * 600 + ")" );
      }

    } catch (SQLException ex) {
      Logger.getLogger(DatabaseTest.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      System.out.println("Inital database completed!");
    }
    
    
    // Query 1
    try {
      connection = DriverManager.getConnection(conn, username, password);
      command = connection.createStatement();
      System.out.println("SELECT * FROM product WHERE Price < 40 Order By Price");
      data = command.executeQuery("SELECT * FROM product WHERE Price < 40 Order By Price");
    } catch (SQLException ex) {
      Logger.getLogger(DatabaseTest.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        if(data.first()) {
          while(data.next()) {
            System.out.println("Name: " + data.getString("Name") + "  Price: " + data.getString("Price"));
          }
        }
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
      
      System.out.println("Query test1 completed!");
    }
    
    // Query 2
    try {
      connection = DriverManager.getConnection(conn, username, password);
      command = connection.createStatement();
      System.out.println("SELECT Name, AVG(product.Price) As Average_Price FROM product WHERE Price < 40 Group By Name");
      data = command.executeQuery("SELECT Name, AVG(product.Price) As Average_Price FROM product WHERE Price < 40 Group By Name");
    } catch (SQLException ex) {
      Logger.getLogger(DatabaseTest.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        if(data.first()) {
          while(data.next()) {
            System.out.println("Name: " + data.getString("Name") + "  Price: " + data.getString("Average_Price"));
          }
        }
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
      
      System.out.println("Query test2 completed!");
    }
  }
  
}
