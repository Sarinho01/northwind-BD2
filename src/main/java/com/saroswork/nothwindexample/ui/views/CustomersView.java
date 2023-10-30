package com.saroswork.nothwindexample.ui.views;

import com.saroswork.nothwindexample.internal.customer.Customer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("/customer")
@PageTitle("NorthWind Customers")
public class CustomersView extends VerticalLayout {
    Grid<Customer> customerGrid = new Grid<>(Customer.class);
    TextField filterText = new TextField();
    CustomerForm customerForm;


    public CustomersView(){
        addClassName("customer-list-view");
        setSizeFull();

        configureCustomerForm();
        configureGrid();
        add(
                getToolbar(),
                getContent()
        );
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(customerGrid, customerForm);
        content.setFlexGrow(2, customerGrid);
        content.setFlexGrow(1, customerForm);
        content.addClassName("content");
        content.setSizeFull();

        return content;
    }

    private void configureCustomerForm() {
        customerForm = new CustomerForm();
        customerForm.setWidth("25em");
    }

    private void configureGrid() {
        customerGrid.setClassName("customer-grid");
        customerGrid.setSizeFull();
        customerGrid.setColumns("customerID", "companyName", "contactName", "contactTitle", "address", "city", "region", "postalCode", "country", "phone", "fax");
        customerGrid.getColumns().forEach(grid -> grid.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by costumerID");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addCustomerButton = new Button("Add customer");

        HorizontalLayout toolbar = new HorizontalLayout();
        toolbar.add(filterText,addCustomerButton);
        toolbar.setClassName("toolbar");
        return toolbar;
    }
}
