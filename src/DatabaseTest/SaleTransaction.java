
package DatabaseTest;

import cs157b.entity.Sale;
import cs157b.util.HibernateUtil;
import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Pei Lian Liu
 */
public class SaleTransaction {
  
  private Date date;
  private String productName;
  private Integer quantity;
  private Long unitCost;
  private Long totalCost;

  public SaleTransaction(Date date, String productName, Integer quantity, Long unitCost, Long totalCost) {
    this.date = date;
    this.productName = productName;
    this.quantity = quantity;
    this.unitCost = unitCost;
    this.totalCost = totalCost;
  }
  
  public void save(){
    
    Sale sale = new Sale();
    sale.setDate(date);
    sale.setProductName(productName);
    sale.setQuantity(quantity);
    sale.setUnitCost(unitCost);
    sale.setTotalCost(totalCost);
   
    Session session = HibernateUtil.getSessionFactory().openSession();
    try {
        session.beginTransaction();
        session.save(sale);
        session.getTransaction().commit();
    } catch (HibernateException he) {
        he.printStackTrace();
        session.getTransaction().rollback();
    } finally {
        session.close();
    }
  }
}
