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
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery(hql);
            List resultList = q.list();
            displayResult(resultList);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }
    
    private static void displayResult(List resultList) {
        for(Object o : resultList) {
            Sale sale = (Sale)o;
            System.out.println(sale.getProductName());
        }
    }
  
  public static void main(String[] args) {
    //initDB();
    runQueryAll();
    
  }
  
}
