package DatabaseTest;

import cs157b.entity.Sale;
import cs157b.util.HibernateUtil;
import java.sql.*; 
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import java.util.Date;

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
  private static final String conn = "jdbc:mysql://localhost:3306/hibernate";
  
  private static void initDB() {
   try {
      connection = DriverManager.getConnection(conn, username, password);
      command = connection.createStatement();

      command.execute("CREATE TABLE sale (" +
                      "Date DATE NOT NULL," +
                      "ProductName varchar(255)," +
                      "Quantity int(25)," +
                      "UnitCost DECIMAL," +
                      "TotalCost DECIMAL," +
                      "Primary Key(Date));");

      //command.execute("INSERT INTO sale_transaction VALUES"
       // + " ('2017-05-01', 'Apple', '10', '2', '20');");

    } catch (SQLException ex) {
      Logger.getLogger(DatabaseTest.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      System.out.println("Inital database completed!");
    }
  }

    private static String QUERY_ALL="from Sale";
    private static void runQueryAll() {
        executeHQLQuery(QUERY_ALL);
    }

    private static void executeHQLQuery(String hql) {
      Session session = HibernateUtil.getSessionFactory().openSession();
      try {          
            session.beginTransaction();
            Query q = session.createQuery(hql);
            List resultList = q.list();
            displayResult(resultList);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
          session.close();
       }
    }
    
    private static void displayResult(List resultList) {
        for(Object o : resultList) {
            Sale sale = (Sale)o;
            System.out.print("Date: " + sale.getDate() + " ");
            System.out.print("Name: " + sale.getProductName() + "\t");
            System.out.print("Qty: " + sale.getQuantity() + "\t");
            System.out.print("UP: $" + sale.getUnitCost() + "\t");
            System.out.print("Total: $" + sale.getTotalCost());
            System.out.println();
        }
    }
  
  public static void main(String[] args) {

    // Show original database
    runQueryAll();
    
    // Insert new sale transaction
    SaleTransaction saleTransaction;
    saleTransaction = new SaleTransaction(new Date(),"cake", 5, (long) 2, (long) 10);
    //saleTransaction.save();
    System.out.println("\nAfter saved one sale transaction:\n");
    
    // Show updated database
    runQueryAll();
    
    QuerySale query = new QuerySale();
    System.out.println("\nTrying to fetch all Apple product:\n");
    query.fetchProduct("Apple");
    
    System.out.println("\nTrying to fetch Date from 2017-05-02 to 2017-05-04 transactions:\n");
    query.fetchDateInterval("2017-05-02", "2017-05-04");
    
    System.out.println("\nTrying to fetch May, 2017 total sales: $\n");
    query.fetchTotalIncomeByMonth(2017, 5);
  }
  
}
