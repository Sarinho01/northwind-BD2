package com.saroswork.nothwindexample.ui.views.customerviews;

import com.saroswork.nothwindexample.internal.customer.Customer;
import com.saroswork.nothwindexample.internal.customer.CustomerService;
import com.saroswork.nothwindexample.ui.views.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;

@Route(value = "", layout = MainView.class)
@PageTitle("NorthWind Customers")
@Theme("northwindtheme")
public class CustomersView extends VerticalLayout implements AppShellConfigurator {

    private final CustomerService customerService;
    private final Grid<Customer> customerGrid = new Grid<>(Customer.class);
    private final TextField filterText = new TextField();
    private CustomerForm customerForm;


    public CustomersView(CustomerService customerService) {
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

    private void configureCustomerForm() {
        customerForm = new CustomerForm();
        customerForm.setWidth("25em");

        customerForm.addSaveListener(this::saveContact);
        customerForm.addDeleteListener(this::deleteContact);
        customerForm.addCloseListener(e -> closeEditor());
    }

    private void configureGrid() {
        customerGrid.setClassName("customer-grid");
        customerGrid.setSizeFull();
        customerGrid.setColumns("customerID", "companyName", "contactName", "contactTitle", "address", "city", "region", "postalCode", "country", "phone", "fax");

        customerGrid.addComponentColumn(customer -> {
            Button updateButton = new Button("Update");
            updateButton.addClickListener(c -> updateContact(customer));
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
        toolbar.add(filterText, addCustomerButton);
        toolbar.setClassName("toolbar");
        return toolbar;
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(customerGrid, customerForm);
        content.setFlexGrow(2, customerGrid);
        content.setFlexGrow(1, customerForm);
        content.addClassName("content");
        content.setSizeFull();

        return content;
    }

    private void updateList() {
        customerGrid.setItems(customerService.findAll(filterText.getValue()));
    }

    private void closeEditor() {
        customerForm.setCustomer(null);
        customerForm.setVisible(false);
        removeClassName("editing");
    }

    private void editCustomer(Customer customer) {
        if (customer == null) {
            closeEditor();
            return;
        }
        customerForm.setCustomer(customer);
        customerForm.setVisible(true);
        addClassName("editing");
    }

    private void addContact() {
        editCustomer(new Customer());
    }

    private void saveContact(CustomerForm.SaveEvent saveEvent) {
        try {
            customerService.insert(saveEvent.getContact());
            updateList();
            closeEditor();
        }
        catch (Exception e){
            Notification.show(e.getMessage());
        }
    }

    private void deleteContact(CustomerForm.DeleteEvent deleteEvent) {
        try {
            customerService.delete(deleteEvent.getContact().getCustomerID());
            updateList();
            closeEditor();
        }catch (Exception e){
            Notification.show(e.getMessage());
        }
    }

    private void updateContact(Customer customer) {
        editCustomer(customer);
    }



}

