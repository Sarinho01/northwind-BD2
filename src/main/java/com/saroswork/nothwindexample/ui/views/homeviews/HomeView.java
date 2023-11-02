package com.saroswork.nothwindexample.ui.views.homeviews;

import com.saroswork.nothwindexample.internal.customer.Customer;
import com.saroswork.nothwindexample.internal.customer.CustomerService;
import com.saroswork.nothwindexample.ui.views.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route(value = "", layout = MainView.class)
public class HomeView extends VerticalLayout {
    private final CustomerService customerService;
    private final Grid<Customer> customerGrid = new Grid<>(Customer.class);


    public HomeView(CustomerService customerService) {
        this.customerService = customerService;
        
        configureGrid();

    
        add(
                getContent()
        );

    }

    private Component getContent() {
        H1 h1 = new H1("TOP 10 Customers with more ORDERS");
        VerticalLayout content = new VerticalLayout(h1,customerGrid);
        content.setAlignItems(Alignment.CENTER);

        return content;
    }



    private void configureGrid() {
        customerGrid.setColumns("customerID", "companyName", "contactName");
        customerGrid.setItems(customerService.findTOP10Customers());
    }
}
