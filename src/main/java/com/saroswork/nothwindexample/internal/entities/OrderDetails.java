package com.saroswork.nothwindexample.internal.entities;

import jakarta.persistence.*;

import java.util.Objects;

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

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetails that = (OrderDetails) o;
        return Objects.equals(orderID, that.orderID) && Objects.equals(productID, that.productID) && Objects.equals(unitPrice, that.unitPrice) && Objects.equals(quantity, that.quantity) && Objects.equals(discount, that.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, productID, unitPrice, quantity, discount);
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderID=" + orderID +
                ", productID=" + productID +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", discount=" + discount +
                '}';
    }
}
