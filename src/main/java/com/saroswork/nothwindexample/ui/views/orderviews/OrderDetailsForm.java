package com.saroswork.nothwindexample.ui.views.orderviews;

import com.saroswork.nothwindexample.internal.orderdetails.OrderDetails;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.shared.Registration;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsForm extends FormLayout {
    private final ComboBox<Integer> productID = new ComboBox<>("productID");
    private final NumberField unitPrice = new NumberField("Unit Price");
    private final IntegerField quantity = new IntegerField("Quantity");
    private final IntegerField discount = new IntegerField("Discount");
    private final Button saveButton = new Button("Save");
    private final Button cancelButton = new Button("Cancel");

    public OrderDetailsForm() {
        configureComboBox();
        configureNumberFields();


        add(
                productID,
                unitPrice,
                quantity,
                discount,
                createButtonLayout()
        );

    }

    private void configureNumberFields() {
        unitPrice.setMin(0.01);
        Div dolarPrefix = new Div();
        dolarPrefix.setText("$");
        unitPrice.setPrefixComponent(dolarPrefix);

        quantity.setMin(1);
        quantity.setStepButtonsVisible(true);

        discount.setMin(0);
        discount.setMax(100);
        Div porcentagePrefix = new Div();
        porcentagePrefix.setText("%");
        discount.setPrefixComponent(porcentagePrefix);
        discount.setStepButtonsVisible(true);
    }

    private void configureComboBox() {
        List<Integer> pList = new ArrayList<>();

        for (int i = 1; i < 78; i++) {
            pList.add(i);
        }

        productID.setItems(pList);
    }

    private Component createButtonLayout() {
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancelButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        saveButton.addClickListener(buttonClickEvent -> fireEvent(new SaveEvent(this)));
        cancelButton.addClickListener(buttonClickEvent -> fireEvent(new CloseEvent(this)));

        return new HorizontalLayout(saveButton,cancelButton);
    }

    public OrderDetails createOrderDetails() {
        boolean isValid = validate();

        if (!isValid)
            return null;

        OrderDetails orderDetails = new OrderDetails();
        try {
            orderDetails.setProductID(productID.getValue());
            orderDetails.setUnitPrice(unitPrice.getValue());
            orderDetails.setQuantity(quantity.getValue());
            orderDetails.setDiscount(discount.getValue() / 100.0);
        } catch (Exception e) {
            Notification.show(e.getMessage());
            return null;
        }
        return orderDetails;
    }

    private boolean validate() {
        List<String> errors = new ArrayList<>();

        Integer productIDVar = productID.getValue();
        if (productIDVar == null
                || !(1 <= productIDVar && productIDVar <= 77)) {
            errors.add("ERROR: invalid productID");
        }

        Double unitPriceVar = unitPrice.getValue();
        if (unitPriceVar == null
                || unitPriceVar < 0.01) {
            errors.add("ERROR: invalid unit price");
        }

        Integer quantityVar = quantity.getValue();
        if (quantityVar == null
                || quantityVar < 1) {
            errors.add("ERROR: invalid quantity");
        }

        Integer discountVar = discount.getValue();
        if (discountVar == null
                || !(0 <= discountVar && discountVar <= 100)) {
            errors.add("ERROR: invalid discount");
        }

        errors.forEach(Notification::show);
        return errors.isEmpty();
    }

    public void clearFields() {
        productID.setValue(0);
        unitPrice.setValue(0.0);
        quantity.setValue(0);
        discount.setValue(0);
    }

    public abstract static class OrderDetailsFormEvent extends ComponentEvent<OrderDetailsForm> {
        protected OrderDetailsFormEvent(OrderDetailsForm source) {
            super(source, false);
        }

    }

    public static class SaveEvent extends OrderDetailsForm.OrderDetailsFormEvent {
        SaveEvent(OrderDetailsForm source) {
            super(source);
        }
    }

    public static class CloseEvent extends OrderDetailsForm.OrderDetailsFormEvent {
        CloseEvent(OrderDetailsForm source) {
            super(source);
        }
    }

    public Registration addSaveListener(ComponentEventListener<OrderDetailsForm.SaveEvent> listener) {
        return addListener(OrderDetailsForm.SaveEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<OrderDetailsForm.CloseEvent> listener) {
        return addListener(OrderDetailsForm.CloseEvent.class, listener);
    }
}
