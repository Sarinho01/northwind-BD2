package com.saroswork.nothwindexample.ui.views;

import com.saroswork.nothwindexample.ui.views.customerviews.CustomersView;
import com.saroswork.nothwindexample.ui.views.homeviews.HomeView;
import com.saroswork.nothwindexample.ui.views.orderviews.CreateOrder;
import com.saroswork.nothwindexample.ui.views.orderviews.OrderView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;


public class MainView extends AppLayout {


    public MainView() {
       createHeader();
       createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("NorthWind");
        logo.addClassNames(LumoUtility.FontSize.LARGE,
                LumoUtility.Margin.MEDIUM
        );

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();

        header.addClassNames(
            LumoUtility.Padding.Vertical.NONE,
            LumoUtility.Padding.Horizontal.MEDIUM
        );

        addToNavbar(header);
    }

    private void createDrawer() {
        addToDrawer(
                new VerticalLayout(
                    new RouterLink("HOME", HomeView.class),
                    new RouterLink("Customers", CustomersView.class),
                    new RouterLink("Orders", OrderView.class),
                    new RouterLink("Create Order", CreateOrder.class)
        ));

    }

}
