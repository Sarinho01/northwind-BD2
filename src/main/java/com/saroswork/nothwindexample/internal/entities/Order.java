package com.saroswork.nothwindexample.internal.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID")
    private Integer orderID;

    @Column(name = "CustomerID", length = 5, columnDefinition = "NCHAR(5)")
    private String customerID;
    @Column(name = "EmployeeID")
    private Integer employeeID;

    @CreationTimestamp
    @Column(name = "OrderDate")
    private LocalDateTime orderDate;
    @Column(name = "RequiredDate")
    private LocalDateTime requiredDate;
    @Column(name = "ShippedDate")
    private LocalDateTime shippedDate;
    @Column(name = "ShipVia")
    private Integer shipVia;
    @Column(columnDefinition = "MONEY")
    private Double freight;
    @Column(name = "ShipName", length = 40)
    private String shipName;
    @Column(name = "ShipAdress", length = 60)
    private String shipAdress;
    @Column(name = "ShipCity", length = 15)
    private String shipCity;
    @Column(name = "ShipRegion", length = 15)
    private String shipRegion;
    @Column(name = "ShipPostalCode", length = 10)
    private String shipPostalCode;
    @Column(name = "ShipCountry", length = 15)
    private String shipCountry;

    public Integer getOrderID() {
        return orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(LocalDateTime requiredDate) {
        this.requiredDate = requiredDate;
    }

    public LocalDateTime getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(LocalDateTime shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Integer getShipVia() {
        return shipVia;
    }

    public void setShipVia(Integer shipVia) {
        this.shipVia = shipVia;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipAdress() {
        return shipAdress;
    }

    public void setShipAdress(String shipAdress) {
        this.shipAdress = shipAdress;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipRegion() {
        return shipRegion;
    }

    public void setShipRegion(String shipRegion) {
        this.shipRegion = shipRegion;
    }

    public String getShipPostalCode() {
        return shipPostalCode;
    }

    public void setShipPostalCode(String shipPostalCode) {
        this.shipPostalCode = shipPostalCode;
    }

    public String getShipCountry() {
        return shipCountry;
    }

    public void setShipCountry(String shipCountry) {
        this.shipCountry = shipCountry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderID, order.orderID) && Objects.equals(customerID, order.customerID) && Objects.equals(employeeID, order.employeeID) && Objects.equals(orderDate, order.orderDate) && Objects.equals(requiredDate, order.requiredDate) && Objects.equals(shippedDate, order.shippedDate) && Objects.equals(shipVia, order.shipVia) && Objects.equals(freight, order.freight) && Objects.equals(shipName, order.shipName) && Objects.equals(shipAdress, order.shipAdress) && Objects.equals(shipCity, order.shipCity) && Objects.equals(shipRegion, order.shipRegion) && Objects.equals(shipPostalCode, order.shipPostalCode) && Objects.equals(shipCountry, order.shipCountry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, customerID, employeeID, orderDate, requiredDate, shippedDate, shipVia, freight, shipName, shipAdress, shipCity, shipRegion, shipPostalCode, shipCountry);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", customerID='" + customerID + '\'' +
                ", employeeID=" + employeeID +
                ", orderDate=" + orderDate +
                ", requiredDate=" + requiredDate +
                ", shippedDate=" + shippedDate +
                ", shipVia=" + shipVia +
                ", freight=" + freight +
                ", shipName='" + shipName + '\'' +
                ", shipAdress='" + shipAdress + '\'' +
                ", shipCity='" + shipCity + '\'' +
                ", shipRegion='" + shipRegion + '\'' +
                ", shipPostalCode='" + shipPostalCode + '\'' +
                ", shipCountry='" + shipCountry + '\'' +
                '}';
    }
}
