package com.saroswork.nothwindexample.ui.views.orderviews;

import com.saroswork.nothwindexample.internal.customer.CustomerService;
import com.saroswork.nothwindexample.internal.order.Order;
import com.saroswork.nothwindexample.internal.order.OrderService;
import com.saroswork.nothwindexample.internal.orderdetails.OrderDetails;
import com.saroswork.nothwindexample.internal.orderdetails.OrderDetailsService;
import com.saroswork.nothwindexample.ui.views.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;


@Route(value = "orders/create", layout = MainView.class)
@PageTitle("Orders | Create | NorthWind")
public class CreateOrder extends VerticalLayout {
    private final OrderService orderService;
    private final OrderDetailsService orderDetailsService;
    private final List<OrderDetails> orderDetailsList = new ArrayList<>();
    private final OrderForm orderForm;
    private OrderDetailsForm orderDetailsForm;
    private final Grid<OrderDetails> orderDetailsGrid = new Grid<>(OrderDetails.class);


    public CreateOrder(OrderService orderService, OrderDetailsService orderDetailsService, CustomerService customerService) {
        this.orderDetailsService = orderDetailsService;
        this.orderService = orderService;
        this.orderForm = new OrderForm(customerService);


        configureGrid();
        configureOrderDetailsForm();

        add(
                getToolBar(),
                getContent()
        );

        clearClass();
    }

    private void configureGrid() {
        orderDetailsGrid.setClassName("orderdetails-grid");
        orderDetailsGrid.setColumns("productID", "unitPrice", "quantity", "discount");


        orderDetailsGrid.getColumns().forEach(column -> column.setAutoWidth(true));
    }

    private void configureOrderDetailsForm() {
        orderDetailsForm = new OrderDetailsForm();
        orderDetailsForm.setWidth("3rem");

        orderDetailsForm.addSaveListener(e -> saveOrderDetails());
        orderDetailsForm.addCloseListener(e -> closeOrderDetailsForm());
    }

    private void saveOrderDetails() {
        OrderDetails orderDetails = orderDetailsForm.createOrderDetails();
        if(orderDetails == null)
            return;
        orderDetailsList.add(orderDetails);
        updateOrderDetails();
        closeOrderDetailsForm();
    }

    private void updateOrderDetails() {
        orderDetailsGrid.setItems(orderDetailsList);
    }


    private Component getToolBar() {
        Button addOrderDetailsButton = new Button("add OrderDetails");
        addOrderDetailsButton.addClickListener(e -> addOrderDetails());

        VerticalLayout toolbar = new VerticalLayout(orderForm, addOrderDetailsButton);
        toolbar.setAlignItems(Alignment.CENTER);
        toolbar.setClassName("toolbar");

        return toolbar;
    }



    private void addOrderDetails() {
        orderDetailsForm.setVisible(true);
        orderDetailsForm.clearFields();
        addClassName("editing");
    }

    private void closeOrderDetailsForm() {
        orderDetailsForm.clearFields();
        orderDetailsForm.setVisible(false);
        removeClassName("editing");
    }

    private Component getContent() {
        HorizontalLayout layout = new HorizontalLayout(orderDetailsGrid, orderDetailsForm);
        layout.setFlexGrow(2, orderDetailsGrid);
        layout.setFlexGrow(1, orderDetailsForm);
        layout.addClassName("layout");
        layout.setSizeFull();

        Button addOrderButton = new Button("add Order");
        addOrderButton.addClickListener(e -> finalizeOrder());



        VerticalLayout content = new VerticalLayout(layout, addOrderButton);
        content.setAlignItems(Alignment.CENTER);
        content.setSizeFull();
        content.setClassName("content");


        return content;
    }

    private void finalizeOrder() {
        Order order = orderForm.createOrder();
        Order insertedOrder = orderService.insert(order);

        if(insertedOrder == null){
            Notification.show("ERROR: cannot add order in database");
            return;
        }

        orderDetailsList.forEach(od -> od.setOrderID(insertedOrder.getOrderID()));
        List<OrderDetails> insertedODlist = orderDetailsService.insertAll(orderDetailsList);

        if(insertedODlist == null || insertedODlist.isEmpty()){
            Notification.show("ERROR: cannot add orderdetails in database");
            return;
        }

        Notification.show("Order successful inserted, your orderID is "+insertedOrder.getOrderID());
        clearClass();

    }

    private void clearClass() {
        closeOrderDetailsForm();
        orderForm.clearFields();
        orderDetailsList.clear();
        updateOrderDetails();
    }
}
