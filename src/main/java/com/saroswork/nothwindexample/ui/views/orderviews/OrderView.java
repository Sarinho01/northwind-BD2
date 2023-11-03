package com.saroswork.nothwindexample.ui.views.orderviews;

import com.saroswork.nothwindexample.internal.order.Order;
import com.saroswork.nothwindexample.internal.order.OrderService;
import com.saroswork.nothwindexample.internal.orderdetails.OrderDetails;
import com.saroswork.nothwindexample.internal.orderdetails.OrderDetailsService;
import com.saroswork.nothwindexample.ui.views.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;


@Route(value = "orders", layout = MainView.class)
@PageTitle("Orders | NorthWind")
public class OrderView extends VerticalLayout {

    private final OrderService orderService;
    private final OrderDetailsService orderDetailsService;
    private final TextField filterOrderID = new TextField("Filter by OrderID");
    private final Grid<OrderDetails> orderDetailsGrid = new Grid<>(OrderDetails.class);
    private final Grid<Order> orderGrid = new Grid<>(Order.class);
    private VerticalLayout oDContent;

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
        closeOD();
    }

    private void configureGrid() {
        configureOrderGrid();
        configureOrderDetailsGrid();
    }

    private void configureOrderGrid() {
        orderGrid.addClassName("order-grid");
        orderGrid.setSizeFull();
        orderGrid.setColumns("orderID", "customerID", "employeeID", "orderDate", "freight", "requiredDate", "shippedDate", "shipVia",
                "shipAdress", "shipCity", "shipRegion", "shipPostalCode", "shipCountry");

        orderGrid.addComponentColumn(order -> {
            Button odButton = new Button("OrderDetails");
            odButton.addClickListener(e -> showOrderDetails(order));
            return odButton;
        }).setHeader("Show OD");
        orderGrid.getColumns().forEach(column -> column.setAutoWidth(true));
    }

    private void configureOrderDetailsGrid() {
        orderDetailsGrid.setClassName("orderdetails-grid");
        orderDetailsGrid.setSizeFull();
        orderDetailsGrid.setColumns("orderID", "productID", "unitPrice", "quantity", "discount");

        orderDetailsGrid.getColumns().forEach(column -> column.setAutoWidth(true));
        orderDetailsGrid.setSizeFull();
    }

    private Component getToolBar() {
        filterOrderID.setPlaceholder("Filter by orderID");
        filterOrderID.setClearButtonVisible(true);
        filterOrderID.setValueChangeMode(ValueChangeMode.LAZY);
        filterOrderID.addValueChangeListener(e -> updateList());

        HorizontalLayout layout = new HorizontalLayout(filterOrderID);
        layout.setMaxWidth(LumoUtility.Width.FULL);

        return layout;
    }

    private Component getContent() {
        Button closeButton = new Button("Close");
        closeButton.addClassName("close-button");
        closeButton.addClickShortcut(Key.ESCAPE);
        closeButton.addClickListener(e -> closeOD());

        this.oDContent = new VerticalLayout(orderDetailsGrid, closeButton);
        oDContent.addClassName("od-content");
        oDContent.setWidth("60rem");
        oDContent.setAlignItems(Alignment.CENTER);


        HorizontalLayout content = new HorizontalLayout(orderGrid, oDContent);
        content.setFlexGrow(2, orderGrid);
        content.setFlexGrow(1,oDContent);
        content.addClassName("content");
        content.setSizeFull();

        return content;
    }

    private void updateList() {
        orderGrid.setItems(orderService.findAll(filterOrderID.getValue()));
    }

    private void showOrderDetails(Order order) {
        addClassName("showingOD");
        orderDetailsGrid.setItems(orderDetailsService.findByOrderId(order.getOrderID()));
        oDContent.setVisible(true);
    }

    private void closeOD() {
        oDContent.setVisible(false);
        removeClassName("showingOD");

    }
}
