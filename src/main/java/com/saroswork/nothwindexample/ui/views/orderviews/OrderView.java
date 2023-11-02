package com.saroswork.nothwindexample.ui.views.orderviews;

import com.saroswork.nothwindexample.internal.order.OrderService;
import com.saroswork.nothwindexample.internal.orderdetails.OrderDetails;
import com.saroswork.nothwindexample.internal.orderdetails.OrderDetailsService;
import com.saroswork.nothwindexample.ui.views.MainView;
import com.vaadin.flow.component.Component;
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
    private final Grid<OrderDetails> orderDetailsTreeGrid = new Grid<>();

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
        orderDetailsTreeGrid.addClassName("order-treegrid");
        orderDetailsTreeGrid.setSizeFull();
    }
}