package com.saroswork.nothwindexample.ui.views.orderviews;

import com.saroswork.nothwindexample.internal.customer.CustomerService;
import com.saroswork.nothwindexample.internal.order.Order;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class OrderForm extends FormLayout {
    private final CustomerService customerService;
    private HashSet<String> stringList;
    private final ComboBox<String> comboBoxCustomerID = new ComboBox<>("Customer");
    private final DateTimePicker requiredDate = new DateTimePicker("Required Date");
    private final ComboBox<Integer> comboBoxShipVia = new ComboBox<>("Ship Via");
    private final TextField shipName = new TextField("Ship Name");
    private final TextField shipAdress = new TextField("Ship Adress");
    private final TextField shipCity = new TextField("Ship City");
    private final TextField shipRegion = new TextField("Ship Region");
    private final TextField shipPostalCode = new TextField("Ship Postal Code");
    private final TextField shipCountry = new TextField("Ship Country");

    public OrderForm(CustomerService customerService) {
        this.customerService = customerService;
        addClassName("order-form");

        configureComboBox();
        setFieldLength();

        add(
                comboBoxCustomerID,
                requiredDate,
                comboBoxShipVia,
                shipName,
                shipAdress,
                shipCity,
                shipRegion,
                shipPostalCode,
                shipCountry
        );
    }

    private void setFieldLength() {
        shipName.setMaxLength(40);
        shipAdress.setMaxLength(60);
        shipCity.setMaxLength(15);
        shipRegion.setMaxLength(15);
        shipPostalCode.setMaxLength(10);
        shipCountry.setMaxLength(15);
    }

    private void configureComboBox() {
        List<String> listStr = new ArrayList<>();
        customerService.findAll("").forEach(c -> listStr.add(c.getCustomerID()));
        stringList = new HashSet<>(listStr);

        comboBoxCustomerID.setItems(listStr);

        comboBoxShipVia.setItems(1, 2, 3);
    }

    public Order createOrder() {
        boolean isValid = validate();
        if (!isValid)
            return null;

        Order order = new Order();
        Random random = new Random();

        try {
            order.setCustomerID(comboBoxCustomerID.getValue());
            order.setEmployeeID(random.nextInt(1, 10));
            order.setRequiredDate(requiredDate.getValue());
            order.setShipVia(comboBoxShipVia.getValue());
            order.setFreight((double) random.nextInt(1, 1000));
            order.setShipName(shipName.getValue());
            order.setShipAdress(shipAdress.getValue());
            order.setShipCity(shipCity.getValue());
            order.setShipRegion(shipRegion.getValue());
            order.setShipPostalCode(shipPostalCode.getValue());
            order.setShipCountry(shipCountry.getValue());
        } catch (Exception e) {
            Notification.show(e.getMessage());
            return null;
        }
        return order;
    }

    private boolean validate() {
        List<String> errors = new ArrayList<>();

        String customerID = comboBoxCustomerID.getValue();
        if (!stringList.contains(customerID)
                || customerID == null
                || customerID.isEmpty()) {
            errors.add("ERROR: invalid CustomerID");
        }

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime requiredDateVar = requiredDate.getValue();
        if (requiredDateVar == null
                || requiredDateVar.isBefore(currentTime)) {
            errors.add("ERROR: invalid required date");
        }

        Integer shipViaID = comboBoxShipVia.getValue();
        if (shipViaID == null
                || !(1 <= shipViaID && shipViaID <= 3)) {
            errors.add("ERROR: invalid Ship Via");
        }

        String shipNameVar = shipName.getValue();
        if (shipNameVar == null || shipNameVar.isEmpty()) {
            errors.add("ERROR: Ship name is null");
        }

        String shipCountryVar = shipCountry.getValue();
        if (shipCountryVar == null || shipCountryVar.isEmpty()) {
            errors.add("ERROR: Ship country is null");
        }

        String shipCityVar = shipCity.getValue();
        if (shipCityVar == null || shipCityVar.isEmpty()) {
            errors.add("ERROR: Ship city is null");
        }

        errors.forEach(Notification::show);
        return errors.isEmpty();
    }

    public void clearFields() {
        comboBoxCustomerID.setValue("");
        requiredDate.setValue(LocalDateTime.MIN);
        comboBoxShipVia.setValue(0);
        shipName.setValue("");
        shipAdress.setValue("");
        shipCity.setValue("");
        shipRegion.setValue("");
        shipPostalCode.setValue("");
        shipCountry.setValue("");
    }
}
