package DatabaseTest;

import cs157b.entity.Sale;
import cs157b.util.HibernateUtil;
import java.text.DateFormatSymbols;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Pei Lian Liu
 */
public class QuerySale {
  public void fetchProduct(String item) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    try {
        session.beginTransaction();
        Query q = session.createQuery("from Sale WHERE ProductName='"+ item +"'");
        List resultList = q.list();
        displayResult(resultList);
        session.getTransaction().commit();
    } catch (HibernateException he) {
        he.printStackTrace();
        session.getTransaction().rollback();
    } finally {
        session.close();
    }
  }
  
  public void fetchDateInterval(String from, String to) {
    String hql = "from Sale WHERE Date >= '"+from+"' AND Date <= '"+to+"'";    
    Session session = HibernateUtil.getSessionFactory().openSession();
    try {
        session.beginTransaction();
        Query q = session.createQuery(hql);
        List resultList = q.list();
        displayResult(resultList);
        session.getTransaction().commit();
    } catch (HibernateException he) {
        he.printStackTrace();
        session.getTransaction().rollback();
    } finally {
        session.close();
    }
  }
  
  public void fetchTotalIncomeByMonth(int year, int month) {

    Number number = 100;
    String[] months = new DateFormatSymbols().getMonths();
    String hql = "select sum(S.totalCost) from Sale S WHERE month(Date) = '"+month+"' AND year(Date) = '"+year+"'";    
    Session session = HibernateUtil.getSessionFactory().openSession();
    try {
        session.beginTransaction();
        Query q = session.createQuery(hql);
        List resultList = q.list();
        number = (Number) resultList.get(0);
        System.out.println(months[month - 1]+" this month total sales is : " + number.intValue());
        session.getTransaction().commit();
    } catch (HibernateException he) {
        he.printStackTrace();
        session.getTransaction().rollback();
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
  
}
