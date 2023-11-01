package com.saroswork.nothwindexample.ui.views;

import com.saroswork.nothwindexample.internal.customer.Customer;
import com.saroswork.nothwindexample.internal.customer.CustomerService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;

@Route("/customer")
@PageTitle("NorthWind Customers")
@Theme("northwindtheme")
public class CustomersView extends VerticalLayout implements AppShellConfigurator {

    private CustomerService customerService;
    Grid<Customer> customerGrid = new Grid<>(Customer.class);
    TextField filterText = new TextField();
    CustomerForm customerForm;


    public CustomersView(CustomerService customerService){
        this.customerService = customerService;

        addClassName("customer-list-view");
        setSizeFull();

        configureCustomerForm();
        configureGrid();
        add(
                getToolbar(),
                getContent()
        );
        
        updateList();
        closeEditor();
    }

    private void closeEditor() {
        customerForm.setCustomer(null);
        customerForm.setVisible(false);
        removeClassName("editing");
    }

    private void editCustomer(Customer customer) {
        if(customer == null) {
            closeEditor();
            return;
        }
        customerForm.setCustomer(customer);
        customerForm.setVisible(true);
        addClassName("editing");
    }

    private void updateList() {
        customerGrid.setItems(customerService.findAll(filterText.getValue()));
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

        customerGrid.addComponentColumn(customer-> {
            Button updateButton = new Button("Update");
            updateButton.addClickListener(c -> editCustomer(customer));
            return updateButton;
        }).setHeader("Update");
        customerGrid.getColumns().forEach(grid -> grid.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by costumerID");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());


        Button addCustomerButton = new Button("Add customer");
        addCustomerButton.addClickListener(e -> addContact());

        HorizontalLayout toolbar = new HorizontalLayout();
        toolbar.add(filterText,addCustomerButton);
        toolbar.setClassName("toolbar");
        return toolbar;
    }

    private void addContact() {
        editCustomer(new Customer());
    }
}
