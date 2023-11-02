package com.saroswork.nothwindexample.ui.views.customerviews;

import com.saroswork.nothwindexample.internal.customer.Customer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

public class CustomerForm extends FormLayout {
    private Customer customer;
    private final Binder<Customer> binder = new BeanValidationBinder<>(Customer.class);

    private final TextField customerID = new TextField("CustomerID");
    private final TextField companyName = new TextField("Company Name");
    private final TextField contactName = new TextField("Contact Name");
    private final TextField contactTitle = new TextField("Contact Title");
    private final TextField address = new TextField("Address");
    private final TextField city = new TextField("City");
    private final TextField region = new TextField("Region");
    private final TextField postalCode = new TextField("Postal Code");
    private final TextField country = new TextField("Country");
    private final TextField phone = new TextField("Phone");
    private final TextField fax = new TextField("Fax");
    private final Button saveButton = new Button("Save");
    private final Button deleteButton = new Button("Delete");
    private final Button cancelButton = new Button("Cancel");

    public CustomerForm() {
        addClassName("customer-form");
        binder.bindInstanceFields(this);

        setFieldsLength();

        add(
                customerID,
                companyName,
                companyName,
                contactName,
                contactTitle,
                address,
                city,
                region,
                postalCode,
                country,
                phone,
                fax,
                createButtonLayout()
        );


    }

    private void setFieldsLength() {
        customerID.setMaxLength(5);
        companyName.setMaxLength(40);
        contactName.setMaxLength(30);
        contactTitle.setMaxLength(30);
        address.setMaxLength(60);
        city.setMaxLength(15);
        region.setMaxLength(15);
        postalCode.setMaxLength(10);
        country.setMaxLength(15);
        phone.setMaxLength(24);
        fax.setMaxLength(24);
    }

    private Component createButtonLayout() {
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancelButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        saveButton.addClickListener(buttonClickEvent -> validateAndSave());
        deleteButton.addClickListener(buttonClickEvent -> fireEvent(new DeleteEvent(this, customer)));
        cancelButton.addClickListener(buttonClickEvent -> fireEvent(new CloseEvent(this)));

        saveButton.addClickShortcut(Key.ENTER);
        cancelButton.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(saveButton, deleteButton, cancelButton);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(customer);
            fireEvent(new SaveEvent(this, customer));
        } catch (Exception e) {
            Notification.show("ERROR: cannot change CustomerID or some empty parameter");
        }
    }

    public Customer getCustomer() {
        return customer;
    }


    public void setCustomer(Customer customer) {
        this.customer = customer;
        binder.readBean(customer);
    }

    public abstract static class CustomerFormEvent extends ComponentEvent<CustomerForm> {
        private final Customer customer;

        protected CustomerFormEvent(CustomerForm source, Customer customer) {
            super(source, false);
            this.customer = customer;
        }

        public Customer getContact() {
            return customer;
        }
    }

    public static class SaveEvent extends CustomerFormEvent {
        SaveEvent(CustomerForm source, Customer contact) {
            super(source, contact);
        }
    }

    public static class DeleteEvent extends CustomerFormEvent {
        DeleteEvent(CustomerForm source, Customer customer) {
            super(source, customer);
        }

    }

    public static class CloseEvent extends CustomerFormEvent {
        CloseEvent(CustomerForm source) {
            super(source, null);
        }
    }

    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
        return addListener(DeleteEvent.class, listener);
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }


}
