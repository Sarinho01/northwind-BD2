package com.saroswork.nothwindexample.internal.orderdetails;


import java.io.Serializable;
import java.util.Objects;

public class OrderDetailID implements Serializable {
    protected  Integer orderID;
    protected  Integer productID;

    public OrderDetailID() {
    }

    public OrderDetailID(Integer orderID, Integer productID) {
        this.orderID = orderID;
        this.productID = productID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailID that = (OrderDetailID) o;
        return Objects.equals(orderID, that.orderID) && Objects.equals(productID, that.productID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, productID);
    }
}
