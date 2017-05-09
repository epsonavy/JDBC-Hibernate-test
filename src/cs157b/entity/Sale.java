package cs157b.entity;

import java.util.Date;

/**
 * Sale 
 */
public class Sale  implements java.io.Serializable {


     private Date date;
     private String productName;
     private Integer quantity;
     private Long unitCost;
     private Long totalCost;

    public Sale() {
    }

	
    public Sale(Date date) {
        this.date = date;
    }
    public Sale(Date date, String productName, Integer quantity, Long unitCost, Long totalCost) {
       this.date = date;
       this.productName = productName;
       this.quantity = quantity;
       this.unitCost = unitCost;
       this.totalCost = totalCost;
    }
   
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    public String getProductName() {
        return this.productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Integer getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Long getUnitCost() {
        return this.unitCost;
    }
    
    public void setUnitCost(Long unitCost) {
        this.unitCost = unitCost;
    }
    public Long getTotalCost() {
        return this.totalCost;
    }
    
    public void setTotalCost(Long totalCost) {
        this.totalCost = totalCost;
    }




}


