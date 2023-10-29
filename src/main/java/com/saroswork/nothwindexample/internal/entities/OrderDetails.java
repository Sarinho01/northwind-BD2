package com.saroswork.nothwindexample.internal.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Order Details")
@IdClass(OrderDetailID.class)
public class OrderDetails {
    @Id
    @Column(name = "OrderID")
    private Integer orderID;
    @Id
    @Column(name = "ProductID")
    private Integer productID;
    @Column(name = "UnitPrice", columnDefinition = "MONEY")
    private Double unitPrice;
    @Column(name = "Quantity", columnDefinition = "SMALLINT")
    private Integer quantity;
    @Column(name = "Discount", columnDefinition = "REAL")
    private Double discount;

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getProductId() {
        return productID;
    }

    public void setProductId(Integer productId) {
        this.productID = productId;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }


}
