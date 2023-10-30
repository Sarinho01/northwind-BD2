package com.saroswork.nothwindexample.ui.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class CustomerForm extends FormLayout {
    TextField customerID = new TextField("CustomerID");
    TextField companyName = new TextField("Company Name");
    TextField contactName = new TextField("Contact Name");
    TextField contactTitle = new TextField("Contact Title");
    TextField address = new TextField("Address");
    TextField city = new TextField("City");
    TextField region = new TextField("Region");
    TextField postalCode = new TextField("Postal Code");
    TextField country = new TextField("Country");
    TextField phone = new TextField("Phone");
    TextField fax = new TextField("Fax");
    Button saveButton = new Button("Save");
    Button deleteButton = new Button("Delete");
    Button cancelButton = new Button("Cancel");

    public CustomerForm() {
        addClassName("customer-form");

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

    private Component createButtonLayout() {
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancelButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        saveButton.addClickShortcut(Key.ENTER);
        cancelButton.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(saveButton,deleteButton,cancelButton);
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
}
