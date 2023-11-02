package com.saroswork.nothwindexample.ui.views.orderviews;

import com.saroswork.nothwindexample.internal.order.Order;
import com.saroswork.nothwindexample.internal.order.OrderService;
import com.saroswork.nothwindexample.internal.orderdetails.OrderDetails;
import com.saroswork.nothwindexample.internal.orderdetails.OrderDetailsService;
import com.saroswork.nothwindexample.ui.views.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "Orders", layout = MainView.class)
@PageTitle("Orders | NorthWind")
public class OrderView extends VerticalLayout {

    private final OrderService orderService;
    private final OrderDetailsService orderDetailsService;
    private final TextField filterOrderID = new TextField("Filter by OrderID");
    private final Grid<OrderDetails> orderDetailsGrid = new Grid<>();
    private final Grid<Order> orderGrid = new Grid<>();

    public OrderView(OrderService orderService, OrderDetailsService orderDetailsService) {
        this.orderService = orderService;
        this.orderDetailsService = orderDetailsService;

        addClassName("orders-list-view");
        setSizeFull();

        configureGrid();

        add(
                getToolBar(),
                getContent()
        );

        updateList();

    }

    private void updateList() {
    }

    private Component getToolBar() {
        return null;
    }

    private Component getContent() {
        return null;
    }

    private void configureGrid() {
        configureOrderGrid();
        configureOrderDetailsGrid();
    }

    private void configureOrderGrid() {
        orderGrid.addClassName("order-grid");
        orderGrid.setSizeFull();
        orderGrid.setColumns("orderID", "customerID", "employeeID", "orderDate", "requiredDate", "shippedDate", "shipVia",
                "shipAdress", "shipCity", "shipRegion", "shipPostalCode", "shipCountry");

        orderGrid.addComponentColumn(order -> {
            Button odButton = new Button("OrderDetails");
            odButton.addClickListener(e -> showOrderDetails(order));
            return odButton;
        }).setHeader("Show OD");
        orderGrid.getColumns().forEach(column -> column.setAutoWidth(true));
    }

    private void showOrderDetails(Order order) {

    }

    private void configureOrderDetailsGrid() {
        orderDetailsGrid.setClassName("orderdetails-grid");
        orderDetailsGrid.setSizeFull();
        orderDetailsGrid.setColumns("orderID", "productID", "unitPrice", "quantity", "discount");
        
        orderDetailsGrid.getColumns().forEach(column -> column.setAutoWidth(true));
    }
}